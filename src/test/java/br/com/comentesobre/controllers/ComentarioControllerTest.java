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
import br.com.comentesobre.logica.ComentarioLogica;
import br.com.comentesobre.model.Comentario;
import br.com.comentesobre.model.Tema;
import br.com.comentesobre.model.Usuario;

public class ComentarioControllerTest {

    private ComentarioController controller;

    @Mock
    private ComentarioLogica comentarioLogica;

    @Mock
    private Result result;

    private final MockValidator validador = new MockValidator();

    private Tema temaParaTeste;

    private Usuario usuarioParaComentar;

    private Comentario comentario;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        controller = new ComentarioController(result, comentarioLogica, validador);

        temaParaTeste = new Tema();
        temaParaTeste.setTitulo("métodos ágeis");

        usuarioParaComentar = new Usuario();
        usuarioParaComentar.setEmail("teste@teste.com");

        comentario = new Comentario();
        comentario.setConteudo("Comentario do usuário.");
    }

    @Test
    public void deveRedirecionarParaActionDeListarComentarios() {
        when(result.redirectTo(controller)).thenReturn(controller);
        when(comentarioLogica.getTemaAtualParaNovoComentario()).thenReturn(temaParaTeste);

        controller.comentar(usuarioParaComentar, comentario);

        verify(comentarioLogica, times(1)).comentar(usuarioParaComentar, comentario);
        verify(result, times(1)).redirectTo(controller);
    }

    @Test
    public void deveFalharNaValidacaoPorNaoTerUmUsuarioComEmailValido(){
        usuarioParaComentar.setEmail("");

        List<Message> erros = null;

        try{
            controller.comentar(usuarioParaComentar, comentario);
        }catch (ValidationException e) {
            erros = e.getErrors();
        }

        Assert.assertTrue("A lista de erros contém mais ou menos de um erro", erros.size() == 1);
    }

    @Test
    public void deveFalharNaValidacaoPorNaoTerUmComentarioValido(){
        comentario.setConteudo("");

        List<Message> erros = null;

        try{
            controller.comentar(usuarioParaComentar, comentario);
        }catch (ValidationException e) {
            erros = e.getErrors();
        }

        Assert.assertTrue("A lista de erros contém mais ou menos de um erro", erros.size() == 1);
    }
}