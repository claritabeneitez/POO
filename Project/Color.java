package Project;
/** 
 * Clase que modela el color de las figuras geometricas.
 * @author Clara Beneitez
 * @version 1.0, 5/7/2021
 */
public class Color {
    // Atributos
    private int r;
    private int g;
    private int b;

    // Atributos estaticos
    private static final int MAX = 255;

    // Constructor
    /** 
     * Metodo constructor que crea un color a parir de sus componente RGB.
     * @param r
     * @param g
     * @param b 
     */
    Color(int r, int g, int b) {
        if ((r > MAX) || (g > MAX) || (b > MAX)) {
            this.r = MAX;
            this.g = MAX;
            this.b = MAX;
        } else {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }

    // Metodos estaticos o de Clase
    /**
     * Metodo estatico que modela el color blanco.
     * @return el color blanco
     */
    public static final Color BLANCO() {
        return new Color(255,255,255);
    }
    /**
     * Metodo estatico que modela el color negro.
     * @return el color negro
     */
    public static final Color NEGRO(){
    	return new Color(0, 0, 0);
    }
    /**
     * Metodo estatico que modela el color rojo.
     * @return el color rojo
     */
    public static final Color ROJO(){
    	return new Color(255, 0, 0);
    }
    /**
     * Metodo estatico que modela el color azul.
     * @return el color azul
     */
    public static final Color AZUL(){
    	return new Color(0, 0, 255);
    }
    /**
     * Metodo estatico que modela el color verde.
     * @return el color verde
     */
    public static final Color VERDE(){
    	return new Color(0, 255, 0);
    }
    /**
     * Metodo estatico que modela el color amarillo.
     * @return el color amarillo
     */
    public static final Color AMARILLO(){
    	return new Color(255, 255, 0);
    }
    /**
     * Metodo estatico que modela el color cyan.
     * @return el color cyan
     */
    public static final Color CYAN(){
    	return new Color(0, 255, 255);
    }
    /**
     * Metodo estatico que modela el color magenta.
     * @return el color magenta
     */
    public static final Color MAGENTA(){
    	return new Color(255, 0, 255);
    }
    
    // Metodos no esteticos o de Instancia
    /**
     * Metodo que identifica si dos colores dados son iguales.
     * @param color
     * @return true si son iguales o false en otro caso
     */
    public boolean esIgualA(Color color) {
        if (this.r == color.getR() && this.g == color.getG() && this.b == color.getB()){
            return true;
        }else { 
            return false;
        }
    }
    /**
     * Metodo que dispone los atributos de un color con un formato especifico.
     * @return cadena con el formato especificado
     */
    public String toString() {
        return "R" + r + "G" + g + "B" + b;
    }

    // 	Getters y Setters
    /**
     * Metodo getter del color relativo al atributo r.
     * @return el componente R del color 
     */
    public int getR() {
        return this.r;
    }
    /**
     * Metodo getter del color relativo al atributo g.
     * @return el componente G del color 
     */
    public int getG() {
        return this.g;
    }
    /**
     * Metodo getter del color relativo al atributo b.
     * @return el componente B del color 
     */
    public int getB() {
        return this.b;
    }
    /**
     * Metodo setter del color relativo al atributo r.
     * @param r 
     */
    public void setR(int r) {
        this.r = r;
    }
    /**
     * Metodo setter del color relativo al atributo g.
     * @param g
     */
    public void setG(int g) {
    	this.g = g;
    }
    /**
     * Metodo setter del color relativo al atributo b.
     * @param b
     */
    public void setB(int b) {
    	this.b = b;
    }
}
