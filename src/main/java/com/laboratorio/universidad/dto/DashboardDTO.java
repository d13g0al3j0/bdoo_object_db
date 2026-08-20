package com.laboratorio.universidad.dto;

public class DashboardDTO {
    private long estudiantes;
    private long docentes;
    private long carreras;
    private long materias;
    private long inscripciones;
    private long pagos;
    private double ingresos;
    private double promedioGeneral;

    public DashboardDTO() {
    }

    public DashboardDTO(long estudiantes, long docentes, long carreras, long materias,
                        long inscripciones, long pagos, double ingresos, double promedioGeneral) {
        this.estudiantes = estudiantes;
        this.docentes = docentes;
        this.carreras = carreras;
        this.materias = materias;
        this.inscripciones = inscripciones;
        this.pagos = pagos;
        this.ingresos = ingresos;
        this.promedioGeneral = promedioGeneral;
    }

    public long getEstudiantes() { return estudiantes; }
    public long getDocentes() { return docentes; }
    public long getCarreras() { return carreras; }
    public long getMaterias() { return materias; }
    public long getInscripciones() { return inscripciones; }
    public long getPagos() { return pagos; }
    public double getIngresos() { return ingresos; }
    public double getPromedioGeneral() { return promedioGeneral; }
}
