package br.com.comentesobre.daos;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.comentesobre.model.Tema;

@Component
public class TemaDao extends JpaDao<Tema>{

    public TemaDao(EntityManager entityManager) {
        super(entityManager, Tema.class);
    }

    public boolean isTituloCadastrado(String tema){
        Tema temaDoBanco = getTemaPorTitulo(tema);

        if(temaDoBanco != null){
            return true;
        } else {
            return false;
        }
    }

    public Tema getTemaPorTitulo(String titulo) throws NoResultException{
        Query query = makeQuery("select t from Tema t where t.titulo = :titulo");
        query.setParameter("titulo", titulo);

        Tema tema = (Tema) query.getSingleResult();

        return tema;
    }
}
