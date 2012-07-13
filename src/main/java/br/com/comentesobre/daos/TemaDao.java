package br.com.comentesobre.daos;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.comentesobre.model.Tema;

@Component
public class TemaDao extends JpaDao<Tema>{

    public TemaDao(EntityManager entityManager) {
        super(entityManager, Tema.class);
    }

    public Tema getTemaPorUri(String uri) {
        Query query = makeQuery("select t from Tema t where t.uri = :uri");
        query.setParameter("uri", uri);

        Tema tema = (Tema) query.getSingleResult();

        return tema;

    }
}
