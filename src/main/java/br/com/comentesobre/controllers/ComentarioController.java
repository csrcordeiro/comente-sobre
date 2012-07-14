package br.com.comentesobre.controllers;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.comentesobre.daos.ComentarioDao;
import br.com.comentesobre.daos.UsuarioDao;
import br.com.comentesobre.model.Comentario;
import br.com.comentesobre.model.Tema;
import br.com.comentesobre.model.Usuario;
import br.com.comentesobre.session.UsuarioSessao;

@Resource
public class ComentarioController {

    private final Result result;
    private final UsuarioDao usuarioDao;
    private final ComentarioDao comentarioDao;
    private final UsuarioSessao usuarioSessao;

    public ComentarioController(Result result, ComentarioDao comentarioDao,
            UsuarioDao usuarioDao, UsuarioSessao usuarioSessao) {
        this.result = result;
        this.comentarioDao = comentarioDao;
        this.usuarioDao = usuarioDao;
        this.usuarioSessao = usuarioSessao;
    }

    @Path("/")
    public void home() {
    }

    @Path("/{tema.uri}/")
    public Tema novoComentario(Tema tema) {
        return tema;
    }

    @Path("/{tema.uri}/listar")
    public void comentar(Usuario usuario, Comentario comentario) {
        try {
            usuario = usuarioDao.getUsuarioPorEmail(usuario.getEmail());
        } catch (NoResultException e) {
            // Se não há um usuário com este email, um novo será criado.
            usuarioDao.persist(usuario);
        }

        comentario.setDono(usuario);
        comentario.setTema(usuarioSessao.getTema());

        comentarioDao.persist(comentario);
        result.forwardTo(this).listar();
    }

    public List<Comentario> listar() {
        return comentarioDao.getComentariosDoTema(usuarioSessao.getTema());
    }
}