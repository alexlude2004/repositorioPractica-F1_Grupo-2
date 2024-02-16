package controlador.TDA.grafos;

import controlador.TDA.Listas.LinkedList;
import controlador.TDA.grafos.exception.VerticeOfSizeException;

/**
 *
 * @author alexg
 */
public class GrafoDirigido extends Grafo {

    private Integer nro_vertices;
    private Integer nro_aristas;
    private LinkedList<Adyacencia> listaAdyacente [];

    public GrafoDirigido(Integer nro_vertices) {
        this.nro_vertices = nro_vertices;
        nro_aristas = 0;
        listaAdyacente = new LinkedList[nro_vertices + 1];
        for (int i = 1; i <= nro_vertices; i++) {
            listaAdyacente[i] = new LinkedList<>();
        }
    }
    
    public LinkedList<Adyacencia>[] getListaAdyacente() {
        return listaAdyacente;
    }
    
    public void setNro_aristas(Integer num) {
        this.nro_aristas = num;
    }
    
    @Override
    public Integer nro_vertices() {
        return this.nro_vertices;
    }

    @Override
    public Integer nro_aristas() {
        return this.nro_aristas;
    }

    @Override
    public Boolean existe_arista(Integer a, Integer b) throws Exception{
        Boolean band = false;
        if (a.intValue() <= nro_vertices.intValue() && b.intValue() <= nro_vertices.intValue()) {
            LinkedList<Adyacencia> lista = listaAdyacente[a];
            for (int i = 0; i < lista.getSize(); i++) {
                Adyacencia aux = lista.get(i);
                if (aux.getD().intValue() == b.intValue()) {
                    band = true;
                    break;
                }
            }
        } else {
            throw new VerticeOfSizeException();
        }
        return band;
    }

    @Override
    public Double peso_arista(Integer a, Integer b) throws Exception{
        Double peso = Double.NaN;
        if (existe_arista(a, b)) {
            LinkedList<Adyacencia> lista = listaAdyacente[a];
            for (int i = 0; i < lista.getSize(); i++) {
                Adyacencia aux = lista.get(i);
                if (aux.getD().intValue() == b.intValue()) {
                    peso = aux.getPeso();
                    break;
                }
            } 
        }
        return peso;
    }

    @Override
    public void insertar(Integer a, Integer b) throws Exception {
        insertar(a, b, Double.NaN);
    }

    @Override
    public void insertar(Integer a, Integer b, Double peso) throws Exception {
        if (a.intValue() <= nro_vertices.intValue() && b.intValue() <= nro_vertices.intValue()) {
            if (!existe_arista(a, b)) {
                nro_aristas++;
                Adyacencia aux = new Adyacencia();
                aux.setPeso(peso);
                aux.setD(b);
                listaAdyacente[a].add(aux);
            }
        } else {
            throw new VerticeOfSizeException();
        }    
    }

    @Override
    public LinkedList<Adyacencia> adyacentes(Integer a) {
        return listaAdyacente[a];
    }

}
