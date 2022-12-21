package net.trueHorse.enchantmentPreservation;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;

public class Utils {

    public static NbtCompound createEnchantmentstoneNbt(ItemStack stoneStack){
        NbtCompound nbtCompound = new NbtCompound();
        nbtCompound.putString("StoneId", Registries.ITEM.getId(stoneStack.getItem()).toString());
        nbtCompound.put("StoredEnchantments", stoneStack.getOrCreateNbt().getList("StoredEnchantments",10));
        return nbtCompound;
    }
}
