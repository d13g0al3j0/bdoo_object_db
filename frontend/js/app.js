const studentsBody = document.querySelector('#students');
const statusElement = document.querySelector('#status');
const totalElement = document.querySelector('#total-estudiantes');

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
            return row;
        }));
        statusElement.textContent = `${students.length} estudiantes cargados desde ObjectDB.`;
    } catch (error) {
        totalElement.textContent = '0';
        statusElement.textContent = `No fue posible consultar la API: ${error.message}`;
    }
}

document.querySelector('#reload').addEventListener('click', loadStudents);
loadStudents();
