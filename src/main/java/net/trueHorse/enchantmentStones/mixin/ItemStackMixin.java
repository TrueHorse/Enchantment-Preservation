package net.trueHorse.enchantmentStones.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.trueHorse.enchantmentStones.EnchantmentStones;
import net.trueHorse.enchantmentStones.ItemStackAccess;
import net.trueHorse.enchantmentStones.Utils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ItemStackAccess {

    @Shadow
    private NbtCompound nbt;
    @Shadow
    public abstract NbtCompound getOrCreateNbt();

    /*
    @Inject(method = "onClicked",at=@At(value = "HEAD"))
    private void testStones(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference, CallbackInfoReturnable<Boolean> info){
        EnchantmentStones.LOGGER.error(String.valueOf(stack.getOrCreateNbt().contains("Enchantment Stones")));
    }
     */

    @Shadow public abstract Item getItem();

    @Shadow public abstract boolean isIn(TagKey<Item> tag);

    @Inject(method = "damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V",at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V",shift = At.Shift.BEFORE))
    private <T> void pickUpEnchantmentStonesOnBreak(int amount, LivingEntity entity, Consumer<T> breakCallback, CallbackInfo info) {

        if (entity instanceof PlayerEntity) {
            NbtList stoneList = this.nbt.getList("Enchantment Stones",10);

            for (NbtElement stoneNbt : stoneList) {
                ItemStack stone = Registry.ITEM.get(Identifier.tryParse(((NbtCompound)stoneNbt).getString("StoneId"))).getDefaultStack();
                stone.getOrCreateNbt().put("StoredEnchantments",((NbtCompound)stoneNbt).getList("Enchantments",10));

                ((PlayerEntity) entity).giveItemStack(stone);
            }
        }
    }

    //get,has add
    @Inject(method = "getEnchantments",at=@At("HEAD"),cancellable = true)
    private void getStoredEnchantments(CallbackInfoReturnable<NbtList> info){
        if(this.isIn(EnchantmentStones.ENCHANTMENT_STONES)){
            info.setReturnValue(this.nbt != null ? this.nbt.getList("StoredEnchantments", 10) : new NbtList());
        }
    }

    @Inject(method = "hasEnchantments",at=@At("HEAD"),cancellable = true)
    private void hasStoredEnchantments(CallbackInfoReturnable<Boolean> info){
        if(this.isIn(EnchantmentStones.ENCHANTMENT_STONES)){
            if (this.nbt != null && this.nbt.contains("StoredEnchantments", 9)) {
                info.setReturnValue(!this.nbt.getList("StoredEnchantments", 10).isEmpty());
            } else {
                info.setReturnValue(false);
            }
        }
    }

    @Inject(method = "addEnchantment",at=@At("HEAD"),cancellable = true)
    private void addStoredEnchantment(Enchantment enchantment, int lvl, CallbackInfo info){
        EnchantmentStones.LOGGER.error("made it");
        if(this.isIn(EnchantmentStones.ENCHANTMENT_STONES)){
            this.getOrCreateNbt();
            if (!this.nbt.contains("StoredEnchantments", 9)) {
                this.nbt.put("StoredEnchantments", new NbtList());
            }

            NbtList nbtList = this.nbt.getList("StoredEnchantments", 10);
            nbtList.add(EnchantmentHelper.createNbt(EnchantmentHelper.getEnchantmentId(enchantment), (byte)lvl));
            info.cancel();
        }
    }

    @Override
    public void addEnchantmentStone(ItemStack stoneStack){
        this.getOrCreateNbt();
        if (!this.nbt.contains("Enchantment Stones", 9)) {
            this.nbt.put("Enchantment Stones", new NbtList());
        }

        NbtList nbtList = this.nbt.getList("Enchantment Stones", 10);
        nbtList.add(Utils.createEnchantmentstoneNbt(stoneStack));
    }
}
