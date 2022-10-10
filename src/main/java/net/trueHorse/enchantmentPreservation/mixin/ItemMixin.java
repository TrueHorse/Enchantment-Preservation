package net.trueHorse.enchantmentPreservation.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.trueHorse.enchantmentPreservation.ItemAccess;
import net.trueHorse.enchantmentPreservation.config.EnchantmentPreservationConfig;
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
        if(!Boolean.parseBoolean(EnchantmentPreservationConfig.getVal("enchantableWithoutStone"))){
            return !itemStack.getOrCreateNbt().getList("Enchantment Stones",10).isEmpty()&&itemStack.getOrCreateNbt().getList("Enchantment Stones",10).size()<Integer.parseInt(EnchantmentPreservationConfig.getVal("enchantmentsPerStone"));
        }else{
            return this.isEquipment(itemStack);
        }
    }

    @Override
    public boolean isEquipment(ItemStack itemStack){
        return this.getMaxCount() == 1 && this.isDamageable();
    }
}
