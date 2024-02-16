package controlador.TDA.grafos;

import controlador.TDA.Listas.LinkedList;
import controlador.util.Utilidades;
import java.io.FileWriter;
import java.util.HashMap;

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
                    + "var options = {\n"
                    + "  layout: {\n"
                    + "    improvedLayout: true\n"
                    + "  },\n"
                    + "  physics: {\n"
                    + "    barnesHut: {\n"
                    + "      gravitationalConstant: -2000,\n"
                    + "      centralGravity: 0.3,\n"
                    + "      springLength: 230,\n"
                    + "      springConstant: 0.05,\n"
                    + "      damping: 0.09,\n"
                    + "      avoidOverlap: 0.1\n"
                    + "    }\n"
                    + "  },\n"
                    + "  edges: {\n"
                    + "    smooth: {\n"
                    + "      type: 'continuous'\n"
                    + "    },\n"
                    + "    color: 'darkblue',\n"
                    + "    width: 2\n"
                    + "  }\n"
                    + "};\n"
                    + "var network = new vis.Network(container, data, options);";

            FileWriter file = new FileWriter(URL);
            file.write(data + "\n" + finalArchivo);
            file.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void crearArchivoCamino(GrafoEtiquetadoDirigido g) throws Exception {
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
                    + "var options = {\n"
                    + "  layout: {\n"
                    + "    improvedLayout: true\n"
                    + "  },\n"
                    + "  physics: {\n"
                    + "    barnesHut: {\n"
                    + "      gravitationalConstant: -2000,\n"
                    + "      centralGravity: 0.3,\n"
                    + "      springLength: 230,\n"
                    + "      springConstant: 0.05,\n"
                    + "      damping: 0.09,\n"
                    + "      avoidOverlap: 0.1\n"
                    + "    }\n"
                    + "  },\n"
                    + "  edges: {\n"
                    + "    smooth: {\n"
                    + "      type: 'continuous'\n"
                    + "    },\n"
                    + "    color: 'darkblue',\n"
                    + "    width: 2\n"
                    + "  }\n"
                    + "};\n"
                    + "var network = new vis.Network(container, data, options);";

            FileWriter file = new FileWriter(URL);
            file.write(data + "\n" + finalArchivo);
            file.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
//public void crearArchivo(GrafoEtiquetadoDirigido g){
//        StringBuilder datos = new StringBuilder("var nodes = new vis.DataSet([\n");
//        for (int i = 1; i <= g.nro_vertices(); i++) {//Se recorre los vertices y se extrae su etiqueta:
//            String etiqueta = g.obtenerEtiqueta(i).toString();
//            datos.append("\t{ id: ").append(i).append(", label: '").append("").append(etiqueta).append("' },\n");
//        }
//        datos.append("]);\n");
//        
//        try {
//        datos.append("var edges = new vis.DataSet([\n");
//        for (int i = 1; i <= g.nro_vertices(); i++) {//Vemos las adyacencias de cada vertice
//            if(!g.adyacentes(i).isEmpty()){
//                Adyacencia[] lista = g.adyacentes(i).toArray();
//                    for (int j = 0; j < lista.length ; j++) {
//                        Adyacencia a = lista[j];
//                        if(g.existe_arista(i, a.getD())){
//                            if(datos.toString(),i,a.getD())
//                                datos.append("\t{ from: ").append(i).append(", to: ").append(a.getD()).append(" },\n");
//                            else 
//                                datos.append("\t{ from: ").append(i).append(", to: ").append(a.getD()).append(", label: '").append(Utilidades.redondear(a.getPeso())).append("' },\n");
//                        }
//                    }
//            }
//        }
//        datos.append("]);");
//        String data = datos.toString();
//        String finalArchivo = 
//            "var container = document.getElementById(\"mynetwork\");\n" +
//            "      var data = {\n" +
//            "        nodes: nodes,\n" +
//            "        edges: edges,\n" +
//            "      };\n" +
//            "      var options = {};\n" +
//            "      var network = new vis.Network(container, data, options);";
//        
//            FileWriter file = new FileWriter(url);
//            file.write(data+ "\n"+finalArchivo);
//            file.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public void crearArchivo(Grafo grafo){
//        if(grafo instanceof GrafoEtiquetadoDirigido || grafo instanceof GrafoEtiquetadoNoDirigido){
//            GrafoEtiquetadoDirigido g = (GrafoEtiquetadoDirigido)grafo;
//            StringBuilder datos = new StringBuilder("var nodes = new vis.DataSet([\n");
//            for (int i = 1; i <= g.nro_vertices(); i++) {//Se recorre los vertices y se extrae su etiqueta:
//                String etiqueta = g.obtenerEtiqueta(i).toString();
//                datos.append("\t{ id: ").append(i).append(", label: '").append("").append(etiqueta).append("' },\n");
//            }
//            datos.append("]);\n");
//            try {
//            datos.append("var edges = new vis.DataSet([\n");
//            for (int i = 1; i <= g.nro_vertices(); i++) {//Vemos las adyacencias de cada vertice
//            if(!g.adyacentes(i).isEmpty()){
//                Adyacencia[] lista = g.adyacentes(i).toArray();
//                    for (int j = 0; j < lista.length ; j++) {
//                        Adyacencia a = lista[j];
//                        if(g.existe_arista(i, a.getD())){
//                            datos.append("\t{ from: ").append(i).append(", to: ").append(a.getD()).append(", label: '").append(a.getPeso()).append("' },\n");
//                        }
//                    }
//            }
//        }
//        datos.append("]);");
//        //faltan edges 
//        String data = datos.toString();
//        String finalArchivo = 
//            "var container = document.getElementById(\"mynetwork\");\n" +
//            "      var data = {\n" +
//            "        nodes: nodes,\n" +
//            "        edges: edges,\n" +
//            "      };\n" +
//            "      var options = {};\n" +
//            "      var network = new vis.Network(container, data, options);";
//        
//            FileWriter file = new FileWriter(url);
//            file.write(data+ "\n"+finalArchivo);
//            file.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        }
//        
//    }    

}
