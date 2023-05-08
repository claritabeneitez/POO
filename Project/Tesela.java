package Project;
/** 
 * Clase que modela las teselas de un mosaico.
 * @author Clara Beneitez
 * @version 1.0, 5/7/2021
 */
public class Tesela implements Luminosity{
    // Atributos
    private Color color;
    private Figura figura;
    private String posicion;
    private int w;
    private int h;
    private int estado;
    private int luminosityChange;

    // Atributos estaticos
    private static int wTesela;
    private static int hTesela;
    public static final String R="R"; 
    public static final String U="U"; 
    public static final String C="C"; 
    public static final String D="D"; 
    public static final String L="L"; 
    boolean conCirculo=false;
    boolean conRectangulo=false;
    boolean conTriangulo=false;

    // Constructores
    /**
     * Metodo constructor que crea una tesela a partir de un color y un estado.
     * @param color
     * @param estado 
     */
    Tesela (Color color,int estado){
        this.color=color;
        this.estado=estado;
        this.luminosityChange=0;
    }
    /**
     * Metodo constructor que crea una tesela a partir de una figura, un color, una posicion y un estado.
     * @param figura
     * @param color
     * @param posicion
     * @param estado 
     */
    Tesela (Figura figura,Color color,String posicion,int estado){
        this.figura=figura;
        if(this.figura instanceof Circulo){
            conCirculo=true;
        }
        if(this.figura instanceof Rectangulo){
            conRectangulo=true;
        }
        if(this.figura instanceof Triangulo){
            conTriangulo=true;
        }
        this.color=color;
        this.estado=estado;
        this.luminosityChange=0;
        if (posicion.contains(R)||posicion.contains(U)||posicion.contains(C)||
            posicion.contains(D)||posicion.contains(L)) {
            this.posicion=posicion;
        }else {
            this.posicion=C;
        }
    }

