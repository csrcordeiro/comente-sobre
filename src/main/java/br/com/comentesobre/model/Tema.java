package br.com.comentesobre.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Tema",
       uniqueConstraints=@UniqueConstraint(columnNames = { "titulo" }))
public class Tema extends NormalEntity {

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

    public String tratarTituloParaUri() {
        if(titulo == null) {
            return null;
        }

        String uri = titulo.toLowerCase();

        uri = uri.replaceAll("[ãâàáä]", "a").replaceAll("[êèéë]", "e")
                .replaceAll("[îìíï]", "i").replaceAll("[õôòóö]", "o")
                .replaceAll("[ûúùü]", "u").replaceAll(" ", "-");

        return uri;
    }
}
