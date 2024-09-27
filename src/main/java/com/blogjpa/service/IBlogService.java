package com.blogjpa.service;

import com.blogjpa.model.Blog;
import com.blogjpa.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface IBlogService extends GenerateService<Blog>{
    Page<Blog>findAllBlog(Pageable pageable);
    Iterable<Blog> findBlogByTitle(String name);
    Iterable<Blog> findAllByCategory(Category category);
}
