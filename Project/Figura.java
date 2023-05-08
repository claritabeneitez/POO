package Project;
/** 
 * Clase que modela figuras geometricas.
 * @author Clara Beneitez
 * @version 1.0, 5/7/2021
 */
public abstract class Figura {
    private Color color;
    /**
     * Metodo que calcula el area de la figura.
     * @return el area de la figura 
     */
    public abstract double area();
    /**
     * Metodo que identifica si dos figuras dadas son iguales
     * @param o
     * @return true si son iguales o false en otro caso
     */
    public abstract boolean esIgualA(Object o);
    /**
     * Metodo que dispone los atributos de una figura con un formato especifico.
     * @return cadena con el formato especificado
     */
    public abstract String toString();
}
