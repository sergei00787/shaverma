package com.jbond.shaurmito.controllers;

import com.jbond.shaurmito.entity.Ingredient;
import com.jbond.shaurmito.entity.Order;
import com.jbond.shaurmito.entity.Shaverma;
import com.jbond.shaurmito.repo.IngredientRepository;
import com.jbond.shaurmito.repo.ShavermaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class ShavermaController {
    private final IngredientRepository ingredientRepository;
    private ShavermaRepository shavermaRepository;

    @Autowired
    public ShavermaController(IngredientRepository ingredientRepository, ShavermaRepository shavermaRepository) {
        this.ingredientRepository = ingredientRepository;
        this.shavermaRepository = shavermaRepository;
    }

    @ModelAttribute(name = "shaverma")
    public Shaverma shaverma() {
        return new Shaverma();
    }

    @ModelAttribute("order")
    public Order order() {
        return new Order();
    }

    @GetMapping
    public String showShavermaForm(Model model){

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(ingr -> ingredients.add(ingr));

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        return "designShaverma";
    }

    @PostMapping
    public String processShaverma(@Valid Shaverma shaverma, Errors errors, @ModelAttribute Order order){
        log.info(shaverma.toString());

        if (errors.hasErrors()) {
            log.info(errors.toString());
            return "designShaverma";
        }

        shaverma.createdAt();
        Shaverma savedShaverma = shavermaRepository.save(shaverma);
        order.addDesignShaverma(savedShaverma);

        log.info(savedShaverma.toString());
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }


}
