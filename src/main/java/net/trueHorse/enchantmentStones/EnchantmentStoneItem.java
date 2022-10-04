package net.trueHorse.enchantmentStones;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.trueHorse.enchantmentStones.config.EnchantmentStonesConfig;

public class EnchantmentStoneItem extends Item {

    public EnchantmentStoneItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack){
        return itemStack.getEnchantments().size() < Integer.parseInt(EnchantmentStonesConfig.getVal("enchantmentsPerStone"));
    }

    @Override
    public int getEnchantability(){
        return 1;
    }
}
