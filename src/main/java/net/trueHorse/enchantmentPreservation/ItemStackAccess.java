package net.trueHorse.enchantmentPreservation;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;

public interface ItemStackAccess {

    void addEnchantmentStone(ItemStack stoneStack);

    void addEnchantmentFromStone(Enchantment enchantment, int level);
}
