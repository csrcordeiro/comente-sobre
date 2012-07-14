package br.com.comentesobre.controllers;

import javax.persistence.NoResultException;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.comentesobre.daos.TemaDao;
import br.com.comentesobre.model.Tema;
import br.com.comentesobre.session.UsuarioSessao;

@Resource
public class TemaController {

    private final Result result;
    private final TemaDao temaDao;
    private final UsuarioSessao usuarioSessao;

    public TemaController(Result result, TemaDao temaDao, UsuarioSessao usuarioSessao) {
        this.result = result;
        this.temaDao = temaDao;
        this.usuarioSessao = usuarioSessao;
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

        usuarioSessao.setTema(tema);

        result.redirectTo(ComentarioController.class).novoComentario(tema);
    }

}
