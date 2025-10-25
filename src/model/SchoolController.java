package model;

public class SchoolController {

    
    private String nombre;
    private int horasGastadas;

   
    public static final int PISOS = 5;
    public static final int COLUMNAS = 10;
    public static final int MAXIMOHORASSOPORTE = 100;

  
    private Computer[][] computadores;

    
    public SchoolController(String nombre) {
        this.nombre = nombre;
        this.horasGastadas = 0;
        this.computadores = new Computer[PISOS][COLUMNAS];
    }

   
    public String getNombre() {
        return this.nombre;
    }
   
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
  
    public int getHorasGastadas() {
        return this.horasGastadas;
    }

    public void setHorasGastadas(int horasGastadas) {
        this.horasGastadas = horasGastadas;
    }

    /**
     * Aqui agregamos un computador en la primera columna disponible del piso dado,
     * siempre que el número serial sea único.
     *
     * @param serial número serial único del computador.
     * @param piso   piso destino 
     * @param ventana true si el puesto está junto a ventana.
     * @return true si se agregó; false si el serial ya existe o el piso está lleno.
     *
     * Precondición: 0 <= piso < PISOS.
     * Postcondición: si es exitoso, el computador queda almacenado en la primera
     *                columna libre del piso indicado.
     */
    public boolean agregarComputador(String serial, int piso, boolean ventana) {
        if (this.buscarComputador(serial) != null) {
            return false;
        }

        for (int col = 0; col < COLUMNAS; col++) {
            if (this.computadores[piso][col] == null) {
                this.computadores[piso][col] = new Computer(serial, ventana);
                return true;
            }
        }

        return false;
    }

    /**
     * Buscamos un computador por su número serial en toda la matriz.
     *
     * @param serial número serial del computador.
     * @return referencia al computador si existe; de lo contrario null.
     *
     * Postcondición: no modifica el estado del sistema.
     */
    public Computer buscarComputador(String serial) {
        for (int i = 0; i < PISOS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                if (this.computadores[i][j] != null &&
                    this.computadores[i][j].getNumeroSerial().equals(serial)) {

                    return this.computadores[i][j];
                }
            }
        }

        return null;
    }

    /**
     * Registramos un incidente para el computador identificado por el serial,
     * sin exceder las horas máximas de soporte.
     *
     * @param serial       número serial del computador.
     * @param descripcion  descripción del incidente.
     * @param horas        horas de solución a consumir 
     * @return true si se registró; false si el computador no existe o no hay horas disponibles.
     *
     * Precondición: horas >= 0.
     * Postcondición: si es exitoso, el incidente queda agregado al computador y
     *                las horasGastadas aumentan en 'horas'.
     */
    public boolean reportarIncidente(String serial, String descripcion, int horas) {
        if (horas < 0) {
            return false;
        }

        Computer comp = this.buscarComputador(serial);

        if (comp != null && this.horasGastadas + horas <= MAXIMOHORASSOPORTE) {
            Incident inc = new Incident(descripcion, horas);
            comp.agregarIncidente(inc);
            this.horasGastadas += horas;
            return true;
        }

        return false;
    }

    /**
     * Determinamos el computador con mayor cantidad de incidentes y entrega un mensaje
     * con su serial, piso, columna y cantidad.
     *
     * @return mensaje con la info del computador con más incidentes,
     *         o "No hay computadores registrados." si la matriz está vacía.
     *
     * Postcondición: no modifica el estado del sistema.
     */
    public String computadorConMasIncidentes() {
    Computer mayor = null;
    int fila = -1;
    int columna = -1;
    int max = -1; // para que el primer compu cuente aunque tenga 0 incidentes

    for (int i = 0; i < PISOS; i++) {
        for (int j = 0; j < COLUMNAS; j++) {
            Computer c = this.computadores[i][j];
            if (c != null && c.cantidadIncidentes() > max) {
                max = c.cantidadIncidentes();
                mayor = c;
                fila = i;
                columna = j;
            }
        }
    }

    if (mayor != null) {
        return "El computador con más incidentes es " + mayor.getNumeroSerial()
             + " ubicado en piso " + fila
             + ", columna " + columna
             + " con " + max + " incidentes.";
    }

    return "No hay computadores registrados.";
    }
}