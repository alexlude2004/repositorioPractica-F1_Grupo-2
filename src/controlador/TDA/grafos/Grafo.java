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

    public HashMap camino(Integer o, Integer d) throws Exception {
        HashMap sendero = new HashMap();
        if (esta_conectado()) {
            LinkedList<Integer> vertices = new LinkedList<>();
            LinkedList<Double> pesos = new LinkedList<>();
            Boolean finalizar = false;
            Integer inicial = o;
            vertices.add(inicial);
            while (!finalizar) {
                LinkedList<Adyacencia> adyacencias = adyacentes(inicial);
                Double peso = Double.MAX_VALUE;
                Integer T = -1;
                for (int i = 0; i < adyacencias.getSize(); i++) {
                    Adyacencia ad = adyacencias.get(i);
                    if (!estaCamino(vertices, ad.getD())) {
                        Double pesoArista = ad.getPeso();
                        if (d.intValue() == ad.getD().intValue()) {
                            T = ad.getD();
                            peso = pesoArista;
                            break;
                        } else if (pesoArista < peso) {
                            T = ad.getD();
                            peso = pesoArista;
                        }
                    }
                }
                vertices.add(T);
                pesos.add(peso);
                inicial = T;
                if (d.intValue() == inicial.intValue()) {
                    break;
                }
            }
            sendero.put("camino", vertices);
            sendero.put("peso", pesos);
        } else {
            System.out.println("error en camino: ");
        }

        return sendero;
    }

//    public boolean conectadoAnchuraNoDir() throws Exception {
//        Boolean[] visitados = obtenerVerticesNoVisitados();
//        LinkedList<Integer> lista = new LinkedList<>();
//        lista.add(1);
//        visitados[1] = true;
//        while (!lista.isEmpty()) {
//            Integer actual = lista.deleteFirst();
//            System.out.println("Actual: " + actual);
//            Adyacencia[] adyacentes = adyacentes(actual).toArray();
//            for (Adyacencia adyacencia : adyacentes) {
//                Integer destino = adyacencia.getD();
//                System.out.println("Destino de adyacencia: " + destino);
//                if (visitados[destino] != null) {
//                    if (!visitados[destino]) {
//                        lista.add(destino);
//                        visitados[destino] = true;
//                    }
//                }
//            }
//        }
//        for (int i = 1; i <= nro_vertices(); i++) {
//            if (!visitados[i]) {
//                return false;
//            }
//        }
//        return true;
//    }
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

// Algoritmo de Dijkstra
//    public void dijkstra(int origen) {
//        LinkedList<Integer> distancia = new LinkedList<>();
//        LinkedList<Boolean> visitado = new LinkedList<>();
//
//        for (int i = 0; i < nro_vertices(); i++) {
//            distancia.add(Integer.MAX_VALUE);
//            visitado.add(false);
//        }
//
//        distancia.set(origen, 0);
//
//        for (int i = 0; i < nro_vertices() - 1; i++) {
//            int u = minDistancia(distancia, visitado);
//            visitado.set(u, true);
//
//            for (int v = 0; v < nro_vertices(); v++) {
//                if (!visitado.get(v) && adyacentes(u, v) != 0 && distancia.get(u) != Integer.MAX_VALUE && distancia.get(u) + adyacentes(u, v) < distancia.get(v)) {
//                    distancia.set(v, distancia.get(u) + adyacentes(u, v));
//                }
//            }
//        }
//    }
//
//// Algoritmo de Floyd-Warshall
//    public void floydWarshall() {
//        LinkedList<LinkedList<Integer>> dist = new LinkedList<>();
//
//        for (int i = 0; i < nro_vertices(); i++) {
//            LinkedList<Integer> temp = new LinkedList<>();
//            for (int j = 0; j < nro_vertices(); j++) {
//                temp.add(adyacentes(i, j));
//            }
//            dist.add(temp);
//        }
//
//        for (int k = 0; k < nro_vertices(); k++) {
//            for (int i = 0; i < nro_vertices(); i++) {
//                for (int j = 0; j < nro_vertices(); j++) {
//                    if (dist.get(i).get(k) + dist.get(k).get(j) < dist.get(i).get(j)) {
//                        dist.get(i).set(j, dist.get(i).get(k) + dist.get(k).get(j));
//                    }
//                }
//            }
//        }
//    }
}
