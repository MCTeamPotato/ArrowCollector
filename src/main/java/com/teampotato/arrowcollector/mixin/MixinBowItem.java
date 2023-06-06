package com.teampotato.arrowcollector.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.BowItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BowItem.class)
public abstract class MixinBowItem {
    @Redirect(method = "releaseUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addFreshEntity(Lnet/minecraft/entity/Entity;)Z"))
    private boolean onReleaseUsing(World instance, Entity entity) {
        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, ((BowItem)(Object)this).getDefaultInstance()) == 0) {
            ((AbstractArrowEntity)entity).pickup = AbstractArrowEntity.PickupStatus.ALLOWED;
        }
        return instance.addFreshEntity(entity);
    }
}
