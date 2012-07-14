package br.com.comentesobre.logica;

import javax.persistence.NoResultException;

import br.com.caelum.vraptor.ioc.Component;
import br.com.comentesobre.daos.TemaDao;
import br.com.comentesobre.model.Tema;
import br.com.comentesobre.session.UsuarioSessao;

@Component
public class TemaLogica {

    private final TemaDao temaDao;

    private final UsuarioSessao usuarioSessao;

    public TemaLogica(TemaDao temaDao, UsuarioSessao usuarioSessao) {
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
    }
}
