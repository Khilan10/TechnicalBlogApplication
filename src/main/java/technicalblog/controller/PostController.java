package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import technicalblog.model.Post;
import technicalblog.service.PostService;

import java.util.ArrayList;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("posts")
    public String getAllPosts(Model model){
        ArrayList<Post> posts=postService.getOnePost();
        model.addAttribute("posts",posts);
        return "posts";
    }

    @RequestMapping("/post/create")
    public String createPost(){
        return "Posts/create";
    }

    @RequestMapping(value="/Posts/create",method = RequestMethod.POST)
    public String submitPost(Post newPost){
        postService.createPost(newPost);
        return "redirect:/posts";
    }

}
