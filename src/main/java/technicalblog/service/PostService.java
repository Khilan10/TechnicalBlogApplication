package technicalblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.Post;
import technicalblog.repository.PostRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {


     @Autowired
     private PostRepository postRepository;
      public List<Post> postService(){

        return postRepository.getAllPost();



        /*Connection con=null;
        try{
            Class.forName("org.postgresql.Driver");
            con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/technicalBlog","postgres","postgres");
            Statement statement=con.createStatement();
            ResultSet rs=statement.executeQuery("select * from posts");
            while(rs.next()){
                Post post=new Post();
                post.setTitle(rs.getString("title"));
                post.setDate(rs.getDate("date"));
                post.setBody(rs.getString("description"));
                posts.add(post);
            }

        }catch(ClassNotFoundException| SQLException ex){
            ex.printStackTrace();
        }finally {
            try{
               if(!con.isClosed()){
                   con.close();
               }
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }

        return  posts;*/


    }

    public Post getOnePost(){

        return postRepository.getLatestPost();
}

    public void createPost(Post newPost){
          newPost.setDate(new Date());
          postRepository.createPost(newPost);
        System.out.println("Print new Post"+newPost);

    }

    public Post getPost(Integer id){
       return postRepository.getPost(id);
    }

    public void updatePost(Post updatedPost){
          updatedPost.setDate(new Date());
          postRepository.updatePost(updatedPost);

    }

    public void deletePost(Integer deletePostId){
          postRepository.deletePost(deletePostId);
    }


}
