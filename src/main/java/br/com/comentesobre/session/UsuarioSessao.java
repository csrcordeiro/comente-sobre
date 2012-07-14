package br.com.comentesobre.session;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.comentesobre.model.Tema;
import br.com.comentesobre.model.Usuario;

/**
 * <p>
 *  Classe para guardar as informações escolhidas pelo usuário.
 * </p>
 * @author César Cordeiro
 */

@Component
@SessionScoped
public class UsuarioSessao implements Serializable{

    private Tema tema;

    private Usuario usuario;

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
