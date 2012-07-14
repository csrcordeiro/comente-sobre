package br.com.comentesobre.logica;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.NoResultException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.comentesobre.daos.ComentarioDao;
import br.com.comentesobre.daos.UsuarioDao;
import br.com.comentesobre.model.Comentario;
import br.com.comentesobre.model.Tema;
import br.com.comentesobre.model.Usuario;
import br.com.comentesobre.session.UsuarioSessao;

public class ComentarioLogicaTest {

    private ComentarioLogica comentarioLogica;

    @Mock
    private ComentarioDao comentarioDao;

    @Mock
    private UsuarioDao usuarioDao;

    @Mock
    private UsuarioSessao usuarioSessao;

    private Tema temaParaTeste;

    private Usuario usuarioParaComentar;

    private Comentario comentario;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        comentarioLogica = new ComentarioLogica(usuarioDao, comentarioDao, usuarioSessao);

        temaParaTeste = new Tema();
        temaParaTeste.setTitulo("métodos ágeis");

        usuarioParaComentar = new Usuario();
        usuarioParaComentar.setEmail("teste@teste.com");

        comentario = new Comentario();
        comentario.setConteudo("Comentario do usuário.");
    }

    @Test
    public void deveCadastrarUmNovoComentarioParaUmDadoTemaECadastrarUmNovoUsuario() {
        when(usuarioDao.getUsuarioPorEmail(usuarioParaComentar.getEmail()))
                .thenThrow(new NoResultException());

        comentarioLogica.comentar(usuarioParaComentar, comentario);

        verify(usuarioDao, times(1)).persist(usuarioParaComentar);
        verify(comentarioDao, times(1)).persist(comentario);
        Assert.assertNotNull(comentario.getDono());
    }

    @Test
    public void deveCadastrarUmNovoComentarioParaUmDadoTemaSemCadastrarUmNovoUsuario() {
        when(usuarioDao.getUsuarioPorEmail(usuarioParaComentar.getEmail()))
                .thenReturn(usuarioParaComentar);

        comentarioLogica.comentar(usuarioParaComentar, comentario);

        verify(usuarioDao, times(0)).persist(usuarioParaComentar);
        verify(comentarioDao, times(1)).persist(comentario);
        Assert.assertNotNull(comentario.getDono());
    }
}