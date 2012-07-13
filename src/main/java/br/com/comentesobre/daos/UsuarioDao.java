package br.com.comentesobre.daos;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.caelum.vraptor.ioc.Component;
import br.com.comentesobre.model.Usuario;

@Component
public class UsuarioDao extends JpaDao<Usuario>{

    public UsuarioDao(EntityManager entityManager) {
        super(entityManager, Usuario.class);
    }

    public Usuario getUsuarioPorEmail(String emailDoUsuario) throws NoResultException{
        Query query = makeQuery("select u from Usuario u where u.email = :email");
        query.setParameter("email", emailDoUsuario);
        return (Usuario) query.getSingleResult();
    }

}
