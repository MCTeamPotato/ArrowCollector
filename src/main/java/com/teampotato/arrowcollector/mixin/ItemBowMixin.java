package com.teampotato.arrowcollector.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ItemBow.class)
public abstract class ItemBowMixin {
    @Inject(method = "onPlayerStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private void collectableInit(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft, CallbackInfo ci, EntityPlayer entityplayer, boolean flag, ItemStack itemstack, int i, float f, boolean flag1, ItemArrow itemarrow, EntityArrow entityarrow, int j, int k) {
        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) == 0) entityarrow.pickupStatus = EntityArrow.PickupStatus.ALLOWED;
    }
}
