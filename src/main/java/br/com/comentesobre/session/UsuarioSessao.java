package br.com.comentesobre.session;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.comentesobre.model.Tema;

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

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public boolean hasTema(){
        if(tema == null){
            return false;
        }
        return true;
    }
}