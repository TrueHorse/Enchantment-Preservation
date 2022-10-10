package net.trueHorse.enchantmentPreservation.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.trueHorse.enchantmentPreservation.config.EnchantmentPreservationConfig;

public class EnchantmentStoneItem extends Item {

    public EnchantmentStoneItem(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack){
        return itemStack.getEnchantments().size() < Integer.parseInt(EnchantmentPreservationConfig.getVal("enchantmentsPerStone"));
    }

    @Override
    public int getEnchantability(){
        return 1;
    }
}
