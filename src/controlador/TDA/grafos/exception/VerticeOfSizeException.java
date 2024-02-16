
package controlador.TDA.grafos.exception;

/**
 *
 * @author alexg
 */
public class VerticeOfSizeException extends Exception {

    public VerticeOfSizeException() {
        super("Fuera de rango");
    }
    
      public VerticeOfSizeException(String msg) {
        super(msg);
    }  
}
