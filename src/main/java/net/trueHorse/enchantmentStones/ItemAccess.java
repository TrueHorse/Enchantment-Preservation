package net.trueHorse.enchantmentStones;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public interface ItemAccess {

    boolean isEquipment(ItemStack itemStack);

    ArrayList<EnchantmentStoneItem> getAddedEnchantmentStones();
}