    // Metodos no estaticos o de Instancia
    public boolean tieneFigura(Figura figura) {
        if (this.figura == null){
            return false;
        }else{ 
            return true;
        }
    }
    /**
     * Metodo que identifica si dos teselas dadas son iguales.
     * @param tesela
     * @return true si son iguales o false en otro caso
     */
    public boolean esIgualA(Tesela tesela) {
        boolean a=false;
        if (this.color.esIgualA(tesela.getColor())) {
            if (this.tieneFigura(figura)==tesela.tieneFigura(figura)) {
                if (this.figura.esIgualA(tesela.figura)) {
                    if(this.figura==null){
                        a=true;
                    }
                    a=this.posicion.equals(tesela.posicion);
                }
            }
        }
        return a;
    }
    /**
     * Metodo que cambia la luminosidad de una de las teselas.
     * @param value El valor que se suma a la luminosidad
     */
    public void changeLuminosity(int value){
        luminosityChange=luminosityChange+value;
    }
    /**
     * Metodo que rota el color si alguno de sus componentes llega al maximo.
     * @param color
     * @param value
     * @return el color rotado
     */
    public int rotacionColor (int color,int value){
        color=color+value;
        while(color>255 || color <0){
            if(color>255){
                color=color-256;
            }
            if(color<0){
                color=color+256;
            }  
        }
        return color;
    }
    /**
     * Metodo que dispone los atributos de una tesela con un formato especifico cuando su estado es 0.
     * @return cadena con el formato especificado
     */
    public String toStringApagada(){
        if (conCirculo) {
            String a = "{R0G0B0,CIR:{R0G0B0,p," + ((Circulo)figura).getRadio() + "}}";
            return a.replace("p", posicion);
        }
        if (conRectangulo) {
            String b = "{R0G0B0,REC:{R0G0B0,p," + ((Rectangulo)figura).getBase() + "," + ((Rectangulo)figura).getAltura() + "}}";
            return b.replace("p",posicion);
        }
        if (conTriangulo){
            String c = "{R0G0B0,TRI:{R0G0B0,p," + ((Triangulo)figura).getAncho() + "," + ((Triangulo)figura).getAltura() + "}}";
            return c.replace("p",posicion);
        }
        return "{R0G0B0}";
    }
    /**
     * Metodo que dispone los atributos de una tesela con un formato especifico cuando su estado es 1.
     * @return cadena con el formato especificado
     */
    public String toString(){
        if (conCirculo) {
            String a = "{R" + rotacionColor(color.getR(),luminosityChange) 
                    + "G" + rotacionColor(color.getG(),luminosityChange) 
                    + "B" + rotacionColor(color.getB(),luminosityChange)
                    + ",CIR:{R" + rotacionColor(((Circulo)figura).getColor().getR(),luminosityChange) 
                    + "G" + rotacionColor(((Circulo)figura).getColor().getG(),luminosityChange) 
                    + "B" + rotacionColor(((Circulo)figura).getColor().getB(),luminosityChange)
                    + ",p," + ((Circulo)figura).getRadio() + "}}";
            return a.replace("p", posicion);
        }
        if (conRectangulo) {
            String b = "{R" + rotacionColor(color.getR(),luminosityChange) 
                    + "G" + rotacionColor(color.getG(),luminosityChange) 
                    + "B" + rotacionColor(color.getB(),luminosityChange)
                    + ",REC:{R" + rotacionColor(((Rectangulo)figura).getColor().getR(),luminosityChange) 
                    + "G" + rotacionColor(((Rectangulo)figura).getColor().getG(),luminosityChange) 
                    + "B" + rotacionColor(((Rectangulo)figura).getColor().getB(),luminosityChange)
                    + ",p," + ((Rectangulo)figura).getBase() + "," + ((Rectangulo)figura).getAltura() + "}}";
            return b.replace("p",posicion);
        }
        if (conTriangulo){
            String c = "{R" + rotacionColor(color.getR(),luminosityChange) 
                    + "G" + rotacionColor(color.getG(),luminosityChange) 
                    + "B" + rotacionColor(color.getB(),luminosityChange)
                    + ",TRI:{R" + rotacionColor(((Triangulo)figura).getColor().getR(),luminosityChange) 
                    + "G" + rotacionColor(((Triangulo)figura).getColor().getG(),luminosityChange) 
                    + "B" + rotacionColor(((Triangulo)figura).getColor().getB(),luminosityChange)
                    + ",p," + ((Triangulo)figura).getAncho() + "," + ((Triangulo)figura).getAltura() + "}}";
            return c.replace("p",posicion);
        }
        return "{R" + rotacionColor(color.getR(),luminosityChange) 
            + "G" + rotacionColor(color.getG(),luminosityChange) 
            + "B" + rotacionColor(color.getB(),luminosityChange) + "}";
    }
    /**
     * Metodo que dispone los atributos de una tesela con un formato especifico cuando su estado es 2.
     * @return cadena con el formato especificado
     */
    public String toStringFiguraApagada(){
        if (conCirculo) {
            String a = "{R" + rotacionColor(color.getR(),luminosityChange) 
                    + "G" + rotacionColor(color.getG(),luminosityChange) 
                    + "B" + rotacionColor(color.getB(),luminosityChange)
                    + ",CIR:{R0G0B0,p," + ((Circulo)figura).getRadio() + "}}";
            return a.replace("p", posicion);
        }
        if (conRectangulo) {
            String b = "{R" + rotacionColor(color.getR(),luminosityChange) 
                    + "G" + rotacionColor(color.getG(),luminosityChange) 
                    + "B" + rotacionColor(color.getB(),luminosityChange)
                    + ",REC:{R0G0B0,p," + ((Rectangulo)figura).getBase() + "," + ((Rectangulo)figura).getAltura() + "}}";
            return b.replace("p",posicion);
        } 
        if (conTriangulo){
            String c = "{R" + rotacionColor(color.getR(),luminosityChange) 
                    + "G" + rotacionColor(color.getG(),luminosityChange) 
                    + "B" + rotacionColor(color.getB(),luminosityChange)
                    + ",TRI:{R0G0B0,p," + ((Triangulo)figura).getAncho() + "," + ((Triangulo)figura).getAltura() + "}}";
            return c.replace("p",posicion);
        }
        return "{R" + rotacionColor(color.getR(),luminosityChange) 
                + "G" + rotacionColor(color.getG(),luminosityChange) 
                + "B" + rotacionColor(color.getB(),luminosityChange) + "}";
    }
    /**
     * Metodo que dispone los atributos de una tesela con un formato especifico cuando su estado es 3.
     * @return cadena con el formato especificado
     */
    public String toStringFiguraEncendida(){
        if (conCirculo) {
            String a = "{R0G0B0,CIR:{R" + rotacionColor(((Circulo)figura).getColor().getR(),luminosityChange) 
                    + "G" + rotacionColor(((Circulo)figura).getColor().getG(),luminosityChange) 
                    + "B" + rotacionColor(((Circulo)figura).getColor().getB(),luminosityChange)
                    + ",p," + ((Circulo)figura).getRadio() + "}}";
            return a.replace("p", posicion);
        }
        if (conRectangulo) {
            String b = "{R0G0B0,REC:{R" + rotacionColor(((Rectangulo)figura).getColor().getR(),luminosityChange) 
                    + "G" + rotacionColor(((Rectangulo)figura).getColor().getG(),luminosityChange) 
                    + "B" + rotacionColor(((Rectangulo)figura).getColor().getB(),luminosityChange)
                    + ",p," + ((Rectangulo)figura).getBase() + "," + ((Rectangulo)figura).getAltura() + "}}";
            return b.replace("p",posicion);
        }
        if (conTriangulo){
            String c = "{R0G0B0,TRI:{R" + rotacionColor(((Triangulo)figura).getColor().getR(),luminosityChange) 
                    + "G" + rotacionColor(((Triangulo)figura).getColor().getG(),luminosityChange) 
                    + "B" + rotacionColor(((Triangulo)figura).getColor().getB(),luminosityChange)
                    + ",p," + ((Triangulo)figura).getAncho() + "," + ((Triangulo)figura).getAltura() + "}}";
            return c.replace("p",posicion);
        }
        return "{R0G0B0}";
        
    }

