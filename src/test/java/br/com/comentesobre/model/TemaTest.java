package br.com.comentesobre.model;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TemaTest {

    private Tema tema;

    @Before
    public void setUp(){
        tema = new Tema();
    }

    @Test
    public void deveTransformarOTituloDadoEmUmaUriValida() {
        tema.setTitulo("métodos ágeis");

        String uri = tema.tratarTituloParaUri();

        Assert.assertEquals("metodos-ageis", uri);
    }
}
