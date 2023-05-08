package Project;
/** 
 * Clase que modela rectangulos.
 * @author Clara Beneitez
 * @version 1.0, 5/7/2021
 */
public class Rectangulo extends Figura{

    // Atributos
    private Color color;
    private int base;
    private int altura;

    // Constructor
    /** 
     * Metodo constructor que crea un rectangulo a partir de una base, una altura y un color.
     * @param base
     * @param altura
     * @param color 
     */
    Rectangulo(int base, int altura, Color color) {
    	if (base>100) {
            this.base = 100;
    	}
    	else {
            this.base = base;
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
     * Metodo que calcula el area del rectangulo.
     * @return el area del rectangulo
     */
    public double area() {
    	double a = base*altura / 100;
    	return a;
	}
    /**
     * Metodo que identifica si dos rectangulos dados son iguales.
     * @param rectangulo
     * @return true si son iguales o false en otro caso
     */
    public boolean esIgualA(Object rectangulo) {
    	if(this.color == ((Rectangulo)rectangulo).getColor() && this.base == ((Rectangulo)rectangulo).getBase() 
            && this.altura == ((Rectangulo)rectangulo).getAltura()) {
            return true;
    	} else {
            return false;
    	}
    }
    /**
     * Metodo que dispone los atributos de un rectangulo con un formato especifico.
     * @return cadena con el formato especificado
     */
    public String toString() {
    	return color + "," + "p" + "," + base + "," + altura;
    }

    // Getters y Setters
    /**
     * Metodo getter del rectangulo relativo al atributo color.
     * @return el color del rectangulo 
     */
    public Color getColor() {
    	return this.color;
    }
    /**
     * Metodo getter del rectangulo relativo al atributo base.
     * @return la base del rectangulo 
     */
    public int getBase() {
    	return this.base;
    }
    /**
     * Metodo getter del rectangulo relativo al atributo altura.
     * @return la altura del rectangulo 
     */
    public int getAltura() {
    	return this.altura;
    }
    /**
     * Metodo setter del rectangulo relativo al atributo color.
     * @param color 
     */
    public void setColor(Color color) {
    	this.color = color;
    }
    /**
     * Metodo setter del rectangulo relativo al atributo base.
     * @param base 
     */
    public void setBase(int base) {
    	this.base = base;
    }
    /**
     * Metodo setter del rectangulo relativo al atributo altura.
     * @param altura 
     */
    public void setAltura(int altura) {
    	this.altura = altura;
    }
}
