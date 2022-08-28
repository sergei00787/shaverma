package com.jbond.shaurmito.repo;

import com.jbond.shaurmito.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
    }
