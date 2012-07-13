package br.com.comentesobre.controllers;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.comentesobre.daos.TemaDao;
import br.com.comentesobre.model.Comentario;
import br.com.comentesobre.model.Tema;

@Resource
public class ComentarioController {

    private final Result result;
    private final TemaDao temaDao;

    public ComentarioController(Result result, TemaDao temaDao) {
        this.result = result;
        this.temaDao = temaDao;
    }

    @Path("/")
    public void home() {
    }

    public void escolher(Tema tema) {
        String titulo = tema.tratarTituloParaUri();
        tema.setTitulo(titulo);

        try {
            tema = temaDao.getTemaPorTitulo(tema.getTitulo());
        }catch (NoResultException e) {
            //Caso não haja resultado, um novo tema será cadastrado.
            temaDao.persist(tema);
        }

        result.redirectTo(this).novoComentario(titulo,tema);
    }

    @Path("/{titulo}")
    public void novoComentario(String titulo, Tema tema) {
    }

    public void comentar(Comentario comentario) {
        // TODO: lógica de comentario.

        result.forwardTo(this).listar();
    }

    public List<Comentario> listar() {
        return null;
    }
}