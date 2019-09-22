package technicalblog.repository;

import org.springframework.stereotype.Repository;
import technicalblog.model.User;
import javax.persistence.*;


import technicalblog.model.User;

@Repository
public class UserRepository {

    @PersistenceUnit(name="techblog")
    private EntityManagerFactory emf;

    public void registerUser(User newUser){

        EntityManager em=emf.createEntityManager();
        EntityTransaction transaction=em.getTransaction();
        try{
            transaction.begin();
            em.persist(newUser);
            transaction.commit();
        }catch (Exception ex){
            transaction.rollback();
        }
    }


    public  User checkUser(String username ,String password){
        EntityManager em=emf.createEntityManager();
        try {
            TypedQuery<User> typedQuery = em.createQuery("select u from User u where u.username=:username and u.password=:password", User.class);
            typedQuery.setParameter("username", username);
            typedQuery.setParameter("password", password);
            return typedQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }

    }
}
