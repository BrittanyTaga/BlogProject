package com.techtalentsouth.techtalentblog.BlogPost;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller //help with the way the page is being set up
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogPostRepository;
    private static List<BlogPost> posts = new ArrayList<>();

    @GetMapping("/")
    public String index(BlogPost blogPost, Model model){
        model.addAttribute("posts", posts);
        return "blogpost/index";
    }
    
    @GetMapping("/blogposts/new")
    public String newBlog (BlogPost blogPost){
        return "blogpost/new";
    }

    
    @PostMapping("/blogposts")
    public String addNewBlogPost(BlogPost blogPost, Model model){
        blogPostRepository.save(new BlogPost(blogPost.getTitle(), blogPost.getAuthor(), blogPost.getBlogEntry()));
        posts.add(blogPost);
        model.addAttribute("title", blogPost.getTitle());
	    model.addAttribute("author", blogPost.getAuthor());
	    model.addAttribute("blogEntry", blogPost.getBlogEntry());
    
	    return "blogpost/result";
    }

    //delete button is working
    @RequestMapping(value = "/blogposts/{id}", method = RequestMethod.DELETE)
    public String deletePostWithId(@PathVariable Long id, BlogPost blogPost) {
    
        blogPostRepository.deleteById(id);
        return "blogpost/index";
    
    }

}
