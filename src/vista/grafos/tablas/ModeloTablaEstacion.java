
package vista.grafos.tablas;

import controlador.TDA.Listas.LinkedList;
import javax.swing.table.AbstractTableModel;
import modelo.Estacion;

/**
 *
 * @author alexg
 */
public class ModeloTablaEstacion extends AbstractTableModel{
    private LinkedList<Estacion> lista = new LinkedList<>();
    
    @Override
    public int getRowCount() {
        return lista.getSize();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Estacion e = null; 
        try {
            e = lista.get(rowIndex);
        } catch (Exception ex) {
        }
   
        switch (columnIndex) {
            case 0:
                return (rowIndex + 1);
            case 1:
                return e.getNombre();
            case 2:
                return e.getLatitud();
            case 3:
                return e.getLongitud();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nro";
            case 1:
                return "Nombre";
            case 2:
                return "Latitud";
            case 3:
                return "Longitud";
            default:
                throw new AssertionError();
        }
    }

    /**
     * @return the lista
     */
    public LinkedList<Estacion> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(LinkedList<Estacion> lista) {
        this.lista = lista;
    }
    
}
