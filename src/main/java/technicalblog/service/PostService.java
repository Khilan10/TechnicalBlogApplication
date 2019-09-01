package technicalblog.service;

import org.springframework.stereotype.Service;
import technicalblog.model.Post;

import java.util.ArrayList;
import java.util.Date;

@Service
public class PostService {

    public ArrayList<Post> getAllPosts(){

        ArrayList<Post> posts=new ArrayList<Post>();
        Post post1=new Post();
        post1.setTitle("Post 1");
        post1.setBody("body 1");
        post1.setDate(new Date());

        Post post2=new Post();
        post2.setTitle("Post 2");
        post2.setBody("body 2");
        post2.setDate(new Date());

        Post post3=new Post();
        post3.setTitle("Post 3");
        post3.setBody("body 3");
        post3.setDate(new Date());

        posts.add(post1);
        posts.add(post2);
        posts.add(post3);

        return  posts;

    }

    public ArrayList<Post> getOnePost(){

        ArrayList<Post> posts=new ArrayList<Post>();

        Post post=new Post();
        post.setTitle("Your first Post");
        post.setBody("This is my first post");
        post.setDate(new Date());

        posts.add(post);

        return posts;

    }

    public void createPost(Post newPost){

    }
}
