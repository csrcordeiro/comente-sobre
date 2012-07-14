package br.com.comentesobre.session;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.comentesobre.model.Tema;

/**
 * <p>
 *  Classe para guardar a sessão escolhida pelo usuário.
 * </p>
 * @author César Cordeiro
 */

@Component
@SessionScoped
public class UsuarioSessao {

    private Tema tema;

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }
}
