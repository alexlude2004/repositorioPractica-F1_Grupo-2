package vista;

import controlador.EstacionController;
import controlador.TDA.Listas.LinkedList;
import controlador.TDA.grafos.Grafo;
import controlador.TDA.grafos.GrafoEtiquetadoDirigido;
import controlador.TDA.grafos.GrafoEtiquetadoNoDirigido;
import controlador.util.Utilidades;
import java.io.FileWriter;
import modelo.Estacion;
import org.edisoncor.gui.comboBox.ComboBoxRect;

/**
 *
 * @author alexg
 */
public class UtilesEstacionVista {

    public static void cargarComboEstacion(ComboBoxRect combo) throws Exception {
        combo.removeAllItems();
        LinkedList<Estacion> lista = new EstacionController().listAll();
        for (int i = 0; i < lista.getSize(); i++) {
            combo.addItem(lista.get(i));
        }
    }
    
    public static Estacion getComboEstacion(ComboBoxRect combo) {
        return (Estacion) combo.getSelectedItem();
    }
    
    public static Double distanciaEstaciones(Estacion o, Estacion d) {
        return Utilidades.distanciaDosPuntos(o.getLatitud(), o.getLongitud(), d.getLatitud(), d.getLongitud());
    }
    
    public static void crearMapaEstacion(Grafo grafo) {
        if (grafo instanceof GrafoEtiquetadoDirigido || grafo instanceof GrafoEtiquetadoNoDirigido) {
            GrafoEtiquetadoDirigido ge = (GrafoEtiquetadoDirigido) grafo;
            String mapa = "var osmUrl = 'https://tile.openstreetmap.org/{z}/{x}/{y}.png',\n"
                    + "                    osmAttrib = '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors',\n"
                    + "                    osm = L.tileLayer(osmUrl, {maxZoom: 15, attribution: osmAttrib});\n"
                    + "\n"
                    + "            var map = L.map('map').setView([-4.036, -79.201], 15);\n"
                    + "\n"
                    + "            L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {\n"
                    + "                attribution: '&copy; <a href=\"https://www.openstreetmap.org/copyright\">OpenStreetMap</a> contributors'\n"
                    + "            }).addTo(map);" + "\n";

            try {
                for (int i = 1; i <= ge.nro_vertices(); i++) {
                    Estacion ec =(Estacion) ge.obtenerEtiqueta(i);
                    mapa += "L.marker([" + ec.getLatitud() + "," + ec.getLongitud() + "]).addTo(map)" + "\n";
                    mapa += ".bindPopup(' " + ec.getNombre() +" ')" + "\n";
                    mapa += ".openPopup();" + "\n";
                }
                FileWriter file = new FileWriter("mapas/mapa.js");
                file.write(mapa);
                file.close();
            } catch (Exception e) {
            }
        }
    }
    
}
