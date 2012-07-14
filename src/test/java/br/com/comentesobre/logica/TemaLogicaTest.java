package br.com.comentesobre.logica;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.NoResultException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.comentesobre.daos.TemaDao;
import br.com.comentesobre.model.Tema;
import br.com.comentesobre.session.UsuarioSessao;

public class TemaLogicaTest {

    private TemaLogica temaLogica;

    private Tema temaParaTeste;

    @Mock
    private TemaDao temaDao;

    @Mock
    private UsuarioSessao usuarioSessao;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        temaLogica = new TemaLogica(temaDao, usuarioSessao);

        temaParaTeste = new Tema();
        temaParaTeste.setTitulo("métodos ágeis");
    }

    @Test
    public void deveSalvarUmNovoTemaEscolhido() {
        when(temaDao.getTemaPorUri(temaParaTeste.tratarTituloParaUri())).thenThrow(new NoResultException());

        temaLogica.escolher(temaParaTeste);

        verify(temaDao, times(1)).persist(temaParaTeste);
        verify(usuarioSessao, times(1)).setTema(temaParaTeste);
    }

    @Test
    public void naoDeveSalvarTemaPoisJaFoiSalvoEDeveRecuperarORegistroJaSalvo() {
        when(temaDao.getTemaPorUri(temaParaTeste.tratarTituloParaUri())).thenReturn(temaParaTeste);

        temaLogica.escolher(temaParaTeste);

        verify(temaDao, times(0)).persist(temaParaTeste);
        verify(temaDao, times(1)).getTemaPorUri(temaParaTeste.getUri());
        verify(usuarioSessao, times(1)).setTema(temaParaTeste);
    }
}
