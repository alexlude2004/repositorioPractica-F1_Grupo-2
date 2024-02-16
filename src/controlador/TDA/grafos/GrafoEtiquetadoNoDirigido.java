package controlador.TDA.grafos;

/**
 *
 * @author alexg
 */
public class GrafoEtiquetadoNoDirigido<E> extends GrafoEtiquetadoDirigido<E> {

    public GrafoEtiquetadoNoDirigido(Integer nro_vertices, Class<E> clazz) {
        super(nro_vertices, clazz);
    }

    @Override
    public void insertar(Integer a, Integer b, Double peso) throws Exception {
        if (a.intValue() <= nro_vertices() && b.intValue() <= nro_vertices()) {
            if (!existe_arista(a, b)) {
                Adyacencia auxO = new Adyacencia();
                auxO.setPeso(peso);
                auxO.setD(b);
                Adyacencia auxD = new Adyacencia();
                auxD.setPeso(peso);
                auxD.setD(a);
                getListaAdyacente()[a].add(auxO);
                getListaAdyacente()[b].add(auxD);
                setNro_aristas(nro_aristas() + 1);
            }
        } else {
            throw new Exception();
        }
    }

}
