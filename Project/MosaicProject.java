package Project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
/** 
 * Clase que contiene el metodo main del programa.
 * @author Clara Beneitez
 * @version 1.0, 5/7/2021
 */
public class MosaicProject {
    /**
     * Metodo main del programa.
     * @param args 
     */
    public static void main(String[] args ){
        Mosaico mosaico=null;
        String ficheroInstrucciones = args[0];
        Scanner entrada = null;
        String instruccion=null;
            try {
                entrada = new Scanner(new FileInputStream("Project/"+ficheroInstrucciones));
            } catch (FileNotFoundException e) {
                System.exit(-1);
            }
        while(entrada.hasNextLine()) {
            instruccion=(entrada.nextLine()).trim();
            if (instruccion.startsWith("ReadMosaic")){ //Lectura del mosaico
                String ficheroMosaico=instruccion.substring(instruccion.indexOf(" ")+1,instruccion.length());
                mosaico = new Mosaico("Project/"+ficheroMosaico); 
            }
            if (instruccion.startsWith("CreateRegion")){ //Creacion de una region
                String region[]=(instruccion.substring(instruccion.indexOf(" ")+1,instruccion.length())).split(",");
                mosaico.anadirRegion(mosaico.new RegionRectangular(mosaico,region[0],Integer.parseInt(region[1]),
                    Integer.parseInt(region[2]),Integer.parseInt(region[3]),Integer.parseInt(region[4])));
            }
            if(instruccion.startsWith("ChangeLuminosity")){ //Cambio de luminosidad
                if(instruccion.startsWith("ChangeLuminosityMosaic")){
                    String luminosity=instruccion.substring(instruccion.indexOf(" ")+1,instruccion.length());
                    mosaico.changeLuminosity(Integer.parseInt(luminosity));
                }
                if(instruccion.startsWith("ChangeLuminosityRegion")){
                    String luminosity[]=(instruccion.substring(instruccion.indexOf(" ")+1,instruccion.length())).split(",");
                    Mosaico.RegionRectangular region=mosaico.getRegion(luminosity[1]);
                    region.changeLuminosity(Integer.parseInt(luminosity[0]));
                }
                if(instruccion.startsWith("ChangeLuminosityTile")){
                    String luminosity[]=(instruccion.substring(instruccion.indexOf(" ")+1,instruccion.length())).split(",");
                    Tesela tesela=mosaico.getTesela(new Coordenada(Integer.parseInt(luminosity[1]),Integer.parseInt(luminosity[2])));
                    tesela.changeLuminosity(Integer.parseInt(luminosity[0]));
                }
            }
            if(instruccion.startsWith("ChangeStatus")){ //Cambio de estado
                if(instruccion.startsWith("ChangeStatusMosaic")){
                    String status=instruccion.substring(instruccion.indexOf(" ")+1,instruccion.length());
                    for(Coordenada c : (mosaico.getMapaTeselas()).keySet()){
                        (mosaico.getTesela(c)).setEstado(Integer.parseInt(status));
                    }
                }
                if(instruccion.startsWith("ChangeStatusRegion")){
                    String status[]=(instruccion.substring(instruccion.indexOf(" ")+1,instruccion.length())).split(",");
                    Mosaico.RegionRectangular region=mosaico.getRegion(status[1]);
                    for(Coordenada c : region.getCoordenadas()){
                        (mosaico.getTesela(c)).setEstado(Integer.parseInt(status[0]));
                    }
                }
                if(instruccion.startsWith("ChangeStatusTile")){
                    String status[]=(instruccion.substring(instruccion.indexOf(" ")+1,instruccion.length())).split(",");
                    Tesela tesela=mosaico.getTesela(new Coordenada(Integer.parseInt(status[1]),Integer.parseInt(status[2])));
                    tesela.setEstado(Integer.parseInt(status[0]));
                }
            }
            if(instruccion.startsWith("SortRegionsByArea")){ //Ordenacion de regiones
                String ficheroRegiones=instruccion.substring(instruccion.indexOf(" ")+1,instruccion.length());
                mosaico.ordenarRegionesXAreaAsc();
                mosaico.salvarAFicheroRegiones("Project/"+ficheroRegiones);
            }
            if(instruccion.startsWith("SaveMosaic")){ //Escritura del mosaico
                String ficheroSalida=instruccion.substring(instruccion.indexOf(" ")+1,instruccion.length());
                mosaico.salvarAFichero("Project/"+ficheroSalida);
            }
        }
    }
}