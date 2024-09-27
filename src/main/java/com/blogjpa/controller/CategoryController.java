package com.blogjpa.controller;

import com.blogjpa.model.Blog;
import com.blogjpa.model.Category;
import com.blogjpa.service.IBlogService;
import com.blogjpa.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IBlogService iBlogService;

    @GetMapping
    public ModelAndView showListCategory() {
        ModelAndView modelAndView = new ModelAndView("/category/list");
        Iterable<Category> categories = categoryService.findAll();
        modelAndView.addObject("categories", categories);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showFormCreate() {
        ModelAndView modelAndView = new ModelAndView("/category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }

    @PostMapping("save")
    public ModelAndView save(@ModelAttribute("category") Category category, BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("redirect:/categories");
        new Category().validate(category,result);
        if(result.hasErrors()){
            modelAndView = new ModelAndView("/category/create");
        }
        categoryService.save(category);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showFormUpdate(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/category/update");
        Optional<Category> findById = categoryService.findById(id);
        modelAndView.addObject("category", findById.get());
        return modelAndView;
    }
    @PostMapping("/update")
    public ModelAndView update( @ModelAttribute("category") Category category){
        ModelAndView modelAndView = new ModelAndView("redirect:/categories");
        categoryService.save(category);
        return modelAndView;
    }
    @GetMapping("/{id}/view")
    public ModelAndView view(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("/blog/list");
        Optional<Category> findById = categoryService.findById(id);
        if(!findById.isPresent()){
            return new ModelAndView("/error_404");
        }
        Iterable<Blog> blogs = iBlogService.findAllByCategory(findById.get());
        modelAndView.addObject("blogs", blogs);
             return modelAndView;
    }
}
