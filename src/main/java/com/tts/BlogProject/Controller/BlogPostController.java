package com.tts.BlogProject.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tts.BlogProject.Model.BlogPostProperties;
import com.tts.BlogProject.Repository.BlogPostRepository;

//the Controller will determine how the data and user will move through the project
//provides a connection btwn templates(browser view) and the data from our DB

@Controller
//help send output to a template
public class BlogPostController {
	
	//Model to reference as far as inserting info to DB
	private BlogPostProperties blogPostProperties;
	
	//Add BlogPost Repository to the Controller
	@Autowired
	private BlogPostRepository blogPostRepository;
	
	//An ArrayList of BlogPosts called posts thats created in the Application itself, not the DB
	//private static List<BlogPostProperties> posts = new ArrayList<>();
	
	@GetMapping(value="/")
	//Method named Index that returns a specific template called "index" in the blogpost template directory
	public String index(BlogPostProperties blogPostProperties, Model model) {
		//Mistake: model.addAttribute("posts", posts) just displays the posts List and doesnt actually retrieve data from the DB
		//The name "repo" is what you call upon in your index.html to call upon DB and assign to value "blog" so you can subsequently call upon blog.title, blog.title etc
		model.addAttribute("repo", blogPostRepository.findAll());
		return "blogpost/index";
	}
	
	@GetMapping(value = "/blog_posts/new")
	public String newBlog (BlogPostProperties blogPostProperties) {
		return "blogpost/new";
	}
	
	//Get the Post. Path Variable extracts the {id} and assign it to Long id
	@GetMapping(value="/blog_posts/update/{id}")
	public String updateBlogPost(@PathVariable Long id, Model model) {
		//Optional means that it might find the result but it might not
		Optional<BlogPostProperties> result = blogPostRepository.findById(id);
		BlogPostProperties blogPostProperties = null;
		
		//check if result aka id exist, True
		if(result.isPresent()) {
			blogPostProperties = result.get();
		} else {
			throw new RuntimeException("Did not find post id" + id);
		}
		
		//Set the post as a model attribute
		model.addAttribute("blogPostProperties", blogPostProperties);
		//send it across to form
		return "blogpost/new";
	}
	
	@GetMapping("/blog_posts/delete/{id}")
	public String deletePostById(@PathVariable Long id) {
		blogPostRepository.deleteById(id);
		return "redirect:/";
	}
	
	@PostMapping(value = "/blog_posts/new")
	//Set up Method that will take in data entered in the form and add it to the DB
	//Method will POST info to DB and display "confirmation" on a new html page called "result"
	public String addNewBlogPost(BlogPostProperties blogPostProperties, Model model) {
		//this will save the attributes to the DB through the repository interface
		//blogPostRepository.save(new BlogPostProperties(blogPostProperties.getTitle(), blogPostProperties.getAuthor(), blogPostProperties.getBlogEntry()));
		//Every new blog post will be added to the "posts" ArrayList
		//posts.add(blogPostProperties);
		blogPostRepository.save(blogPostProperties);
		model.addAttribute("title", blogPostProperties.getTitle());
		model.addAttribute("author", blogPostProperties.getAuthor());
		model.addAttribute("blogEntry", blogPostProperties.getBlogEntry());
		return "blogpost/result";
	}
	
}
