
package modelo;

/**
 *
 * @author alexg
 */
public class Estacion {

    private Integer id;
    private String nombre;
    private String foto1;
    private String foto2;
    private String foto3;
    private String foto4;
    private Double latitud;
    private Double longitud;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the foto1
     */
    public String getFoto1() {
        return foto1;
    }

    /**
     * @param foto1 the foto1 to set
     */
    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }
    
    /**
     * @return the foto2
     */
    public String getFoto2() {
        return foto2;
    }

    /**
     * @param foto2 the foto2 to set
     */
    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    /**
     * @return the foto3
     */
    public String getFoto3() {
        return foto3;
    }

    /**
     * @param foto3 the foto3 to set
     */
    public void setFoto3(String foto3) {
        this.foto3 = foto3;
    }

    /**
     * @return the foto4
     */
    public String getFoto4() {
        return foto4;
    }

    /**
     * @param foto4 the foto4 to set
     */
    public void setFoto4(String foto4) {
        this.foto4 = foto4;
    }    

    /**
     * @return the latitud
     */
    public Double getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public Double getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
