package Project;
/** 
 * Clase que modela las coordenadas de la tesela.
 * @author Clara Beneitez
 * @version 1.0, 5/7/2021
 */
public class Coordenada implements Comparable <Coordenada> {
    // Atributos
    private int fila;
    private int columna;
    
    // Constructor
    /**
     * Metodo constructor que crea una coordenada a partir de una fila y una columna.
     * @param fila
     * @param columna 
     */
    Coordenada(int fila, int columna) {
        this.fila=fila;
        this.columna=columna;
    }
    /**
     * Metodo que compara las coordenadas entre si para ordenarlas.
     * @param c
     * @return int
     */
    public int compareTo(Coordenada c) {
        int resultado=0;
        if (this.fila>c.getFila()) {
            resultado=1;
        }
        if (this.fila<c.getFila()) {
            resultado=-1;
        }
        if (this.fila==c.getFila()) {
            if (this.columna>c.getColumna()) {
                resultado=1;
            }
            if (this.columna<c.getColumna()) {
                resultado=-1;
            }
        }
        return resultado;
    }
    /**
     * Metodo que identifica si dos coordenadas dadas son iguales.
     * @param coordenada
     * @return true si son iguales o false en otro caso
     */
    public boolean esIgualA(Coordenada coordenada){
        if(this.fila==coordenada.getFila()){
            if(this.columna==coordenada.getColumna()){
                return true;
            }
        }
        return false;
    }
    /**
     * Metodo que dispone los atributos de una coordenada con un formato especifico.
     * @return cadena con el formato especificado
     */
    public String toString(){
        return "(" + fila + "," + columna + ")";
    }
     	
// Getters y Setters
    /**
     * Metodo getter de la coordenada relativo al atributo fila.
     * @return la fila de la coordenada
     */
    public int getFila() {
        return this.fila;
    }
    /**
     * Metodo getter de la coordenada relativo al atributo columna.
     * @return la columna de la coordenada
     */
    public int getColumna() {
        return this.columna;
    }
     /**
     * Metodo setter de la coordenada relativo al atributo fila.
     * @param fila 
     */
    public void setFila(int fila) {
        this.fila = fila;
    }
     /**
     * Metodo setter de la coordenada relativo al atributo columna.
     * @param columna 
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }
}
