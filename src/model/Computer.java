package model;

import java.util.ArrayList;

public class Computer {

    private String numeroSerial;
    private boolean siguienteVentana; 
    private ArrayList<Incident> incidentes;

    public Computer(String numeroSerial, boolean siguienteVentana) {
        this.numeroSerial = numeroSerial;
        this.siguienteVentana = siguienteVentana;
        this.incidentes = new ArrayList<Incident>();
    }

    public String getNumeroSerial() {
        return this.numeroSerial;
    }

    public void setNumeroSerial(String numeroSerial) {
        this.numeroSerial = numeroSerial;
    }

    public boolean getSiguienteVentana() {
        return this.siguienteVentana;
    }

    public void setSiguienteVentana(boolean siguienteVentana) {
        this.siguienteVentana = siguienteVentana;
    }

    public void agregarIncidente(Incident nuevo) {
        this.incidentes.add(nuevo);
    }

    public int cantidadIncidentes() {
        return this.incidentes.size();
    }

    public String toString() {
        return "Serial: " + this.numeroSerial
             + " | Ventana: " + this.siguienteVentana
             + " | Incidentes: " + this.cantidadIncidentes();
    }
}
