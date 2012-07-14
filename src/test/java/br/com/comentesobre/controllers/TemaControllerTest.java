package br.com.comentesobre.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.util.test.MockValidator;
import br.com.caelum.vraptor.validator.Message;
import br.com.caelum.vraptor.validator.ValidationException;
import br.com.comentesobre.logica.TemaLogica;
import br.com.comentesobre.model.Tema;

public class TemaControllerTest {

    @Mock
    private Result result;

    @Mock
    private ComentarioController comentarioController;

    @Mock
    private TemaLogica temaLogica;

    private final MockValidator validador = new MockValidator();

    private TemaController temaController;

    private Tema temaParaTeste;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        temaController = new TemaController(result, temaLogica, validador);

        temaParaTeste = new Tema();
        temaParaTeste.setTitulo("métodos ágeis");
    }

    @Test
    public void deveDirecionarParaOControllerDeComentarioParaNovoComentario() {
        when(result.redirectTo(ComentarioController.class)).thenReturn(
                comentarioController);

        temaController.escolher(temaParaTeste);

        verify(temaLogica, times(1)).escolher(temaParaTeste);
        verify(result, times(1)).redirectTo(ComentarioController.class);
    }

    @Test
    public void deveFalharNaValidacaoDeTituloDoTema() {
        temaParaTeste.setTitulo("");

        List<Message> errors = null;
        when(result.redirectTo(ComentarioController.class)).thenReturn(
                comentarioController);

        try {
            temaController.escolher(temaParaTeste);
        } catch (ValidationException e) {
            errors = e.getErrors();
        }

        Assert.assertTrue("Mais de um erro validado.",
                errors.size() == 1);
        verify(result, times(0)).redirectTo(ComentarioController.class);
    }
}
