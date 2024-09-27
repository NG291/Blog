package com.blogjpa.formatter;

import com.blogjpa.model.Blog;
import com.blogjpa.service.impl.BlogService;
import org.springframework.format.Formatter;
;import java.text.ParseException;
import java.util.Locale;

public class BlogFormatter implements Formatter<Blog> {
    private BlogService blogService;
    public BlogFormatter(BlogService blogService){
        this.blogService= blogService;
    }
    @Override
    public Blog parse(String id, Locale locale) throws ParseException {
        return blogService.findById(Long.parseLong(id)).get();
    }

    @Override
    public String print(Blog object, Locale locale) {
        return "";
    }
}
