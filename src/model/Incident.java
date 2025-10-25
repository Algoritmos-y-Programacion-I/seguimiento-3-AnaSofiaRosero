package model;

import java.time.LocalDate;

public class Incident {

    private LocalDate reporteDatos;
    private String descripcion;
    private boolean solucion;
    private int horasSolucion;

    public Incident(String descripcion, int horasSolucion) {
        this.reporteDatos = LocalDate.now();
        this.descripcion = descripcion;
        this.horasSolucion = horasSolucion;
        this.solucion = false;
    }

    public LocalDate getReporteDatos() {
        return this.reporteDatos;
    }

    public void setReporteDatos(LocalDate reporteDatos) {
        this.reporteDatos = reporteDatos;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getSolucion() {
        return this.solucion;
    }

    public void setSolucion(boolean solucion) {
        this.solucion = solucion;
    }

    public int getHorasSolucion() {
        return this.horasSolucion;
    }

    public void setHorasSolucion(int horasSolucion) {
        this.horasSolucion = horasSolucion;
    }
}
