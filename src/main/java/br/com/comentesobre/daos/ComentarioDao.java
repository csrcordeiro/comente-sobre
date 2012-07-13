package br.com.comentesobre.daos;

import javax.persistence.EntityManager;

import br.com.caelum.vraptor.ioc.Component;
import br.com.comentesobre.model.Comentario;

@Component
public class ComentarioDao extends JpaDao<Comentario>{

    public ComentarioDao(EntityManager entityManager) {
        super(entityManager, Comentario.class);
    }

}
