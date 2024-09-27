package com.blogjpa.controller;

import com.blogjpa.model.Blog;
import com.blogjpa.model.Category;
import com.blogjpa.service.IBlogService;
import com.blogjpa.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IBlogService iBlogService;
    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> listProvinces() {
        return categoryService.findAll();
    }

    @GetMapping
    public String list(Pageable pageable, Model model, @RequestParam(defaultValue = "createAt") String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        pageable = PageRequest.of(pageable.getPageNumber(), 4, sort);
        Page<Blog> blogs = iBlogService.findAllBlog(pageable);
        model.addAttribute("blogs", blogs);
        return "/blog/list";
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        model.addAttribute("blog", new Blog());
        return "/blog/create";
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("blog") Blog blog, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("redirect:/blog");
        if (bindingResult.hasErrors()) {
            modelAndView = new ModelAndView("/blog/create");
            return modelAndView;
        }
        iBlogService.save(blog);
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public String showFormUpdate(@PathVariable("id") Long id, Model model) {
        Optional<Blog> blog = iBlogService.findById(id);
        model.addAttribute("blog", blog.get());
        return "/blog/update";
    }

    @PostMapping("/update")
    public String update(Model model, Blog blog) {
        iBlogService.save(blog);
        return "redirect:/blog";
    }

    @GetMapping("/{id}/delete")
    public String showFormDelete(@PathVariable("id") Long id, Model model) {
        Optional<Blog> blog = iBlogService.findById(id);
        model.addAttribute("blog", blog.get());
        return "/blog/delete";
    }

    @PostMapping("/delete")
    public String delete(Model model, Blog blog) {
        iBlogService.remove(blog.getId());
        return "redirect:/blog";
    }

    @GetMapping("/{id}/view")
    public String showView(Model model, @RequestParam("id") Long id) {
        Optional<Blog> blog = iBlogService.findById(id);
        model.addAttribute("blog", blog.get());
        return "/blog/view";
    }

    @GetMapping("/search")
    public String showSearchForm() {
        return "/blog/list";
    }

    @PostMapping("/search")
    public String searchBlogs(@RequestParam("title") String title, Model model) {
        Iterable<Blog> blogs = iBlogService.findBlogByTitle(title);
        if (!blogs.iterator().hasNext()) {
            model.addAttribute("message", "No blogs found with the title: " + title);
        }
        model.addAttribute("blogs", blogs);
        return "/blog/listTitle";
    }

//    @GetMapping("/se")
//    public String showSearchCategory() {
//        return "/blog/list";
//    }
//    @PostMapping("/searchCategory")
//    public String searchCategory(@RequestParam("Category") String category, Model model) {
//        Iterable<Blog> blogs = iBlogService.findBlogByCategory(category);
//        if (!blogs.iterator().hasNext()) {
//            model.addAttribute("message", "No blogs found with the title: " +category );
//        }
//        model.addAttribute("blogs", blogs);
//        return "redirect:/blog";
//    }

}