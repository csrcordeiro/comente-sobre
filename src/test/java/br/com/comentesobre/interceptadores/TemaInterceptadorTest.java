package br.com.comentesobre.interceptadores;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.comentesobre.controllers.ComentarioController;
import br.com.comentesobre.session.UsuarioSessao;

public class TemaInterceptadorTest {

    public TemaInterceptador temaInterceptador;

    @Mock
    public UsuarioSessao usuarioSessao;

    @Mock
    public Result result;

    @Mock
    public ComentarioController controller;

    @Mock
    public ResourceMethod method;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        temaInterceptador = new TemaInterceptador(usuarioSessao, result);
    }

    @Test
    public void deveInterceptarARequisicaoERedirecionarParaAPaginaCerta() {
        when(result.redirectTo(ComentarioController.class)).thenReturn(controller);
        when(usuarioSessao.hasTema()).thenReturn(false);
        when(method.containsAnnotation(Restrito.class)).thenReturn(true);
        boolean verdade = false;

        verdade = temaInterceptador.accepts(method);

        temaInterceptador.intercept(null, null, null);

        Assert.assertTrue(verdade);
        verify(result, times(1)).redirectTo(ComentarioController.class);
    }

    @Test
    public void naoDeveInterceptarAChamada() {
        when(method.containsAnnotation(Restrito.class)).thenReturn(true);
        when(usuarioSessao.hasTema()).thenReturn(true);

        boolean mentira = true;

        mentira = temaInterceptador.accepts(method);

        Assert.assertFalse(mentira);
    }

}
