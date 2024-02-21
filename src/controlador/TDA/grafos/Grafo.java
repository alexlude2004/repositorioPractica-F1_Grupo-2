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

        int i = 1;
        while (i <= V) {
            distancia[i] = Double.MAX_VALUE;
            visitado[i] = false;
            i++;
        }

        distancia[o] = 0.0;

        i = 1;
        while (i <= V - 1) {
            Double min = Double.MAX_VALUE;
            Integer min_index = -1;

            int v = 1;
            while (v <= V) {
                if (!visitado[v] && distancia[v] <= min) {
                    min = distancia[v];
                    min_index = v;
                }
                v++;
            }
            visitado[min_index] = true;

            int j = 1;
            while (j <= V) {
                boolean nodoNoVisitado = !visitado[j];
                boolean existeArista = existe_arista(min_index, j);
                boolean distanciaValida = distancia[min_index] != Double.MAX_VALUE;
                boolean esCaminoMasCorto = distancia[min_index] + peso_arista(min_index, j) < distancia[j];

                if (nodoNoVisitado && existeArista && distanciaValida && esCaminoMasCorto) {
                    distancia[j] = distancia[min_index] + peso_arista(min_index, j);
                    predecesores.put(j, min_index);
                }

                j++;
            }
            i++;
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

        int i = 1;
        while (i <= V) {
            int j = 1;
            while (j <= V) {
                if (i == j) {
                    distancias[i][j] = 0.0;
                } else if (existe_arista(i, j)) {
                    distancias[i][j] = peso_arista(i, j);
                    predecesores[i][j] = i;
                } else {
                    distancias[i][j] = Double.MAX_VALUE;
                    predecesores[i][j] = null;
                }
                j++;
            }
            i++;
        }

        int k = 1;
        while (k <= V) {
            i = 1;
            while (i <= V) {
                int j = 1;
                while (j <= V) {
                    boolean hayCaminoHastaK = distancias[i][k] != Double.MAX_VALUE;
                    boolean hayCaminoDesdeK = distancias[k][j] != Double.MAX_VALUE;
                    boolean esCaminoMasCorto = distancias[i][k] + distancias[k][j] < distancias[i][j];
                    if (hayCaminoHastaK && hayCaminoDesdeK && esCaminoMasCorto) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                        predecesores[i][j] = predecesores[k][j];
                    }
                    j++;
                }
                i++;
            }
            k++;
        }

        LinkedList<Integer> camino = new LinkedList<>();
        Integer u = d;
        while (u != null) {
            camino.add(u);
            u = predecesores[o][u];
        }

        sendero.put("camino", camino);
        sendero.put("distancias", distancias);

        return sendero;
    }

    public boolean recorridoAnchura() throws Exception {
        int V = nro_vertices();
        boolean[] visitados = new boolean[V + 1];
        LinkedList<Integer> lista = new LinkedList<>();

        lista.add(1);
        visitados[1] = true;

        while (!lista.isEmpty()) {
            Integer actual = lista.deleteFirst();
            Adyacencia[] adyacentes = adyacentes(actual).toArray();

            for (Adyacencia adyacencia : adyacentes) {
                Integer destino = adyacencia.getD();

                if (!visitados[destino]) {
                    lista.add(destino);
                    visitados[destino] = true;
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (!visitados[i]) {
                return false;
            }
        }
        return true;
    }

    public Boolean recorridoProfundidad() throws Exception {
        int V = nro_vertices();
        boolean[] visitados = new boolean[V + 1];
        LinkedList<Integer> lista = new LinkedList<>();

        lista.add(1);

        while (!lista.isEmpty()) {
            Integer actual = lista.deleteFirst();
            if (!visitados[actual]) {
                visitados[actual] = true;
                Adyacencia[] adyacentes = adyacentes(actual).toArray();

                for (Adyacencia adyacencia : adyacentes) {
                    Integer destino = adyacencia.getD();
                    lista.add(destino);
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if (!visitados[i]) {
                return false;
            }
        }
        return true;
    }

}
