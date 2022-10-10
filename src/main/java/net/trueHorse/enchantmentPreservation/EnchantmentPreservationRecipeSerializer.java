package net.trueHorse.enchantmentPreservation;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;

public class EnchantmentPreservationRecipeSerializer {

    public static RecipeSerializer<AddEnchantmentStoneRecipe> ADD_ENCHANTMENT_STONE;

    public static void registerSpecialRecipe(){
        ADD_ENCHANTMENT_STONE = RecipeSerializer.register("crafting_special_addenchantmentstone",new SpecialRecipeSerializer<>(AddEnchantmentStoneRecipe::new));
    }
}
