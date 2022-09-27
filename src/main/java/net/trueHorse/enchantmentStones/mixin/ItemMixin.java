package net.trueHorse.enchantmentStones.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.trueHorse.enchantmentStones.ItemAccess;
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
        if(false){
            return false;
        }else{
            return this.getMaxCount() == 1 && this.isDamageable();
        }
    }

    @Override
    public boolean isEquipment(ItemStack itemStack){
        return this.getMaxCount() == 1 && this.isDamageable();
    }
}
