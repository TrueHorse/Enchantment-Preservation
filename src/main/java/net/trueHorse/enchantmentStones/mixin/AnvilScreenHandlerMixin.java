package net.trueHorse.enchantmentStones.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.trueHorse.enchantmentStones.EnchantmentStones;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {

    @Redirect(method = "updateResult", at=@At(value = "INVOKE",target = "Lnet/minecraft/item/ItemStack;isDamageable()Z",ordinal = 1))
    private boolean isAnvilable(ItemStack itemStack){
        if(itemStack.isIn(EnchantmentStones.ENCHANTMENT_STONES)){
            return true;
        }else{
            return itemStack.isDamageable();
        }
    }
}
