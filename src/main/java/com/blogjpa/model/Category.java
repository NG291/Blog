package com.blogjpa.model;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.*;
;

@Entity
@Table(name ="category")
public class Category implements Validator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    public Category(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
      Category category = (Category)  target;
      String name = category.getName();
      if(name==null|| "".equals(name)){
        errors.rejectValue("name", "error.name.blank","ten category khong duoc de trong");
      }
    }
}
