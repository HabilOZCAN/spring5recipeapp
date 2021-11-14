package guru.springframework.spring5recipeapp.bootstrap;

import guru.springframework.spring5recipeapp.domain.*;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>(2);
//        get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");
        if (!dashUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (!pintUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not Found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not Found");
        }

//        get optionals

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure cupsUom = cupUomOptional.get();

//        get Categories

        Optional<Category> americanCategoryOptinal = categoryRepository.findByDescription("American");
        if (!americanCategoryOptinal.isPresent()) {
            throw new RuntimeException("Expected Category not Found");
        }

        Optional<Category> mexicanCategoryOptinal = categoryRepository.findByDescription("Mexican");
        if (!mexicanCategoryOptinal.isPresent()) {
            throw new RuntimeException("Expected Category not Found");
        }

        Category americanCategory = americanCategoryOptinal.get();
        Category mexicanCategory = mexicanCategoryOptinal.get();

//        Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1. kladfs;adjsf;lkafjds\n" +
                "2. ;lakfdj;alkjdf;alkjf;al\n" +
                "3. ;lakfdj;alkjdf;alkjf;al\n" +
                "4. ;lakfdj;alkjdf;alkjf;al\n" +
                "\n" +
                "\n" +
                "Read more: http://www.google.com");

        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("there is some notes are related what we do \n" +
                "feel free to ask whatever you want to learn about life\n" +
                "but at the end, you will realise that you are alone and together\n" +
                "\n" +
                "\n" +
                "Read more: http://www.google.com");

        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("ripe avacados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal(".5"), teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("Lime juice", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("Minced Onion", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("Serrano chiles", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("freshy pepper", new BigDecimal(2), dashUom));
        guacRecipe.addIngredient(new Ingredient("Ripe Tomato", new BigDecimal(".5"), eachUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

//        add to return list
        recipes.add(guacRecipe);

//        Yummy Tacos
        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Spicy Grilled Taco");
        tacoRecipe.setPrepTime(20);
        tacoRecipe.setCookTime(9);
        tacoRecipe.setDifficulty(Difficulty.MODERATE);
        tacoRecipe.setDirections("1. kladfs;adjsf;lkafjds\n" +
                "2 ;lakfdj;alkjdf;alkjf;al\n" +
                ";lakfdj;alkjdf;alkjf;al\n" +
                "\n" +
                "\n" +
                "3 ;lakfdj;alkjdf;alkjf;al\n" +
                ";lakfdj;alkjdf;alkjf;al\n" +
                "4 ;lakfdj;alkjdf;alkjf;al\n" +
                ";lakfdj;alkjdf;alkjf;al\n" +
                "5 ;lakfdj;alkjdf;alkjf;al\n" +
                "\n" +
                "\n" +
                "read more: http://www.google.com");

        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("TACos is some notes are related what we do \n" +
                "TACos free to ask whatever you want to learn about life\n" +
                "TACos at the end, you will realise that you are alone and together\n" +
                "TACos free to ask whatever you want to learn about life\n" +
                "TACos at the end, you will realise that you are alone and together\n" +
                "\n" +
                "\n" +
                "read more: http://www.google.com");

        tacoRecipe.setNotes(tacoNotes);

        tacoRecipe.addIngredient(new Ingredient("Ancho chili powder", new BigDecimal(2), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), teaSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Dried cumin", new BigDecimal(1), teaSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Sugar", new BigDecimal(1), teaSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Salt", new BigDecimal(".5"), teaSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Clove of Garlic", new BigDecimal(1), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Finely grated orange", new BigDecimal(1), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Fresh-squeezed Orange", new BigDecimal(3), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Olive Oil", new BigDecimal(2), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Boneless chicken", new BigDecimal(4), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("Small Corn tortillas", new BigDecimal(8), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Packed baby arugula", new BigDecimal(3), cupsUom));
        tacoRecipe.addIngredient(new Ingredient("Medium riped Avocados", new BigDecimal(2), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Radishes", new BigDecimal(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Cherry", new BigDecimal(".5"), pintUom));
        tacoRecipe.addIngredient(new Ingredient("Red onion", new BigDecimal(".25"), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", new BigDecimal(4), eachUom));
        tacoRecipe.addIngredient(new Ingredient("Cup sour cream", new BigDecimal(4), cupsUom));
        tacoRecipe.addIngredient(new Ingredient("Lime", new BigDecimal(4), eachUom));

        tacoRecipe.getCategories().add(americanCategory);
        tacoRecipe.getCategories().add(mexicanCategory);

        recipes.add(tacoRecipe);
        return recipes;
    }
}
