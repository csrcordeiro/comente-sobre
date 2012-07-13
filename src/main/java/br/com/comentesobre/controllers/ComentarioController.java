package br.com.comentesobre.controllers;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.comentesobre.daos.ComentarioDao;
import br.com.comentesobre.daos.TemaDao;
import br.com.comentesobre.daos.UsuarioDao;
import br.com.comentesobre.model.Comentario;
import br.com.comentesobre.model.Tema;
import br.com.comentesobre.model.Usuario;

@Resource
public class ComentarioController {

    private final Result result;
    private final TemaDao temaDao;
    private final UsuarioDao usuarioDao;
    private final ComentarioDao comentarioDao;

    public ComentarioController(Result result, TemaDao temaDao,
            ComentarioDao comentarioDao, UsuarioDao usuarioDao) {
        this.result = result;
        this.temaDao = temaDao;
        this.comentarioDao = comentarioDao;
        this.usuarioDao = usuarioDao;
    }

    @Path("/")
    public void home() {
    }

    public void escolher(Tema tema) {
        String uri = tema.tratarTituloParaUri();
        tema.setUri(uri);

        try {
            tema = temaDao.getTemaPorUri(uri);
        } catch (NoResultException e) {
            // Caso não haja resultado, um novo tema será cadastrado.
            temaDao.persist(tema);
        }

        result.redirectTo(this).novoComentario(tema);
    }

    @Path("/{tema.uri}")
    public Tema novoComentario(Tema tema) {
        return tema;
    }

    public void comentar(Tema tema, Usuario usuario, Comentario comentario) {
        try{
            usuario = usuarioDao.getUsuarioPorEmail(usuario.getEmail());
        }catch (NoResultException e) {
            // Se não há um usuário com este email, um novo será criado.
            usuarioDao.persist(usuario);
        }

        comentario.setDono(usuario);
        comentario.setTema(tema);

        comentarioDao.persist(comentario);
        result.redirectTo(this).listar(tema);
    }

    public List<Comentario> listar(Tema tema) {
        return null;
    }
}