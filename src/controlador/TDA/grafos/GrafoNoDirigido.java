package controlador.TDA.grafos;

import controlador.TDA.grafos.exception.VerticeOfSizeException;

/**
 *
 * @author alexg
 */
public class GrafoNoDirigido extends GrafoDirigido {

    public GrafoNoDirigido(Integer nro_vertices) {
        super(nro_vertices);
    }

    @Override
    public void insertar(Integer a, Integer b, Double peso) throws Exception {
        if (a.intValue() <= nro_vertices().intValue() && b.intValue() <= nro_vertices().intValue()) {
            if (!existe_arista(a, b)) {
                setNro_aristas(nro_aristas() + 1);

                Adyacencia auxO = new Adyacencia();
                auxO.setPeso(peso);
                auxO.setD(b);

                Adyacencia auxD = new Adyacencia();
                auxD.setPeso(peso);
                auxD.setD(a);

                getListaAdyacente()[a].add(auxO);
                getListaAdyacente()[b].add(auxD);
            }
        } else {
            throw new VerticeOfSizeException();
        }
    }

}
