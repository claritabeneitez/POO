package Project;
/** 
 * Clase que modela circulos.
 * @author Clara Beneitez
 * @version 1.0, 5/7/2021
 */
public class Circulo extends Figura{

    // Atributos
    private Color color;
    private int radio; 

    // Atributos estaticos
    private static final double PI = 3.141592653;

    // Constructor
    /** 
     * Metodo constructor que crea un circulo a partir de un radio y un color.
     * @param radio
     * @param color 
     */
    Circulo(int radio,Color color) {
    	if (radio>100) {
            this.radio = 100;
    	}
    	else {
            this.radio = radio;
    	}
    	this.color=color;
    }
    
    // Metodos no estaticos o de Instancia
    /** 
     * Metodo que calcula el area de el circulo.
     * @return el area del circulo
     */
    public double area() {
    	double a = PI * (radio*radio) / 100;
    	return a;
    }
    /**
     * Metodo que identifica si dos circulos dados son iguales.
     * @param circulo
     * @return true si son iguales o false en otro caso
     */
    public boolean esIgualA(Object circulo) {
    	if(this.color == ((Circulo)circulo).getColor() && this.radio == ((Circulo)circulo).getRadio()) {
            return true;
    	} else {
            return false;
    	}
    }
    /**
     * Metodo que dispone los atributos de un circulo con un formato especifico.
     * @return cadena con el formato especificado
     */
   public String toString() {
    	return color + "," + "p" + "," + radio;
    }

    // Getters y Setters
    /**
     * Metodo getter del circulo relativo al atributo color.
     * @return el color del circulo 
     */
    public Color getColor() {
    	return this.color;
    }
    /**
     * Metodo getter del circulo relativo al atributo radio.
     * @return el radio del circulo 
     */
    public int getRadio() {
    	return this.radio;
    }
    /**
     * Metodo setter del circulo relativo al atributo color.
     * @param color 
     */
    public void setColor(Color color) {
    	this.color = color;
    }
    /**
     * Metodo setter del circulo relativo al atributo radio.
     * @param radio 
     */
    public void setRadio(int radio) {
    	this.radio = radio;
    }
}
