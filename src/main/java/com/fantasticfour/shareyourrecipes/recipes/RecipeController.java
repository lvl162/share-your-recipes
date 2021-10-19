package com.fantasticfour.shareyourrecipes.recipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fantasticfour.shareyourrecipes.domains.recipes.Recipe;
import com.fantasticfour.shareyourrecipes.recipes.dtos.CreateRecipeDTO;
import com.fantasticfour.shareyourrecipes.recipes.dtos.RecipeDTO;
import com.fantasticfour.shareyourrecipes.recipes.services.RecipeService;
import com.fantasticfour.shareyourrecipes.user.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {

    private final RecipeService recipeService;
    private final UserRepo userRepo;

    @Autowired
    public RecipeController(RecipeService recipeService, UserRepo userRepo) {
        this.recipeService = recipeService;
        this.userRepo = userRepo;
    }

    @GetMapping("/test/recipes")
    public List<RecipeDTO> recipe() {
        List<RecipeDTO> recipes = recipeService.findAll();

        System.out.println(recipes);
        // model.addAttribute("recipes", recipes);

        return recipes;
    }

    @GetMapping("/test/createRecipe")
    public CreateRecipeDTO createRecipe(CreateRecipeDTO recipe) {
        CreateRecipeDTO r = new CreateRecipeDTO();
        r.setTitle("helloo baby");
        r.setImage("image");
        r.setGuideVideoString("guideVideoString");
        r.setCreatorId(userRepo.findByEmail("admin@lvl.gg").get().getId());
        // r.setCreator(userRepo.findByEmail("admin@lvl.gg").get());
        Map<String, String> ingredients = new HashMap<>();

        ingredients.put("Hanh`", "100g");
        ingredients.put("Hanh`1", "100g");
        ingredients.put("Hanh`2", "100g");
        ingredients.put("Hanh`3", "100g");

        List<String> steps = new ArrayList<>();

        steps.add("Buowc1: dot ");
        steps.add("Buowc2: dot ");
        steps.add("Buowc3: dot ");
        steps.add("Buowc4: dot ");
        r.setIngredients(ingredients);
        r.setSteps(steps);

        // recipeService.createRecipe(r);
        recipe = r;
        recipeService.createRecipe(recipe);
        return recipe;
    }

    @GetMapping("/test/deleteRecipe/{idRecipe}")
    public Recipe deleteRecipe(@PathVariable("idRecipe") String idRecipe) {
        Long id = Long.parseLong(idRecipe);
        return recipeService.deleteRecipe(id);
        
    }

    @GetMapping("/test/findRecipeById/{idRecipe}")
    public RecipeDTO findRecipeById(@PathVariable("idRecipe") String idRecipe) {
        Long id = Long.parseLong(idRecipe);
        RecipeDTO  recipeDTO = recipeService.viewRecipeById(id);
        return recipeDTO;
        
    }


}
