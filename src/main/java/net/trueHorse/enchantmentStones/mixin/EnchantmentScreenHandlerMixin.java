package net.trueHorse.enchantmentStones.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.trueHorse.enchantmentStones.EnchantmentStones;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EnchantmentScreenHandler.class)
public abstract class EnchantmentScreenHandlerMixin {

    @Redirect(method = "generateEnchantments",at=@At(value = "INVOKE",target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean isOfBookOrEnchantmentStone(ItemStack instance, Item item){
        return instance.isOf(item)||instance.isIn(EnchantmentStones.ENCHANTMENT_STONES);
    }
}
