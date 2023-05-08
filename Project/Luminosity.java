package Project;
/**
 * Interfaz que modela la luminosidad de las teselas del mosaico.
 * @author Clara Beneitez
 * @version 1.0, 5/7/2021
 */
public interface Luminosity {
    /**
     * Metodo que cambia la luminosidad de una o de cada una de las teselas.
     * @param value El valor que se suma a la luminosidad
     */
    public abstract void changeLuminosity(int value);
}
