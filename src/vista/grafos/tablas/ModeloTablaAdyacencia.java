package vista.grafos.tablas;

import controlador.TDA.grafos.GrafoEtiquetadoNoDirigido;
import controlador.util.Utilidades;
import javax.swing.table.AbstractTableModel;
import modelo.Estacion;

/**
 *
 * @author alexg
 */
public class ModeloTablaAdyacencia extends AbstractTableModel {

    private GrafoEtiquetadoNoDirigido<Estacion> grafo;

    @Override
    public int getRowCount() {
        return getGrafo().nro_vertices();
    }

    @Override
    public int getColumnCount() {
        return getGrafo().nro_vertices() + 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return getGrafo().obtenerEtiqueta(rowIndex + 1).toString();
        } else {
            String valor = "-***-";
            try {
                Estacion o = getGrafo().obtenerEtiqueta(rowIndex + 1);
                Estacion d = getGrafo().obtenerEtiqueta(columnIndex);
                if (getGrafo().existeAristaE(o, d)) {
                    valor = Utilidades.redondear(getGrafo().peso_arista((rowIndex + 1), columnIndex)).toString();
                }
                return valor;
            } catch (Exception e) {
                System.out.println("Error en modelo tabla" + e);
                return valor;
            }
        }
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "Estaciones";
        } else {
            return getGrafo().obtenerEtiqueta(column).toString();
        }
    }

    /**
     * @return the grafo
     */
    public GrafoEtiquetadoNoDirigido<Estacion> getGrafo() {
        return grafo;
    }

    /**
     * @param grafo the grafo to set
     */
    public void setGrafo(GrafoEtiquetadoNoDirigido<Estacion> grafo) {
        this.grafo = grafo;
    }

}
