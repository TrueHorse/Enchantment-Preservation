package net.trueHorse.enchantmentStones;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;

public class EnchantmentStonesRecipeSerializer {

    public static RecipeSerializer<AddEnchantmentStoneRecipe> ADD_ENCHANTMENT_STONE;

    public static void registerSpecialRecipe(){
        ADD_ENCHANTMENT_STONE = RecipeSerializer.register("crafting_special_addenchantmentstone",new SpecialRecipeSerializer<>(AddEnchantmentStoneRecipe::new));
    }
}
