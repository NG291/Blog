package com.blogjpa.service.impl;

import com.blogjpa.model.Category;
import com.blogjpa.repository.IBlogRepository;
import com.blogjpa.model.Blog;
import com.blogjpa.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository iBlogRepository;

    @Override
    public Iterable<Blog> findAll() {
        return iBlogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return iBlogRepository.findById(id);
    }

    @Override
    public void save(Blog blog) {
        iBlogRepository.save(blog);
    }

    @Override
    public void remove(Long id) {
        iBlogRepository.deleteById(id);
    }

    @Override
    public Page<Blog> findAllBlog(Pageable pageable) {
        return iBlogRepository.findAll(pageable);
    }

    @Override
    public Iterable<Blog> findBlogByTitle(String name) {
        return iBlogRepository.findByTitleContainingIgnoreCase(name);
    }

    @Override
    public Iterable<Blog> findAllByCategory(Category category) {
        return iBlogRepository.findAllByCategory(category);
    }

}
