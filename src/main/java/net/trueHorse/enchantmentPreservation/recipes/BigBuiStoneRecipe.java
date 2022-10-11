package net.trueHorse.enchantmentPreservation.recipes;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.trueHorse.enchantmentPreservation.items.EnchantmentPreservationItems;

import java.util.List;
import java.util.Random;

public class BigBuiStoneRecipe extends SpecialCraftingRecipe {

    public BigBuiStoneRecipe(Identifier id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        boolean redHalf = false;
        boolean blackHalf = false;

        for(int i=0;i<inventory.size();i++) {
            ItemStack stack = inventory.getStack(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() == EnchantmentPreservationItems.BIGS_BLACK_HALF && !blackHalf) {
                    blackHalf = true;
                    continue;
                }
                if (stack.getItem()==EnchantmentPreservationItems.BIGS_RED_HALF && !redHalf) {
                    redHalf = true;
                    continue;
                }
                return false;
            }
        }
        return redHalf&&blackHalf;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        ItemStack stack = new ItemStack(EnchantmentPreservationItems.BIG_BUI_STONE);
        List<EnchantmentLevelEntry> enchants = EnchantmentHelper.generateEnchantments(new Random(), Items.DIAMOND_SWORD.getDefaultStack(),50,false);
        enchants.forEach(e -> stack.addEnchantment(e.enchantment,e.level+2));
        return stack;
    }

    @Override
    public boolean fits(int width, int height) {
        return width*height>1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return EnchantmentPreservationRecipeSerializer.ENCHANTED_BIG_BUI_STONE;
    }
}
