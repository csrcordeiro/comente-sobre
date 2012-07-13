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

public class ComentarioControllerTest {

    private ComentarioController controller;

    @Mock
    private TemaDao temaDao;

    @Mock
    private Result result;

    private Tema temaParaTeste;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new ComentarioController(result, temaDao);

        temaParaTeste = new Tema();
        // Tema vindo do request.
        temaParaTeste.setTitulo("métodos ágeis");
    }

    @Test
    public void deveSalvarUmNovoTemaEscolhido() {
        when(result.redirectTo(controller)).thenReturn(controller);
        when(temaDao.getTemaPorTitulo(temaParaTeste.tratarTituloParaUri())).thenThrow(new NoResultException());

        controller.escolher(temaParaTeste);

        verify(temaDao, times(1)).persist(temaParaTeste);
        verify(result, times(1)).redirectTo(controller);
    }

    @Test
    public void naoDeveSalvarTemaPoisJaFoiSalvoEDeveRecuperarORegistroJaSalvo() {
        when(result.redirectTo(controller)).thenReturn(controller);
        when(temaDao.getTemaPorTitulo(temaParaTeste.getTitulo())).thenReturn(temaParaTeste);

        controller.escolher(temaParaTeste);

        verify(temaDao, times(0)).persist(temaParaTeste);
        verify(temaDao, times(1)).getTemaPorTitulo(temaParaTeste.getTitulo());
        verify(result,times(1)).redirectTo(controller);
    }
}