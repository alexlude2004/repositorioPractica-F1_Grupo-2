
package controlador;

import controlador.TDA.Listas.LinkedList;
import controlador.TDA.grafos.GrafoEtiquetadoNoDirigido;
import controlador.estaciones.dao.DataAccessObject;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import modelo.Estacion;

/**
 *
 * @author alexg
 */
public class EstacionController extends DataAccessObject<Estacion>{

    public EstacionController() {
        super(Estacion.class);
    }

    private Estacion estacion = new Estacion();
    private LinkedList<Estacion> estaciones = new LinkedList<>();
    private GrafoEtiquetadoNoDirigido<Estacion> grafoEstacion;

    public GrafoEtiquetadoNoDirigido<Estacion> getGrafoEstacion() throws Exception {
        if (grafoEstacion == null) {
            LinkedList<Estacion> lista = getEstaciones();
            Integer size = lista.getSize();
            if (size > 0) {
                grafoEstacion = new GrafoEtiquetadoNoDirigido(size, Estacion.class);
                for (int i = 0; i < size; i++) {
                    grafoEstacion.etiquetarVertice((i + 1), lista.get(i));
                }
            }
        }
        return grafoEstacion;
    }

    /**
     * @return the estaciones
     */
    public LinkedList<Estacion> getEstaciones() {
        if (estaciones.isEmpty()) {
            estaciones = listAll();
        }
        return estaciones;
    }

    /**
     * @param estaciones the estaciones to set
     */
    public void setEstaciones(LinkedList<Estacion> estaciones) {
        this.estaciones = estaciones;
    }

    /**
     * @return the estacion
     */
    public Estacion getEstacion() {
        if (estacion == null) {
            estacion = new Estacion();
        }
        return estacion;
    }

    /**
     * @param estacion the estacion to set
     */
    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    public Boolean save() {
        estacion.setId(generar_id());
        return save(estacion);
    }

    public Boolean update() {
        return update(estacion, buscarIndex(estacion.getId()));
    }
    
    public Integer buscarIndex(Integer id) {
        Integer index = -1;
        if (!listAll().isEmpty()) {
            Estacion[] estaciones = listAll().toArray();
            for (int i = 0; i < estaciones.length; i++) {
                if (id.intValue() == estaciones[i].getId().intValue()) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        Integer lenght = listAll().getSize() + 1;
        Integer pos = lenght.toString().length();
        for (int i = 0; i < (10 - pos); i++) {
            code.append("0");
        }
        code.append(lenght.toString());
        return code.toString();
    }

    public void guardarGrafo() throws Exception {
        if (grafoEstacion != null) {
            getXstream().alias(grafoEstacion.getClass().getName(), GrafoEtiquetadoNoDirigido.class);
            getXstream().toXML(grafoEstacion, new FileWriter("data/grafo.json"));
        } else new NullPointerException("Grafo vacio");
    }
    
    public void cargarGrafo() throws Exception {
        grafoEstacion = (GrafoEtiquetadoNoDirigido<Estacion>)getXstream().
                fromXML(new FileReader("data/grafo.json"));
        estaciones.clear();
        for (int i = 0; i <= grafoEstacion.nro_vertices(); i++) {
            estaciones.add(grafoEstacion.obtenerEtiqueta(i));
        }
    }
    
}
