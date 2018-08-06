package Unidad5PF;

public class Notas {
    int idNota;
    String titulo;
    String descripcion;



    public Notas(int idNota, String titulo, String descripcion) {
        this.idNota = idNota;
        this.titulo = titulo;
        this.descripcion = descripcion;



    }



    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
