package com.tts.BlogProject.Repository;

import org.springframework.data.repository.CrudRepository;

import com.tts.BlogProject.Model.BlogPostProperties;

public interface BlogPostRepository extends CrudRepository<BlogPostProperties, Long> {

}
