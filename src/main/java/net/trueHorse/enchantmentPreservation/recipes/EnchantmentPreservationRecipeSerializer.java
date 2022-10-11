package net.trueHorse.enchantmentPreservation.recipes;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;

public class EnchantmentPreservationRecipeSerializer {

    public static RecipeSerializer<AddEnchantmentStoneRecipe> ADD_ENCHANTMENT_STONE;
    public static RecipeSerializer<BigBuiStoneRecipe> ENCHANTED_BIG_BUI_STONE;


    public static void registerSpecialRecipe(){
        ADD_ENCHANTMENT_STONE = RecipeSerializer.register("crafting_special_addenchantmentstone",new SpecialRecipeSerializer<>(AddEnchantmentStoneRecipe::new));
        if(true){
            ENCHANTED_BIG_BUI_STONE = RecipeSerializer.register("crafting_special_enchantedbigbuistone",new SpecialRecipeSerializer<>(BigBuiStoneRecipe::new));
        }
    }
}
