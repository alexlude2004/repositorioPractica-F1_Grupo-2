package controlador.TDA.grafos;

import controlador.TDA.Listas.LinkedList;
import controlador.TDA.grafos.exception.EtiquetaException;
import java.lang.reflect.Array;
import java.util.HashMap;

/**
 *
 * @author alexg
 */
public class GrafoEtiquetadoDirigido<E> extends GrafoDirigido {

    protected E etiqueta[];
    protected HashMap<E, Integer> dicVertices;
    private Class<E> clazz;

    public GrafoEtiquetadoDirigido(Integer nro_vertices, Class<E> clazz) {
        super(nro_vertices);
        this.clazz = clazz;
        etiqueta = (E[]) Array.newInstance(clazz, nro_vertices + 1);
        dicVertices = new HashMap<>(nro_vertices);
    }

    public Boolean existeAristaE(E o, E d) throws Exception {
        if (estaEtiquetado()) {
            return existe_arista(obtenerCodigoE(o), obtenerCodigoE(d));
        } else {
            throw new EtiquetaException();
        }
    }

    public void insertarAristaE(E o, E d, Double peso) throws Exception {
        if (estaEtiquetado()) {
            insertar(obtenerCodigoE(o), obtenerCodigoE(d), peso);
        } else {
            throw new EtiquetaException();
        }
    }

    public void insertarArsitasE(E o, E d) throws Exception {
        if (estaEtiquetado()) {
            insertar(obtenerCodigoE(o), obtenerCodigoE(d), Double.NaN);
        } else {
            throw new EtiquetaException();
        }
    }

    public LinkedList<Adyacencia> adyacentesE(E o) throws Exception {
        if (estaEtiquetado()) {
            return adyacentes(obtenerCodigoE(o));
        } else {
            throw new EtiquetaException();
        }
    }

    public void etiquetarVertice(Integer vertice, E dato) {
        etiqueta[vertice] = dato;
        dicVertices.put(dato, vertice);
    }

    public Boolean estaEtiquetado() {
        Boolean band = true;
        for (int i = 1; i < etiqueta.length; i++) {
            E dato = etiqueta[i];
            if (dato == null) {
                band = false;
                break;
            }
        }
        return band;
    }

    public Integer obtenerCodigoE(E etiqueta) {
        return dicVertices.get(etiqueta);
    }

    public E obtenerEtiqueta(Integer i) {
        return etiqueta[i];
    }

    @Override
    public String toString() {
        StringBuilder grafo = new StringBuilder("GRAFOS ETIQUETADOS \n");
        try {
            for (int i = 1; i <= nro_vertices(); i++) {
                grafo.append("Vertice ").append(obtenerEtiqueta(i)).append("\n");
                if (!adyacentes(i).isEmpty()) {
                    Adyacencia[] lista = adyacentes(i).toArray();
                    for (int j = 0; j < lista.length; j++) {
                        Adyacencia a = lista[j];
                        grafo.append("Adycente ").append(obtenerEtiqueta(a.getD())).
                                append(" -Peso- ").append(a.getPeso()).append("\n");
                    }
                }

            }
        } catch (Exception e) {
        }
        return grafo.toString();
    }

}
