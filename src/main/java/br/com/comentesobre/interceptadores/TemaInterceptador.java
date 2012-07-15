package br.com.comentesobre.interceptadores;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.comentesobre.controllers.ComentarioController;
import br.com.comentesobre.session.UsuarioSessao;

@Intercepts
public class TemaInterceptador implements Interceptor{

    private final UsuarioSessao usuarioSessao;
    private final Result result;

    public TemaInterceptador(UsuarioSessao usuarioSessao, Result result){
        this.usuarioSessao = usuarioSessao;
        this.result = result;
    }

    public void intercept(InterceptorStack stack, ResourceMethod method,
            Object resourceInstance) throws InterceptionException {
        // Direciona para escolher um novo tema.
        result.redirectTo(ComentarioController.class).home();
    }

    public boolean accepts(ResourceMethod method) {
        // Se não há tema não pode continuar.
        return !usuarioSessao.hasTema() && method.containsAnnotation(Restrito.class);
    }
}
