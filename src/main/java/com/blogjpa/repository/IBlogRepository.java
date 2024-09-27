package com.blogjpa.repository;

import com.blogjpa.model.Blog;
import com.blogjpa.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface IBlogRepository  extends CrudRepository<Blog,Long> {
    Page<Blog> findAll(Pageable pageable);
    Iterable<Blog> findByTitleContainingIgnoreCase(String name);
    Iterable<Blog> findAllByCategory(Category category);
}
