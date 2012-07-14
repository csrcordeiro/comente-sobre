package br.com.comentesobre.controllers;

import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.comentesobre.logica.ComentarioLogica;
import br.com.comentesobre.model.Comentario;
import br.com.comentesobre.model.Tema;
import br.com.comentesobre.model.Usuario;

@Resource
public class ComentarioController {

    private final Result result;
    private final ComentarioLogica comentarioLogica;

    public ComentarioController(Result result, ComentarioLogica comentarioLogica) {
        this.result = result;
        this.comentarioLogica = comentarioLogica;
    }

    @Path("/")
    public void home() {
    }

    @Path("/{tema.uri}/")
    public Tema novoComentario(Tema tema) {
        return tema;
    }

    @Path("/{tema.uri}/comentar")
    public void comentar(Usuario usuario, Comentario comentario) {
        //TODO: Criar Validação de formulário.

        comentarioLogica.comentar(usuario, comentario);

        result.redirectTo(this).listar();
    }

    @Path("/lista")
    public List<Comentario> listar() {
        return comentarioLogica.getComentariosDeTemaEscolhido();
    }
}