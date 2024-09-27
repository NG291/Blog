package com.blogjpa.repository;

import com.blogjpa.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryRepository extends CrudRepository<Category,Long> {
}
