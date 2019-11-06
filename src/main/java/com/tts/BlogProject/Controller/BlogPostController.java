package com.tts.BlogProject.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	//An ArrayList of BlogPosts called posts
	private static List<BlogPostProperties> posts = new ArrayList<>();
	
	@GetMapping(value="/")
	//Method named Index that returns a specific template called "index" in the blogpost template directory
	public String index(BlogPostProperties blogPostProperties, Model model) {
		model.addAttribute("posts", posts);
		return "blogpost/index";
	}
	
	@GetMapping(value = "/blog_posts/new")
	public String newBlog (BlogPostProperties blogPostProperties) {
		return "blogpost/new";
	}
	
	@PostMapping(value = "/blog_posts/new")
	//Set up Method that will take in data entered in the form and add it to the DB
	//Method will POST info to DB and display "confirmation" on a new html page called "result"
	public String addNewBlogPost(BlogPostProperties blogPostProperties, Model model) {
		//this will save the attributes to the DB through the repository interface
		blogPostRepository.save(new BlogPostProperties(blogPostProperties.getTitle(), blogPostProperties.getAuthor(), blogPostProperties.getBlogEntry()));
		//Every new blog post will be added to the "posts" ArrayList
		posts.add(blogPostProperties);
		model.addAttribute("title", blogPostProperties.getTitle());
		model.addAttribute("author", blogPostProperties.getAuthor());
		model.addAttribute("blogEntry", blogPostProperties.getBlogEntry());
		return "blogpost/result";
	}
	

}
