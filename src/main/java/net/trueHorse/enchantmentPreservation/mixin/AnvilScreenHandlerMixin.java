package net.trueHorse.enchantmentPreservation.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtList;
import net.minecraft.screen.*;
import net.trueHorse.enchantmentPreservation.EnchantmentPreservation;
import net.trueHorse.enchantmentPreservation.ItemStackAccess;
import net.trueHorse.enchantmentPreservation.config.EnchantmentPreservationConfig;
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
        if(EnchantmentPreservation.ENCHANTMENT_STONES.contains(itemStack.getItem())){
            return itemStack.getEnchantments().size()<Integer.parseInt(EnchantmentPreservationConfig.getVal("enchantmentsPerStone"));
        }else{
            return itemStack.isDamageable();
        }
    }

    @Inject(method = "updateResult",at=@At(value = "INVOKE",target = "Lnet/minecraft/enchantment/EnchantmentHelper;get(Lnet/minecraft/item/ItemStack;)Ljava/util/Map;",ordinal = 1,shift = At.Shift.BEFORE),locals = LocalCapture.CAPTURE_FAILEXCEPTION,cancellable = true)
    private void cancleIfNotEnchatableOrMultiple(CallbackInfo info, ItemStack itemStack, int i, int j, int k, ItemStack itemStack2, ItemStack itemStack3, Map<Enchantment,Integer> map, boolean bl){
        if(EnchantmentPreservation.ENCHANTMENT_STONES.contains(itemStack.getItem())&&itemStack.getCount()!=1){
            this.output.setStack(0, ItemStack.EMPTY);
            this.levelCost.set(0);
            info.cancel();
        }

        if(!Boolean.parseBoolean(EnchantmentPreservationConfig.getVal("enchantableWithoutStone"))&&bl&&!EnchantmentPreservation.ENCHANTMENT_STONES.contains(itemStack.getItem())&&((ItemStackAccess)(Object)itemStack).getEnchantmentStones().isEmpty()){
            this.output.setStack(0, ItemStack.EMPTY);
            this.levelCost.set(0);
            info.cancel();
        }
    }

    @Inject(method = "updateResult",at=@At(value = "INVOKE",target = "Lnet/minecraft/enchantment/EnchantmentHelper;set(Ljava/util/Map;Lnet/minecraft/item/ItemStack;)V",shift = At.Shift.BEFORE),locals = LocalCapture.CAPTURE_FAILEXCEPTION,cancellable = true)
    private void transferStones(CallbackInfo info, ItemStack itemStack, int i, int j, int k, ItemStack itemStack2, ItemStack itemStack3){
        if(!((ItemStackAccess)(Object)itemStack3).getEnchantmentStones().isEmpty()){
            NbtList stoneList1 = ((ItemStackAccess)(Object)itemStack2).getEnchantmentStones().copy();
            NbtList stoneList2 = ((ItemStackAccess)(Object)itemStack3).getEnchantmentStones().copy();
            if(stoneList1.size()+stoneList2.size()>Integer.parseInt(EnchantmentPreservationConfig.getVal("stonesPerEquip"))){
                this.output.setStack(0, ItemStack.EMPTY);
                this.levelCost.set(0);
                info.cancel();
            }else{
                stoneList1.addAll(stoneList2);
                itemStack2.putSubTag("Enchantment Stones",stoneList1);
            }
        }
    }
}
