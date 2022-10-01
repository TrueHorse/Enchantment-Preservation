package net.trueHorse.enchantmentStones.mixin;

import net.minecraft.client.item.TooltipContext;
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
import net.minecraft.text.*;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.trueHorse.enchantmentStones.EnchantmentStones;
import net.trueHorse.enchantmentStones.ItemStackAccess;
import net.trueHorse.enchantmentStones.Utils;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ItemStackAccess {

    @Shadow
    private NbtCompound nbt;
    @Shadow
    public abstract NbtCompound getOrCreateNbt();

    @Shadow public abstract Item getItem();

    @Shadow public abstract boolean isIn(TagKey<Item> tag);

    @Shadow public abstract NbtList getEnchantments();

    @Inject(method = "damage(ILnet/minecraft/entity/LivingEntity;Ljava/util/function/Consumer;)V",at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V",shift = At.Shift.BEFORE))
    private <T> void pickUpEnchantmentStonesOnBreak(int amount, LivingEntity entity, Consumer<T> breakCallback, CallbackInfo info) {

        if (entity instanceof PlayerEntity) {
            NbtList stoneList = this.nbt.getList("Enchantment Stones",10);

            for (NbtElement stoneNbt : stoneList) {
                ItemStack stone = Registry.ITEM.get(Identifier.tryParse(((NbtCompound)stoneNbt).getString("StoneId"))).getDefaultStack();
                stone.getOrCreateNbt().put("StoredEnchantments",((NbtCompound)stoneNbt).getList("StoredEnchantments",10));

                ((PlayerEntity) entity).giveItemStack(stone);
            }
        }
    }

    @Inject(method = "getTooltip",at=@At(value = "INVOKE",target = "Lnet/minecraft/item/ItemStack;appendEnchantments(Ljava/util/List;Lnet/minecraft/nbt/NbtList;)V",shift = At.Shift.BEFORE),locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void appendEnchantmentStones(@Nullable PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> info, List<Text> list){
        NbtList stonesAsNbt = this.getOrCreateNbt().getList("Enchantment Stones",10);
        if(!stonesAsNbt.isEmpty()){
            for(NbtElement stone:stonesAsNbt){
                MutableText stoneName = (MutableText) Registry.ITEM.get(Identifier.tryParse(((NbtCompound)stone).getString("StoneId"))).getName();
                list.add(stoneName.append(":").formatted(Formatting.GRAY));

                NbtList storedEnchantmentNbt = ((NbtCompound)stone).getList("StoredEnchantments",10);
                if(storedEnchantmentNbt.isEmpty()){
                    list.add(MutableText.of(new LiteralTextContent("  None")).formatted(Formatting.GRAY));
                }else {
                    for(NbtElement enchantmentNbt:storedEnchantmentNbt){
                        Registry.ENCHANTMENT.getOrEmpty(EnchantmentHelper.getIdFromNbt((NbtCompound) enchantmentNbt)).ifPresent((e) -> {
                            Text enchantmentText = e.getName(EnchantmentHelper.getLevelFromNbt((NbtCompound) enchantmentNbt));
                            enchantmentText = Text.literal(enchantmentText.getString()).setStyle(this.getEnchantments().contains(enchantmentNbt)?enchantmentText.getStyle(): enchantmentText.getStyle().withColor(Formatting.DARK_GRAY));
                            list.add(MutableText.of(new LiteralTextContent("  ")).append(enchantmentText));
                        });
                    }
                }
            }
            Map<Enchantment,Integer> weakEnchants = EnchantmentHelper.fromNbt(this.nbt.getList("weak enchantments",10));
            weakEnchants.forEach((e,i)->{
                Text enchantmentName = e.getName(i);
                list.add(MutableText.of(enchantmentName.getContent()).setStyle(enchantmentName.getStyle().withColor(Formatting.DARK_GRAY)));
            });
        }
    }

    @ModifyArg(method = "getTooltip", at=@At(value = "INVOKE",target = "Lnet/minecraft/item/ItemStack;appendEnchantments(Ljava/util/List;Lnet/minecraft/nbt/NbtList;)V"),index = 1)
    private NbtList removeAlreadyAppendedEnchantments(NbtList enchantments){
        NbtList unstoredEnchants = enchantments.copy();
        NbtList toRemove = new NbtList();
        for(NbtElement stone:this.getOrCreateNbt().getList("Enchantment Stones",10)){
            toRemove.addAll(((NbtCompound) stone).getList("StoredEnchantments", 10));
        }
        unstoredEnchants.removeAll(toRemove);
        return  unstoredEnchants;
    }

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
    private void addToStoredEnchantment(Enchantment enchantment, int lvl, CallbackInfo info){
        if(this.isIn(EnchantmentStones.ENCHANTMENT_STONES)){
            this.getOrCreateNbt();
            if (!this.nbt.contains("StoredEnchantments", 9)) {
                this.nbt.put("StoredEnchantments", new NbtList());
            }

            NbtList nbtList = this.nbt.getList("StoredEnchantments", 10);
            nbtList.add(EnchantmentHelper.createNbt(EnchantmentHelper.getEnchantmentId(enchantment), (byte)lvl));
            info.cancel();
        }

        if(this.getOrCreateNbt().contains("Enchantment Stones")&&!this.getOrCreateNbt().getList("Enchantment Stones",10).isEmpty()){
            for(NbtElement stone:this.getOrCreateNbt().getList("Enchantment Stones",10)){
                NbtList storedEnchants = ((NbtCompound)stone).getList("StoredEnchantments",10);
                if(storedEnchants.isEmpty()){
                    storedEnchants.add(EnchantmentHelper.createNbt(EnchantmentHelper.getEnchantmentId(enchantment), (byte)lvl));
                    break;
                }
            }
        }
    }

    @Inject(method = "setSubNbt",at=@At("HEAD"),cancellable = true)
    private void setStoredEnchantments(String key, NbtElement element,CallbackInfo info){
        if(Objects.equals(key, "Enchantments")&&this.isIn(EnchantmentStones.ENCHANTMENT_STONES)){
            this.getOrCreateNbt().put("StoredEnchantments", element);
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
