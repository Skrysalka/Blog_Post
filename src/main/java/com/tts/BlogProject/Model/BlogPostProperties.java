package com.tts.BlogProject.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
//designate a plain old java object(POJO) class as an entity so you can use it with JPA services
public class BlogPostProperties {
	
	@Id //sets as primary key
	@GeneratedValue(strategy=GenerationType.AUTO)
	//allows the underlying DB set the value for id
	private long id;
	
	private String title;
	private String author;
	private String blogEntry;
	
	
	public BlogPostProperties() {
		//non-arg constructor needed for JPA
	}
	
	//All Arg Constructor: BlogPost must be created with title, author, and blogEntry
	public BlogPostProperties(String title, String author, String blogEntry) {
		this.title = title;
		this.author = author;
		this.blogEntry = blogEntry;
	}
	
	//Getters and Setters for the Variables bc they're private
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBlogEntry() {
		return blogEntry;
	}

	public void setBlogEntry(String blogEntry) {
		this.blogEntry = blogEntry;
	}
	
	//prints out the value of the Object(BlogPost) instead of its reference point Hashcode
	@Override
	public String toString() {
		return "BlogPostProperties [id=" + id + ", title=" + title + ", author=" + author + ", blogEntry=" + blogEntry
				+ "]";
	}
	
	
	
	

}
