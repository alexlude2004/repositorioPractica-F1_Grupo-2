
package controlador.estaciones.dao;

import controlador.TDA.Listas.LinkedList;

/**
 *
 * @author alexg
 */
public interface TransferObjects <T> {
    public Boolean save(T data);
    public Boolean update(T data, Integer index);
    public LinkedList<T> listAll();
    public T find(Integer id);
    
}
