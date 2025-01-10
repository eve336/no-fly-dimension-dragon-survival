package com.eve.noflydimensions.mixins;

import by.dragonsurvivalteam.dragonsurvival.server.handlers.ServerFlightHandler;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerFlightHandler.class)
public class noflymixin {

    @Inject(method = "isFlying", at = @At("HEAD"), cancellable = true)
    private static void noFlyDimensionCheck(LivingEntity player, CallbackInfoReturnable<Boolean> cir) {
        System.out.println(player.level.dimension());
        if (player.level.dimension() == Level.END) {
            cir.setReturnValue(false);
        }
    }
}
