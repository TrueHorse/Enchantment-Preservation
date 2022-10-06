package net.trueHorse.enchantmentStones.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.item.ItemStack;
import net.trueHorse.enchantmentStones.EnchantmentStones;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {

    @Shadow@Final
    public EnchantmentTarget type;

    public boolean isAcceptableItem(ItemStack stack) {
        return stack.isIn(EnchantmentStones.ENCHANTMENT_STONES) || this.type.isAcceptableItem(stack.getItem());
    }
}
