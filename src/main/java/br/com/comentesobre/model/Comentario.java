package br.com.comentesobre.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Comentario extends NormalEntity{

    @ManyToOne
    private Usuario dono;

    @ManyToOne
    private Tema tema;

    private String conteudo;

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
}
