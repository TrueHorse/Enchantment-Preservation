package net.trueHorse.enchantmentPreservation;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;

public interface ItemStackAccess {

    void addEnchantmentStone(ItemStack stoneStack);

    void addEnchantmentFromStone(Enchantment enchantment, int level);

    NbtList getEnchantmentStones();
}
