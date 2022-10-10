package net.trueHorse.enchantmentPreservation.mixin;

import net.minecraft.item.Item;
import net.trueHorse.enchantmentPreservation.EnchantmentPreservation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = {"net/minecraft/enchantment/EnchantmentTarget$1","net/minecraft/enchantment/EnchantmentTarget$2","net/minecraft/enchantment/EnchantmentTarget$3","net/minecraft/enchantment/EnchantmentTarget$4","net/minecraft/enchantment/EnchantmentTarget$5","net/minecraft/enchantment/EnchantmentTarget$6","net/minecraft/enchantment/EnchantmentTarget$7","net/minecraft/enchantment/EnchantmentTarget$8","net/minecraft/enchantment/EnchantmentTarget$9","net/minecraft/enchantment/EnchantmentTarget$10","net/minecraft/enchantment/EnchantmentTarget$11","net/minecraft/enchantment/EnchantmentTarget$12","net/minecraft/enchantment/EnchantmentTarget$13","net/minecraft/enchantment/EnchantmentTarget$14"})
@Pseudo
public class EnchantmentTargetMixin {

    @Inject(method = "isAcceptableItem",at=@At(value = "HEAD"),cancellable = true)
    private void isEnchantmentStone(Item item, CallbackInfoReturnable<Boolean> info){
        if(item.getDefaultStack().isIn(EnchantmentPreservation.ENCHANTMENT_STONES)){
            info.setReturnValue(true);
        }
    }
}
