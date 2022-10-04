package net.trueHorse.enchantmentStones.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.*;
import net.trueHorse.enchantmentStones.EnchantmentStones;
import net.trueHorse.enchantmentStones.config.EnchantmentStonesConfig;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler{

    @Shadow@Final
    private Property levelCost;

    //This is only here, because of the extension of ForgingScreenHandler
    public AnvilScreenHandlerMixin(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    @Redirect(method = "updateResult", at=@At(value = "INVOKE",target = "Lnet/minecraft/item/ItemStack;isDamageable()Z",ordinal = 1))
    private boolean isAnvilable(ItemStack itemStack){
        if(itemStack.isIn(EnchantmentStones.ENCHANTMENT_STONES)){
            return itemStack.getOrCreateNbt().getList("StoredEnchantments",10).size()<Integer.parseInt(EnchantmentStonesConfig.getVal("enchantmentsPerStone"));
        }else{
            return itemStack.isDamageable();
        }
    }

    @Inject(method = "updateResult",at=@At(value = "INVOKE",target = "Lnet/minecraft/enchantment/EnchantmentHelper;get(Lnet/minecraft/item/ItemStack;)Ljava/util/Map;",ordinal = 1,shift = At.Shift.BEFORE),locals = LocalCapture.CAPTURE_FAILEXCEPTION,cancellable = true)
    private void cancleIfNotEnchatableAndBl(CallbackInfo info, ItemStack itemStack, int i, int j, int k, ItemStack itemStack2, ItemStack itemStack3, Map<Enchantment,Integer> map, boolean bl){
        if(!Boolean.parseBoolean(EnchantmentStonesConfig.getVal("enchantableWithoutStone"))&&bl&&!itemStack.isIn(EnchantmentStones.ENCHANTMENT_STONES)&&itemStack.getOrCreateNbt().getList("Enchantment Stones",10).isEmpty()){
            this.output.setStack(0, ItemStack.EMPTY);
            this.levelCost.set(0);
            info.cancel();
        }
    }
}
