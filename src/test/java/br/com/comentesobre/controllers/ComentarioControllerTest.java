package br.com.comentesobre.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.NoResultException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.comentesobre.daos.ComentarioDao;
import br.com.comentesobre.daos.TemaDao;
import br.com.comentesobre.daos.UsuarioDao;
import br.com.comentesobre.model.Comentario;
import br.com.comentesobre.model.Tema;
import br.com.comentesobre.model.Usuario;

public class ComentarioControllerTest {

    private ComentarioController controller;

    @Mock
    private TemaDao temaDao;

    @Mock
    private ComentarioDao comentarioDao;

    @Mock
    private UsuarioDao usuarioDao;

    @Mock
    private Result result;

    private Tema temaParaTeste;

    private Usuario usuarioParaComentar;

    private Comentario comentario;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new ComentarioController(result, temaDao, comentarioDao, usuarioDao);

        temaParaTeste = new Tema();
        temaParaTeste.setTitulo("métodos ágeis");

        usuarioParaComentar = new Usuario();
        usuarioParaComentar.setEmail("teste@teste.com");

        comentario = new Comentario();
        comentario.setConteudo("Comentario do usuário.");
    }

    @Test
    public void deveSalvarUmNovoTemaEscolhido() {
        when(result.redirectTo(controller)).thenReturn(controller);
        when(temaDao.getTemaPorUri(temaParaTeste.tratarTituloParaUri())).thenThrow(new NoResultException());

        controller.escolher(temaParaTeste);

        verify(temaDao, times(1)).persist(temaParaTeste);
        verify(result, times(1)).redirectTo(controller);
    }

    @Test
    public void naoDeveSalvarTemaPoisJaFoiSalvoEDeveRecuperarORegistroJaSalvo() {
        when(result.redirectTo(controller)).thenReturn(controller);
        when(temaDao.getTemaPorUri(temaParaTeste.tratarTituloParaUri())).thenReturn(temaParaTeste);

        controller.escolher(temaParaTeste);

        verify(temaDao, times(0)).persist(temaParaTeste);
        verify(temaDao, times(1)).getTemaPorUri(temaParaTeste.getUri());
        verify(result,times(1)).redirectTo(controller);
    }

    @Test
    public void deveCadastrarUmNovoComentarioParaUmDadoTemaECadastrarUmNovoUsuario() {
        when(temaDao.search(temaParaTeste.getId())).thenReturn(temaParaTeste);
        when(usuarioDao.getUsuarioPorEmail(usuarioParaComentar.getEmail())).thenThrow(new NoResultException());
        when(result.redirectTo(controller)).thenReturn(controller);

        controller.comentar(temaParaTeste, usuarioParaComentar, comentario);

        verify(usuarioDao, times(1)).persist(usuarioParaComentar);
        verify(comentarioDao, times(1)).persist(comentario);
        verify(result, times(1)).redirectTo(controller);
        Assert.assertNotNull(comentario.getDono());
    }

    @Test
    public void deveCadastrarUmNovoComentarioParaUmDadoTemaSemCadastrarUmNovoUsuario(){
        when(temaDao.search(temaParaTeste.getId())).thenReturn(temaParaTeste);
        when(usuarioDao.getUsuarioPorEmail(usuarioParaComentar.getEmail())).thenReturn(usuarioParaComentar);
        when(result.redirectTo(controller)).thenReturn(controller);

        controller.comentar(temaParaTeste, usuarioParaComentar, comentario);

        verify(usuarioDao, times(0)).persist(usuarioParaComentar);
        verify(comentarioDao, times(1)).persist(comentario);
        verify(result, times(1)).redirectTo(controller);
        Assert.assertNotNull(comentario.getDono());
    }
}