package Project;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.*;
/** 
 * Clase que modela mosaicos.
 * @author Clara Beneitez
 * @version 1.0, 5/7/2021
 */
public class Mosaico implements Luminosity{
	// Atributos
	private int wTesela;
	private int hTesela;
	private int fila=1;
	private int columna=1;
	private List<RegionRectangular> regiones;
	private Map<Coordenada,Tesela> mapaTeselas;
        /**
         * Metodo constructor que crea un mosaico a partir de las especificaciones que aparecen en un fichero.
         * @param fichero 
         */
        public Mosaico(String fichero){
            int a=0;
            Scanner entrada = null;
            try {
                entrada = new Scanner(new FileInputStream(fichero));
            } catch (FileNotFoundException e) {
                try (BufferedWriter bw=new BufferedWriter(new FileWriter ("Project/error.txt"))){
                    bw.write("FileNotFoundException");
                    bw.close();
                    System.exit(-1);
                }catch(IOException ex){
                    System.exit(-1);
                }
            }
            String linea = null;
            while (entrada.hasNextLine()) {
                linea = (entrada.nextLine()).trim();
                linea=linea.replace(" ","");
                if(!linea.startsWith("*")){
                    if(a==0){
                        String[] partes= linea.split(",");
                        this.fila=Integer.parseInt (partes[0].substring(0,partes[0].indexOf("x")));
                        this.columna=Integer.parseInt (partes[0].substring(partes[0].indexOf("x")+1));
                        this.wTesela=Integer.parseInt (partes[1].substring(0,partes[1].indexOf("x")));
                        this.hTesela=Integer.parseInt (partes[1].substring(partes[1].indexOf("x")+1));
                        mapaTeselas=new HashMap<Coordenada,Tesela>();
                        regiones=new ArrayList<RegionRectangular>();
                        this.inicializar();
                        a=1;
                    } else {
                        int F=Integer.parseInt (linea.substring(linea.indexOf("(")+1,linea.indexOf(",")));
                        int C=Integer.parseInt (linea.substring(linea.indexOf(",")+1,linea.indexOf(")")));
                        if(F>fila||C>columna||F<1||C<1){
                            try (BufferedWriter bw=new BufferedWriter(new FileWriter ("Project/error.txt"))){
                                bw.write("TileOutOfBoundsException");
                                bw.close();
                                System.exit(-1);
                            }catch(IOException e){
                                System.exit(-1);
                            }
                        }
                        int estado=Integer.parseInt (linea.substring(linea.indexOf(":")+1,linea.indexOf("{")));
                        String linea2=(linea.substring(linea.indexOf("{")+1,linea.length())).toUpperCase();
                        if(!linea2.contains(",")){ 
                            Color c1=leerColor(linea2.substring(0,linea2.indexOf("}")));
                            for(Coordenada c:this.mapaTeselas.keySet()){
                                if(c.esIgualA(new Coordenada(F,C))){
                                    mapaTeselas.replace(c,new Tesela(c1,estado));
                                }
                            } 
                        } else {
                            Color c2=leerColor(linea2.substring(0,linea2.indexOf(",")));
                            String figura=linea2.substring(linea2.indexOf(",")+1,linea2.indexOf(":"));
                            String linea3=linea2.substring(linea2.indexOf("{")+1,linea2.indexOf("}"));
                            String[] partes2= linea3.split(",");
                            if (figura.equals("CIR")){
                                Color c3=leerColor(partes2[0]);
                                Figura cir=new Circulo (Integer.parseInt(partes2[2]),c3);
                                for(Coordenada c:this.mapaTeselas.keySet()){
                                    if(c.esIgualA(new Coordenada(F,C))){
                                        mapaTeselas.replace(c,new Tesela(cir,c2,partes2[1],estado));
                                    }
                                }
                            }
                            if (figura.equals("REC")){
                                Color c3=leerColor(partes2[0]);
                                Figura rec=new Rectangulo (Integer.parseInt(partes2[2]),Integer.parseInt(partes2[3]),c3);
                                for(Coordenada c:this.mapaTeselas.keySet()){
                                    if(c.esIgualA(new Coordenada(F,C))){
                                        mapaTeselas.replace(c,new Tesela(rec,c2,partes2[1],estado));
                                    }
                                }
                            }
                            if (figura.equals("TRI")){
                                Color c3=leerColor(partes2[0]);
                                Figura tri=new Triangulo (Integer.parseInt(partes2[2]),Integer.parseInt(partes2[3]),c3);
                                for(Coordenada c:this.mapaTeselas.keySet()){
                                    if(c.esIgualA(new Coordenada(F,C))){
                                        mapaTeselas.replace(c,new Tesela(tri,c2,partes2[1],estado));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            entrada.close();
	}
	
        private Color leerColor(String color){
            int R = Integer.parseInt(color.substring(color.indexOf("R") + 1, color.indexOf("G")));
            int G = Integer.parseInt(color.substring(color.indexOf("G") + 1, color.indexOf("B")));
            int B = Integer.parseInt(color.substring(color.indexOf("B") + 1, color.length()));
            Color c = new Color(R,G,B);
            return c;
        }
        /**
         * Metodo que inicializa las teselas del mosaico con color blanco y sin figura.
         */
        private void inicializar() {
            for (int i = 1; i <= fila; i++) {
                for (int j = 1; j <= columna; j++) {
                    mapaTeselas.put(new Coordenada(i,j),new Tesela(Color.BLANCO(),1));
                }
            }
        }
        /**
         * Clase que modela regiones rectangulares del mosaico.
         * @author Clara Beneitez
         * @version 1.0, 5/7/2021
         */
        public class RegionRectangular implements Luminosity{
            // Atributos
            private Mosaico mosaico;
            private String nombre;
            private Coordenada origen;
            private int w;
            private int h;
            private List<Coordenada> coordenadas;
            
            // Constructor
            /**
             * Metodo constructor que crea una region rectangular a partir de un mosaico, un nombre, un fila de origen, una columna de origen, un ancho y un alto.
             * @param mosaico
             * @param nombre
             * @param f0
             * @param c0
             * @param w
             * @param h 
             */
            public RegionRectangular(Mosaico mosaico,String nombre,int f0,int c0,int w,int h){
                this.mosaico=mosaico;
                if (nombre.length()>30) {
			this.nombre= nombre.substring(0,29);
		}else {
			this.nombre=nombre;
		}
                coordenadas=new ArrayList<Coordenada>();
                if(f0<=mosaico.fila && c0<=mosaico.columna){
                    this.origen=new Coordenada(f0,c0);
                    this.w=w;
                    this.h=h;
                    for(int i=f0;i<=((f0-1)+w);i++){
                        if(i<=mosaico.fila){
                            for(int j=c0;j<=((c0-1)+h);j++){
                                if(j<=mosaico.columna){
                                    coordenadas.add(new Coordenada(i,j));
                                }
                            }
                        }
                    }
                }else{
                    this.origen=new Coordenada(1,1);
                    this.w=0;
                    this.h=0;
                }
            }
            
            //Metodos
            /**
             * Metodo que desordena las coordenadas de la coleccion coordenadas de la region.
             */
            public void desordenar(){
                Collections.shuffle(coordenadas);
            }
            /**
             * Metodo que ordena las coordenadas de la coleccion coordenadas de la region.
             */
            public void ordenarXCoordenadaAsc(){
                Collections.sort(coordenadas); 
            }
            /**
             * Metodo que calcula el area de la region rectangular.
             * @return el area de la region rectangular
             */
            public int Area(){
                return coordenadas.size();
            }
            /**
            * Metodo que cambia la luminosidad de todas las teselas que pertenezcan a la region rectangular.
            * @param value El valor que se suma a la luminosidad
            */
            public void changeLuminosity(int value){
                for(Coordenada cR:coordenadas){
                    for(Coordenada cT: mapaTeselas.keySet()){
                        if(cR.esIgualA(cT)){
                            (mapaTeselas.get(cT)).changeLuminosity(value);
                        }
                    }
                }
            }
            /**
            * Metodo que dispone los atributos de una region rectangular con un formato especifico.
            * @return cadena con el formato especificado
            */
            public String toString () {
                return nombre + ":" + origen.toString() + "," + w + "-" + h + ":" + coordenadas;
            }
            
            // Getters y setters
            /**
            * Metodo getter de la region rectangular relativo al atributo coordenadas.
            * @return la coleccion coordenadas de la region
            */
            public Collection<Coordenada> getCoordenadas(){
                return this.coordenadas;
            }
            /**
            * Metodo getter de la region rectangular relativo al atributo mosaico.
            * @return el mosaico asociado a la region
            */
            public Mosaico getMosaico(){
                return mosaico;
            }
            /**
             * Metodo setter de la region rectangular relativo al atributo mosaico.
             * @param mosaico 
             */
            public void setMosaico(Mosaico mosaico){
                this.mosaico=mosaico;
            }
            /**
            * Metodo getter de la region rectangular relativo al atributo nombre.
            * @return el nombre de la region
            */
            public String getNombre() {
		return nombre;
            }
            /**
             * Metodo setter de la region rectangular relativo al atributo nombre.
             * @param nombre 
             */
            public void setNombre(String nombre) {
                this.nombre = nombre;
            }
            /**
            * Metodo getter de la region rectangular relativo al atributo origen.
            * @return la coordenada de origen de la region
            */
            public Coordenada getOrigen() {
                return origen;
            }
            /**
             * Metodo setter de la region rectangular relativo al atributo origen.
             * @param origen 
             */
            public void setOrigen(Coordenada origen) {
                this.origen = origen;
            }
            /**
            * Metodo getter de la region rectangular relativo al atributo ancho.
            * @return el ancho de la region
            */
            public int getW() {
                return w;
            }
            /**
             * Metodo setter de la region rectangular relativo al atributo ancho.
             * @param w 
             */
            public void setW(int w) {
                this.w = w;
            }
            /**
            * Metodo getter de la region rectangular relativo al atributo alto.
            * @return el alto de la region
            */
            public int getH() {
                return h;
            }
            /**
             * Metodo setter de la region rectangular relativo al atributo alto.
             * @param h 
             */
            public void setH(int h) {
                this.h = h;
            }
        }
        /**
         * Clase que compara las regiones rectangulares por area y nombre para ordenarlas.
         * @author Clara Beneitez
         * @version 1.0, 5/7/2021
         */
        public class CompararArea implements Comparator<RegionRectangular>{
            /**
             * Metodo que compara las regiones rectangulares por area y nombre para ordenarlas.
             * @param r1
             * @param r2
             * @return 
             */
            public int compare(RegionRectangular r1, RegionRectangular r2) {
                int resultado=0;
                if (r1.Area()>r2.Area()) {
                    resultado=1;
                }
                if (r1.Area()<r2.Area()) {
                    resultado=-1;
                }
                if (r1.Area()==r2.Area()) {
                    resultado=(r1.getNombre()).compareTo(r2.getNombre());
                }
                return resultado;
            }
        }
	/**
         * Metodo que guarda una cadena en un fichero.
         * @param fichero 
         */
	public void salvarAFichero(String fichero){
            try (BufferedWriter bw=new BufferedWriter(new FileWriter (fichero))){
                bw.write(this.toString());
                bw.close();
            }catch(FileNotFoundException e){
                try (BufferedWriter bw2=new BufferedWriter(new FileWriter ("Project/error.txt"))){
                    bw2.write("FileNotFoundException");
                    bw2.close();
                    System.exit(-1);
                }catch(IOException ex){
                    System.exit(-1);
                }
            }catch(IOException exc){System.out.print(-1);}
        }
        /**
         * Metodo que guarda una cadena en un fichero.
         * @param fichero 
         */
        public void salvarAFicheroRegiones(String fichero){
            try (BufferedWriter bw=new BufferedWriter(new FileWriter (fichero))){
                bw.write(this.toStringRegiones());
                bw.close();
            }catch(FileNotFoundException e){
                try (BufferedWriter bw2=new BufferedWriter(new FileWriter ("Project/error.txt"))){
                    bw2.write("FileNotFoundException");
                    bw2.close();
                    System.exit(-1);
                }catch(IOException ex){
                    System.exit(-1);
                }
            }catch(IOException exc){System.out.print(-1);}
        }
        /**
         * Metodo para anadir una region rectangular a la coleccion de regiones del mosaico.
         * @param r 
         */
        public void anadirRegion(RegionRectangular r) {
            regiones.add(r);	
	}
	/**
         * Metodo que ordena las regiones rectangulares de la coleccion de regiones del mosaico segun su area y su nombre.
         */
	public void ordenarRegionesXAreaAsc() {
            if(regiones!=null){
                Collections.sort(regiones,new CompararArea());
            }
            for(RegionRectangular r : regiones) {
                r.ordenarXCoordenadaAsc();
            }
	}
        /**
        * Metodo que cambia la luminosidad de todas las teselas del mosaico.
        * @param value El valor que se suma a la luminosidad
        */
        public void changeLuminosity(int value){
            for(Coordenada c : mapaTeselas.keySet()){
                (this.getTesela(c)).changeLuminosity(value);
            }
        }
	/**
        * Metodo que dispone las regiones rectangulares de la coleccion de regiones de un mosaico con un formato especifico.
        * @return cadena con el formato especificado
        */
	public String toStringRegiones() {
            String a = "";
            for(RegionRectangular r : regiones) {
                a = a + r.toString() + "\n";
            }
            return a;
	}
	/**
        * Metodo que dispone los atributos de un mosaico con un formato especifico.
        * @return cadena con el formato especificado
        */
	public String toString() {
            String primero=Integer.toString (fila)+"x"+Integer.toString (columna)+","+Integer.toString (wTesela)+"x"+Integer.toString (hTesela)+"\n";
            List<Coordenada> keys = new LinkedList<Coordenada>(mapaTeselas.keySet());
            Collections.sort(keys);
            String segundo="";
            for (Coordenada c : keys){
                if((mapaTeselas.get(c)).getEstado() == 0){
                    segundo =  segundo + "(" + c.getFila() + "," + c.getColumna() + "):" + 
                    Integer.toString((mapaTeselas.get(c)).getEstado()) + (mapaTeselas.get(c)).toStringApagada()+"\n";
                }
                if((mapaTeselas.get(c)).getEstado() == 1){
                    segundo =  segundo + "(" + c.getFila() + "," + c.getColumna() + "):" + 
                    Integer.toString((mapaTeselas.get(c)).getEstado()) + (mapaTeselas.get(c)).toString()+"\n";
                }
                if((mapaTeselas.get(c)).getEstado() == 2){
                    segundo =  segundo + "(" + c.getFila() + "," + c.getColumna() + "):" + 
                    Integer.toString((mapaTeselas.get(c)).getEstado()) + (mapaTeselas.get(c)).toStringFiguraApagada()+"\n";
                }
                if((mapaTeselas.get(c)).getEstado() == 3){
                    segundo =  segundo + "(" + c.getFila() + "," + c.getColumna() + "):" + 
                    Integer.toString((mapaTeselas.get(c)).getEstado()) + (mapaTeselas.get(c)).toStringFiguraEncendida()+"\n";
                }
            }
            return primero + segundo;
	}

	// 	Getters y Setters
        /**
         * Metodo getter del mosaico relativo a una tesela de la coleccion de teselas.
         * @param c La coordenada asociada a la tesela que queremos obtener
         * @return la tesela asociada a la coordenada
         */
	public Tesela getTesela(Coordenada c) {
            for(Coordenada cT: mapaTeselas.keySet()){
                if(c.esIgualA(cT)){
                    return mapaTeselas.get(cT);
                }
            }
            return null;
	}
        /**
         * Metodo getter del mosaico relativo a una region rectangular de la coleccion de regiones.
         * @param nombre El nombre con el que se identifica la region que queremos obtener
         * @return la region asociada al nombre
         */
        public RegionRectangular getRegion (String  nombre) {
            for(RegionRectangular r : regiones) {
                if (nombre.equals(r.getNombre())){
                    return r;
                }
            }
            return null;
	}
        /**
         * Metodo getter del mosaico relativo al atributo mapaTeselas.
         * @return el mapa de teselas
         */
        public Map<Coordenada,Tesela> getMapaTeselas(){
            return this.mapaTeselas;
        }
        /**
         * Metodo getter del mosaico relativo al atributo fila.
         * @return la fila del mosaico
         */
	public int getFila() {
            return this.fila;
	}
        /**
         * Metodo getter del mosaico relativo al atributo columna.
         * @return la columna del mosaico
         */
	public int getColumna() {
            return this.columna;
	}
        /**
         * Metodo setter del mosaico relativo al atributo fila.
         * @param fila 
         */
	public void setFila(int fila) {
            this.fila=fila;
	}
        /**
         * Metodo setter del mosaico relativo al atributo columna.
         * @param columna 
         */
	public void setColumna(int columna) {
            this.columna=columna;
	}
}