    // 	Getters y Setters
    /**
     * Metodo getter de la tesela relativo al atributo color.
     * @return el color de la tesela 
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * Metodo getter de la tesela relativo al atributo figura.
     * @return la figura de la tesela 
     */
    public Figura getFigura() {
        return this.figura;
    }
    /**
     * Metodo getter de la tesela relativo al atributo posicion.
     * @return la posicion de la tesela 
     */
    public String getPosicion() {
        return this.posicion;
    }
    /**
     * Metodo getter de la tesela relativo al atributo ancho.
     * @return el ancho de la tesela 
     */
    public int getAncho() {
        return this.w;
    }
    /**
     * Metodo getter de la tesela relativo al atributo alto.
     * @return el alto de la tesela 
     */
    public int getAlto() {
        return this.h;
    }
    /**
     * Metodo getter de la tesela relativo al atributo estado.
     * @return el estado de la tesela 
     */
    public int getEstado(){
        return this.estado;
    }
    /**
     * Metodo getter de la tesela relativo al atributo luminosityChange.
     * @return la luminosidad de la tesela 
     */
    public int getLuminosityChange(){
        return this.luminosityChange;
    }
    /**
     * Metodo setter de la tesela relativo al atributo color.
     * @param color 
     */
    public void setColor(Color color) {
        this.color = color;
    }
    /**
     * Metodo setter de la tesela relativo al atributo figura.
     * @param figura 
     */
    public void setFigura(Figura figura) {
    	this.figura = figura;
    }
    /**
     * Metodo setter de la tesela relativo al atributo posicion.
     * @param posicion 
     */
    public void setPosicion(String posicion) {
    	this.posicion = posicion;
    }
    /**
     * Metodo setter de la tesela relativo al atributo luminosityChange.
     * @param luminosityChange 
     */
    public void setLuminosityChange(int luminosityChange){
        this.luminosityChange=luminosityChange;
    }
    /**
     * Metodo setter de la tesela relativo al atributo estado.
     * @param estado 
     */
    public void setEstado (int estado){
        this.estado=estado;
    }
}
