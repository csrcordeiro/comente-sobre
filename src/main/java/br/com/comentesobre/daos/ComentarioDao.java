package br.com.comentesobre.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.comentesobre.model.Comentario;
import br.com.comentesobre.model.Tema;

@Component
public class ComentarioDao extends JpaDao<Comentario>{

    public ComentarioDao(EntityManager entityManager) {
        super(entityManager, Comentario.class);
    }

    public List<Comentario> getComentariosDoTema(Tema tema){
        Query query = makeQuery("select c from Comentario c where c.tema = :tema");
        query.setParameter("tema", tema);
        return query.getResultList();
    }
}
