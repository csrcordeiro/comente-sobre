package br.com.comentesobre.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.NoResultException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.comentesobre.daos.TemaDao;
import br.com.comentesobre.model.Tema;
import br.com.comentesobre.session.UsuarioSessao;

public class TemaControllerTest {

    @Mock
    private Result result;

    @Mock
    private TemaDao temaDao;

    @Mock
    private ComentarioController comentarioController;

    @Mock
    private UsuarioSessao usuarioSessao;

    private TemaController temaController;

    private Tema temaParaTeste;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        temaController = new TemaController(result, temaDao, usuarioSessao);

        temaParaTeste = new Tema();
        temaParaTeste.setTitulo("métodos ágeis");
    }

    @Test
    public void deveSalvarUmNovoTemaEscolhido() {
        when(result.redirectTo(ComentarioController.class)).thenReturn(comentarioController);
        when(temaDao.getTemaPorUri(temaParaTeste.tratarTituloParaUri())).thenThrow(new NoResultException());

        temaController.escolher(temaParaTeste);

        verify(temaDao, times(1)).persist(temaParaTeste);
        verify(usuarioSessao, times(1)).setTema(temaParaTeste);
        verify(result, times(1)).redirectTo(ComentarioController.class);
    }

    @Test
    public void naoDeveSalvarTemaPoisJaFoiSalvoEDeveRecuperarORegistroJaSalvo() {
        when(result.redirectTo(ComentarioController.class)).thenReturn(comentarioController);
        when(temaDao.getTemaPorUri(temaParaTeste.tratarTituloParaUri())).thenReturn(temaParaTeste);

        temaController.escolher(temaParaTeste);

        verify(temaDao, times(0)).persist(temaParaTeste);
        verify(temaDao, times(1)).getTemaPorUri(temaParaTeste.getUri());
        verify(usuarioSessao, times(1)).setTema(temaParaTeste);
        verify(result,times(1)).redirectTo(ComentarioController.class);
    }
}
