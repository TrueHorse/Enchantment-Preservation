package net.trueHorse.enchantmentStones;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnchantmentStoneItem extends Item {

    public EnchantmentStoneItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack){
        return true;
    }

    @Override
    public int getEnchantability(){
        return 1;
    }
}
