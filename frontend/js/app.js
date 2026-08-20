const studentsBody = document.querySelector('#students');
const statusElement = document.querySelector('#status');
const totalElement = document.querySelector('#total-estudiantes');
const menuToggle = document.querySelector('#menu-toggle');
const mainMenu = document.querySelector('#main-menu');
const form = document.querySelector('#student-form');
const fields = {
    id: document.querySelector('#student-id'),
    ci: document.querySelector('#student-ci'),
    name: document.querySelector('#student-name'),
    surname: document.querySelector('#student-surname'),
    email: document.querySelector('#student-email'),
    code: document.querySelector('#student-code'),
    semester: document.querySelector('#student-semester')
};

menuToggle.addEventListener('click', () => {
    const isOpen = menuToggle.getAttribute('aria-expanded') === 'true';
    menuToggle.setAttribute('aria-expanded', String(!isOpen));
    menuToggle.setAttribute('aria-label', isOpen ? 'Abrir menú' : 'Cerrar menú');
    mainMenu.classList.toggle('open', !isOpen);
});

mainMenu.querySelectorAll('a').forEach(link => link.addEventListener('click', () => {
    menuToggle.setAttribute('aria-expanded', 'false');
    menuToggle.setAttribute('aria-label', 'Abrir menú');
    mainMenu.classList.remove('open');
}));

async function request(url, options = {}) {
    const response = await fetch(url, {
        headers: { 'Content-Type': 'application/json', ...(options.headers ?? {}) },
        ...options
    });
    if (!response.ok) {
        const detail = await response.text();
        throw new Error(detail || `HTTP ${response.status}`);
    }
    return response.status === 204 ? null : response.json();
}

function showForm(student = null) {
    form.hidden = false;
    fields.id.value = student?.id ?? '';
    fields.ci.value = student?.ci ?? '';
    fields.name.value = student?.nombre ?? '';
    fields.surname.value = student?.apellido ?? '';
    fields.email.value = student?.correo ?? '';
    fields.code.value = student?.codigoEstudiante ?? '';
    fields.semester.value = student?.semestreActual ?? 1;
    fields.name.focus();
}

function hideForm() {
    form.reset();
    form.hidden = true;
}

async function loadDashboard() {
    try {
        const dashboard = await request('/api/dashboard');
        document.querySelector('#total-docentes').textContent = dashboard.docentes;
        document.querySelector('#total-carreras').textContent = dashboard.carreras;
        document.querySelector('#total-inscripciones').textContent = dashboard.inscripciones;
    } catch {
        ['#total-docentes', '#total-carreras', '#total-inscripciones']
            .forEach(selector => document.querySelector(selector).textContent = '-');
    }
}

async function loadStudents() {
    statusElement.textContent = 'Consultando la API...';
    try {
        const response = await fetch('/api/estudiantes');
        if (!response.ok) throw new Error(`HTTP ${response.status}`);
        const students = await response.json();
        totalElement.textContent = students.length;
        studentsBody.replaceChildren(...students.map(student => {
            const row = document.createElement('tr');
            [student.codigoEstudiante, student.nombreCompleto, student.correo,
                student.carrera ?? 'Sin carrera', student.estadoAcademico].forEach(value => {
                const cell = document.createElement('td');
                cell.textContent = value;
                row.append(cell);
            });
            const actions = document.createElement('td');
            const edit = document.createElement('button');
            edit.type = 'button';
            edit.className = 'table-action';
            edit.textContent = 'Editar';
            edit.addEventListener('click', () => showForm(student));
            const remove = document.createElement('button');
            remove.type = 'button';
            remove.className = 'table-action danger';
            remove.textContent = 'Eliminar';
            remove.addEventListener('click', () => deleteStudent(student.id));
            actions.append(edit, remove);
            row.append(actions);
            return row;
        }));
        statusElement.textContent = `${students.length} estudiantes cargados desde ObjectDB.`;
    } catch (error) {
        totalElement.textContent = '0';
        statusElement.textContent = `No fue posible consultar la API: ${error.message}`;
    }
}

async function deleteStudent(id) {
    if (!window.confirm('¿Eliminar este estudiante?')) return;
    try {
        await request(`/api/estudiantes/${id}`, { method: 'DELETE' });
        statusElement.textContent = 'Estudiante eliminado correctamente.';
        await loadStudents();
        await loadDashboard();
    } catch (error) {
        statusElement.textContent = `No fue posible eliminar: ${error.message}`;
    }
}

form.addEventListener('submit', async event => {
    event.preventDefault();
    const payload = {
        ci: fields.ci.value,
        nombre: fields.name.value,
        apellido: fields.surname.value,
        correo: fields.email.value,
        codigoEstudiante: fields.code.value,
        semestreActual: Number(fields.semester.value)
    };
    const id = fields.id.value;
    try {
        await request(id ? `/api/estudiantes/${id}` : '/api/estudiantes', {
            method: id ? 'PUT' : 'POST',
            body: JSON.stringify(payload)
        });
        hideForm();
        statusElement.textContent = id ? 'Estudiante actualizado correctamente.' : 'Estudiante creado correctamente.';
        await loadStudents();
        await loadDashboard();
    } catch (error) {
        statusElement.textContent = `No fue posible guardar: ${error.message}`;
    }
});

document.querySelector('#reload').addEventListener('click', loadStudents);
document.querySelector('#cancel-student').addEventListener('click', hideForm);
document.querySelector('#new-student').addEventListener('click', () => showForm());
loadStudents();
loadDashboard();
