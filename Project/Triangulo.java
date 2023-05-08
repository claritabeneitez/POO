package Project;
/** 
 * Clase que modela triangulos.
 * @author Clara Beneitez
 * @version 1.0, 5/7/2021
 */
public class Triangulo extends Figura{

    // Atributos
    private Color color;
    private int ancho;
    private int altura;

    // Constructor
    /** 
     * Metodo constructor que crea un triangulo a partir de un ancho, una altura y un color.
     * @param ancho
     * @param altura
     * @param color 
     */
    Triangulo(int ancho, int altura, Color color) {
    	if (ancho>100) {
            this.ancho = 100;
    	}
    	else {
            this.ancho = ancho;
    	}
    	if (altura>100) {
            this.altura = 100;
    	}else {
            this.altura = altura;
    	}
    	this.color=color;
    }
    
    // Metodos no estaticos o de Instancia
    /** 
     * Metodo que calcula el area del triangulo.
     * @return el area del triangulo
     */
    public double area() {
    	double a = ancho*altura /(2*100);
    	return a;
    }
    /**
     * Metodo que identifica si dos triangulos dados son iguales.
     * @param triangulo
     * @return true si son iguales o false en otro caso
     */
    public boolean esIgualA(Object triangulo) {
    	if(this.color == ((Triangulo)triangulo).getColor() && this.ancho == ((Triangulo)triangulo).getAncho() 
            && this.altura == ((Triangulo)triangulo).getAltura()) {
            return true;
    	} else {
            return false;
    	}
    }
    /**
     * Metodo que dispone los atributos de un triangulo con un formato especifico.
     * @return cadena con el formato especificado
     */
    public String toString() {
    	return color + "," + "p" + "," + ancho + "," + altura;
    }

    // Getters y Setters
    /**
     * Metodo getter del triangulo relativo al atributo color.
     * @return el color del triangulo 
     */
    public Color getColor() {
    	return this.color;
    }
     /**
     * Metodo getter del triangulo relativo al atributo ancho.
     * @return el ancho del triangulo 
     */
    public int getAncho() {
    	return this.ancho;
    }
     /**
     * Metodo getter del triangulo relativo al atributo altura.
     * @return la altura del triangulo 
     */
    public int getAltura() {
    	return this.altura;
    }
    /**
     * Metodo setter del triangulo relativo al atributo color.
     * @param color 
     */
    public void setColor(Color color) {
    	this.color = color;
    }
    /**
     * Metodo setter del triangulo relativo al atributo ancho.
     * @param ancho 
     */
    public void setAncho(int ancho) {
    	this.ancho = ancho;
    }
    /**
     * Metodo setter del triangulo relativo al atributo altura.
     * @param altura 
     */
    public void setAltura(int altura) {
    	this.altura = altura;
    }
}
