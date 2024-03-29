package technicalblog.repository;


import org.springframework.stereotype.Repository;
import technicalblog.model.Post;

import javax.persistence.*;
import java.util.List;

@Repository
public class PostRepository {

    @PersistenceUnit(unitName = "techblog")
    private EntityManagerFactory emf;

    public List<Post> getAllPost(){

        EntityManager em=emf.createEntityManager();
        TypedQuery<Post> query=em.createQuery("select p from Post p",Post.class);
        List<Post> resultList=query.getResultList();

        return  resultList;

    }

    public Post getLatestPost(){
        EntityManager em=emf.createEntityManager();
        return em.find(Post.class,3);
    }

    public Post createPost(Post newPost){
        EntityManager em=emf.createEntityManager();
        EntityTransaction transaction=em.getTransaction();

        try {
            transaction.begin();
            em.persist(newPost);
            transaction.commit();
        }catch(Exception ex){
            transaction.rollback();
        }

        return newPost;
    }

    public Post getPost(Integer id){
        EntityManager em=emf.createEntityManager();
        return em.find(Post.class,id);
    }

    public void updatePost(Post UpdatedPost){
        EntityManager em=emf.createEntityManager();
        EntityTransaction transaction=em.getTransaction();
        try{
            transaction.begin();
            em.merge(UpdatedPost);
            transaction.commit();
        }catch (Exception ex){
            transaction.rollback();
        }
    }

    public void deletePost(Integer deleteId){
        EntityManager em=emf.createEntityManager();
        EntityTransaction transaction=em.getTransaction();
        try{
            transaction.begin();;
            Post deletePost=em.find(Post.class,deleteId);
            em.remove(deletePost);
            transaction.commit();
        }catch (Exception ex){
            transaction.rollback();;
        }
    }
}