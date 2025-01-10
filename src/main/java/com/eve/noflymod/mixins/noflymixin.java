package com.eve.noflymod.mixins;

import by.dragonsurvivalteam.dragonsurvival.server.handlers.ServerFlightHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerFlightHandler.class)
public abstract class noflymixin {

    @Inject( at = @At(value = "HEAD"), method = "isFlying", cancellable = true)
    private static void noFlyDimensionCheck(LivingEntity player, CallbackInfoReturnable<Boolean> cir){
        if (player.level.dimension() == Level.END){
            cir.setReturnValue(false);
        }
    }
}
