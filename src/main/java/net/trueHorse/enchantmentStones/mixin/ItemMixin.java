package net.trueHorse.enchantmentStones.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.trueHorse.enchantmentStones.ItemAccess;
import net.trueHorse.enchantmentStones.config.EnchantmentStonesConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class ItemMixin implements ItemAccess {

    @Shadow @Final
    public abstract int getMaxCount();

    @Shadow
    public abstract boolean isDamageable();

    public boolean isEnchantable(ItemStack itemStack)
    {
        if(!Boolean.parseBoolean(EnchantmentStonesConfig.getVal("enchantableWithoutStone"))){
            return !itemStack.getOrCreateNbt().getList("Enchantment Stones",10).isEmpty()&&itemStack.getOrCreateNbt().getList("Enchantment Stones",10).size()<Integer.parseInt(EnchantmentStonesConfig.getVal("enchantmentsPerStone"));
        }else{
            return this.isEquipment(itemStack);
        }
    }

    @Override
    public boolean isEquipment(ItemStack itemStack){
        return this.getMaxCount() == 1 && this.isDamageable();
    }
}
