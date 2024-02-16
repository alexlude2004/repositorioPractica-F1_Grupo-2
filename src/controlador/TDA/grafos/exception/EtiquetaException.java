
package controlador.TDA.grafos.exception;

/**
 *
 * @author alexg
 */
public class EtiquetaException extends Exception {

    public EtiquetaException() {
        super("El grafo no esta etiquetado");
    }
    
      public EtiquetaException(String msg) {
        super(msg);
    }  
}
