package br.com.comentesobre.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Tema extends NormalEntity{

    @OneToMany
    private Collection<Comentario> comentariosSobre;

    private String titulo;

    public Collection<Comentario> getComentariosSobre() {
        return comentariosSobre;
    }

    public void setComentariosSobre(Collection<Comentario> comentariosSobre) {
        this.comentariosSobre = comentariosSobre;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
