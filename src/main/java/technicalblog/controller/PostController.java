package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import technicalblog.model.Post;
import technicalblog.service.PostService;
import technicalblog.model.User;
import technicalblog.model.Category;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("/posts")
    public String getAllPosts(Model model){
        /*ArrayList<Post> posts=new ArrayList<>();
        Post latestPost=postService.getOnePost();
        posts.add(latestPost);
        model.addAttribute("posts",posts);
        return "posts";*/
        List<Post> posts=postService.postService();
        model.addAttribute("posts",posts);
        return "posts";

    }

    @RequestMapping("/post/create")
    public String createPost(){
        return "Posts/create";
    }

    @RequestMapping(value="/Posts/create",method = RequestMethod.POST)
    public String submitPost(Post newPost, HttpSession session){
        User user=(User)session.getAttribute("loggedUser");
        newPost.setUser(user);

        if (newPost.getSpringBlog() != null) {
            Category springBlogCategory = new Category();
            springBlogCategory.setCategory(newPost.getSpringBlog());
            newPost.getCategories().add(springBlogCategory);
        }

        if (newPost.getJavaBlog() != null) {
            Category javaBlogCategory = new Category();
            javaBlogCategory.setCategory(newPost.getJavaBlog());
            newPost.getCategories().add(javaBlogCategory);
        }

        postService.createPost(newPost);
        return "redirect:/posts";
    }

    @RequestMapping(value="/editPost",method = RequestMethod.GET)
    public String editPost(@RequestParam(name="postId") Integer postId,Model model){

       Post post= postService.getPost(postId);
       model.addAttribute("post",post);
       return "posts/edit";

    }

    @RequestMapping(value="/editPost",method = RequestMethod.PUT)
    public String editPostSubmit(@RequestParam(name="postId")Integer postId,Post Updatedpost,HttpSession session){
        User user=(User)session.getAttribute("loggedUser");
        Updatedpost.setId(postId);
        Updatedpost.setUser(user);

        if (Updatedpost.getSpringBlog() != null) {
            Category springBlogCategory = new Category();
            springBlogCategory.setCategory(Updatedpost.getSpringBlog());
            Updatedpost.getCategories().add(springBlogCategory);
        }

        if (Updatedpost.getJavaBlog() != null) {
            Category javaBlogCategory = new Category();
            javaBlogCategory.setCategory(Updatedpost.getJavaBlog());
            Updatedpost.getCategories().add(javaBlogCategory);
        }

        postService.updatePost(Updatedpost);
        return "redirect:/posts";
    }

    @RequestMapping(value="/deletePost",method = RequestMethod.DELETE)
    public String deletePost(@RequestParam(name="postId") Integer postId){
        postService.deletePost(postId);
        return "redirect:/posts";
    }
}
