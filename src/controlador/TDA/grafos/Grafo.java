package controlador.TDA.grafos;

import controlador.TDA.Listas.LinkedList;
import java.util.HashMap;

/**
 *
 * @author alexg
 */
public abstract class Grafo {

    public abstract Integer nro_vertices();

    public abstract Integer nro_aristas();

    public abstract Boolean existe_arista(Integer a, Integer b) throws Exception;

    public abstract Double peso_arista(Integer a, Integer b) throws Exception;

    public abstract void insertar(Integer a, Integer b) throws Exception;

    public abstract void insertar(Integer a, Integer b, Double peso) throws Exception;

    public abstract LinkedList<Adyacencia> adyacentes(Integer a);

    @Override
    public String toString() {
        StringBuilder grafo = new StringBuilder("GRAFOS \n");
        try {
            for (int i = 1; i <= nro_vertices(); i++) {
                grafo.append("Vertice ").append(String.valueOf(i)).append("\n");
                if (!adyacentes(i).isEmpty()) {
                    Adyacencia[] lista = adyacentes(i).toArray();
                    for (int j = 0; j < lista.length; j++) {
                        Adyacencia a = lista[j];
                        grafo.append("Adyacente ").append(a.getD().toString()).append("\n");
                    }
                }

            }
        } catch (Exception e) {
        }
        return super.toString();
    }

    protected Boolean esta_conectado() {
        Boolean band = true;
        try {
            for (int i = 0; i < nro_vertices(); i++) {
                if (adyacentes(i).isEmpty()) {
                    band = false;
                    break;
                }
            }
        } catch (Exception e) {
        }
        return band;
    }

    private Boolean estaCamino(LinkedList<Integer> lista, Integer vertice) throws Exception {
        Boolean band = false;
        for (int i = 0; i < lista.getSize(); i++) {
            if (lista.get(i).intValue() == vertice.intValue()) {
                band = true;
                break;
            }
        }
        return band;
    }

    public HashMap<String, Object> dijkstra(Integer o, Integer d) throws Exception {
        HashMap<String, Object> sendero = new HashMap<>();
        Integer V = nro_vertices();
        Double[] distancia = new Double[V + 1];
        Boolean[] visitado = new Boolean[V + 1];
        HashMap<Integer, Integer> predecesores = new HashMap<>();

        for (int i = 1; i <= V; i++) {
            distancia[i] = Double.MAX_VALUE;
            visitado[i] = false;
        }

        distancia[o] = 0.0;

        for (int i = 1; i <= V - 1; i++) {
            Double min = Double.MAX_VALUE;
            Integer min_index = -1;

            for (int v = 1; v <= V; v++) {
                if (!visitado[v] && distancia[v] <= min) {
                    min = distancia[v];
                    min_index = v;
                }
            }
            visitado[min_index] = true;

            for (int j = 1; j <= V; j++) {
                if (!visitado[j] && existe_arista(min_index, j) && distancia[min_index] != Double.MAX_VALUE
                        && distancia[min_index] + peso_arista(min_index, j) < distancia[j]) {
                    distancia[j] = distancia[min_index] + peso_arista(min_index, j);
                    predecesores.put(j, min_index);
                }
            }
        }

        LinkedList<Integer> camino = new LinkedList<>();
        Integer destinoActual = d;
        while (destinoActual != null) {
            camino.add(destinoActual);
            destinoActual = predecesores.get(destinoActual);
        }

        sendero.put("camino", camino);
        sendero.put("distancias", distancia);

        return sendero;
    }

    public HashMap<String, Object> Floyd(Integer o, Integer d) throws Exception {
        HashMap<String, Object> sendero = new HashMap<>();
        Integer V = nro_vertices();
        Double[][] distancias = new Double[V + 1][V + 1];
        Integer[][] predecesores = new Integer[V + 1][V + 1];

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) {
                    distancias[i][j] = 0.0;
                } else if (existe_arista(i, j)) {
                    distancias[i][j] = peso_arista(i, j);
                    predecesores[i][j] = i;
                } else {
                    distancias[i][j] = Double.MAX_VALUE;
                    predecesores[i][j] = null;
                }
            }
        }

        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (distancias[i][k] != Double.MAX_VALUE && distancias[k][j] != Double.MAX_VALUE
                            && distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        predecesores[i][j] = predecesores[k][j];
                    }
                }
            }
        }

        LinkedList<Integer> camino = new LinkedList<>();
        Integer u = d;
        while (u != o && predecesores[o][u] != null) {
            camino.add(u);
            u = predecesores[o][u];
        }
        camino.add(o);

        sendero.put("camino", camino);
        sendero.put("distancias", distancias);

        return sendero;
    }

}
