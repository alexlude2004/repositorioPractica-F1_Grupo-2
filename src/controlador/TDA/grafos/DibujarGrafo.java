package controlador.TDA.grafos;

import controlador.TDA.Listas.LinkedList;
import controlador.util.Utilidades;
import java.io.FileWriter;

/**
 *
 * @author alexg
 */
public class DibujarGrafo {

    String URL = "d3/grafo.js";

    public void crearArchivo(GrafoEtiquetadoDirigido g) throws Exception {
        StringBuilder data = new StringBuilder("var nodes = new vis.DataSet([\n");
        for (int i = 1; i <= g.nro_vertices(); i++) {
            String etiqueta = g.obtenerEtiqueta(i).toString();
            data.append("\t{ id: ").append(i).append(", label: '").append("").append(etiqueta).append("', color: 'yellow' },\n");
        }
        data.append("]);\n");

        try {
            data.append("var edges = new vis.DataSet([\n");
            for (int i = 1; i <= g.nro_vertices(); i++) {//Vemos las adyacencias de cada vertice
                if (!g.adyacentes(i).isEmpty()) {
                    Adyacencia[] lista = g.adyacentes(i).toArray();
                    for (int j = 0; j < lista.length; j++) {
                        Adyacencia a = lista[j];
                        if (g.existe_arista(i, a.getD())) {
                            data.append("\t{ from: ").append(i).append(", to: ").append(a.getD()).append(", label: '").append(Utilidades.redondear(a.getPeso())).append("', color: 'lightblue' },\n");
                        }
                    }
                }
            }
            data.append("]);");

            String finalArchivo = "var container = document.getElementById(\"mynetwork\");\n"
                    + "var data = {\n"
                    + "  nodes: nodes,\n"
                    + "  edges: edges,\n"
                    + "};\n"
                    + "var options = {};\n"
                    + "var network = new vis.Network(container, data, options);";

            FileWriter file = new FileWriter(URL);
            file.write(data + "\n" + finalArchivo);
            file.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void crearArchivo(GrafoEtiquetadoDirigido g, LinkedList<Integer> camino) throws Exception {
        StringBuilder data = new StringBuilder("var nodes = new vis.DataSet([\n");
        for (int i = 1; i <= g.nro_vertices(); i++) {
            String etiqueta = g.obtenerEtiqueta(i).toString();
            data.append("\t{ id: ").append(i).append(", label: '").append("").append(etiqueta).append("', color: 'yellow' },\n");
        }
        data.append("]);\n");

        try {
            data.append("var edges = new vis.DataSet([\n");
            for (int i = 0; i < camino.size() - 1; i++) {
                Integer from = camino.get(i);
                Integer to = camino.get(i + 1);
                Double peso = g.peso_arista(from, to);
                data.append("\t{ from: ").append(from).append(", to: ").append(to).append(", label: '").append(Utilidades.redondear(peso)).append("', color: 'red' },\n");
            }
            data.append("]);");

            String finalArchivo = "var container = document.getElementById(\"mynetwork\");\n"
                    + "var data = {\n"
                    + "  nodes: nodes,\n"
                    + "  edges: edges,\n"
                    + "};\n"
                    + "var options = {};\n"
                    + "var network = new vis.Network(container, data, options);";

            FileWriter file = new FileWriter(URL);
            file.write(data + "\n" + finalArchivo);
            file.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
