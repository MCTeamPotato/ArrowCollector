package com.teampotato.arrowcollector.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.BowItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BowItem.class)
public abstract class MixinBowItem {
    @Redirect(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
    private boolean onStopUsing(World instance, Entity entity) {
        if (EnchantmentHelper.getLevel(Enchantments.INFINITY, ((BowItem)(Object)this).getDefaultStack()) == 0) {
            ((PersistentProjectileEntity)entity).pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        }
        return instance.spawnEntity(entity);
    }
}
