package br.com.comentesobre.logica;

import java.util.List;

import javax.persistence.NoResultException;

import br.com.caelum.vraptor.ioc.Component;
import br.com.comentesobre.daos.ComentarioDao;
import br.com.comentesobre.daos.UsuarioDao;
import br.com.comentesobre.model.Comentario;
import br.com.comentesobre.model.Usuario;
import br.com.comentesobre.session.UsuarioSessao;

@Component
public class ComentarioLogica {

    private final UsuarioDao usuarioDao;

    private final ComentarioDao comentarioDao;

    private final UsuarioSessao usuarioSessao;

    public ComentarioLogica(UsuarioDao usuarioDao, ComentarioDao comentarioDao,
            UsuarioSessao usuarioSessao) {
        this.usuarioDao = usuarioDao;
        this.comentarioDao = comentarioDao;
        this.usuarioSessao = usuarioSessao;
    }

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
    }

    public List<Comentario> getComentariosDeTemaEscolhido(){
        return comentarioDao.getComentariosDoTema(usuarioSessao.getTema());
    }

}
