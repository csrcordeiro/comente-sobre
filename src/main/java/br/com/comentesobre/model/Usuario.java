package br.com.comentesobre.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Usuario",
       uniqueConstraints=@UniqueConstraint(columnNames = { "email" }))
public class Usuario extends NormalEntity{

    // Este atributo deve ser único.
    private String email;

    // Um usuário faz varios comentários.
    @OneToMany
    private Collection<Comentario> comentarios;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
