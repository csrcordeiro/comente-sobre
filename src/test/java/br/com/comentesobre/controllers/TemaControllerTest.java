package br.com.comentesobre.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.caelum.vraptor.Result;
import br.com.comentesobre.logica.TemaLogica;
import br.com.comentesobre.model.Tema;

public class TemaControllerTest {

    @Mock
    private Result result;

    @Mock
    private ComentarioController comentarioController;

    @Mock
    private TemaLogica temaLogica;

    private TemaController temaController;

    private Tema temaParaTeste;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        temaController = new TemaController(result, temaLogica);

        temaParaTeste = new Tema();
        temaParaTeste.setTitulo("métodos ágeis");
    }

    @Test
    public void deveDirecionarParaOControllerDeComentarioParaNovoComentario() {
        when(result.redirectTo(ComentarioController.class)).thenReturn(comentarioController);

        temaController.escolher(temaParaTeste);

        verify(temaLogica, times(1)).escolher(temaParaTeste);
        verify(result, times(1)).redirectTo(ComentarioController.class);
    }

    //TODO: Testar caso a validação do formulario de errado.
}
