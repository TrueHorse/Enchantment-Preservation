package net.trueHorse.enchantmentStones;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.registry.Registry;

public class Utils {

    public static NbtCompound createEnchantmentstoneNbt(ItemStack stoneStack){
        NbtCompound nbtCompound = new NbtCompound();
        nbtCompound.putString("StoneId", Registry.ITEM.getId(stoneStack.getItem()).toString());
        nbtCompound.put("Enchantments", stoneStack.getOrCreateNbt().getList("Enchantments",10));
        return nbtCompound;
    }
}
