package com.eve.noflydimensions.mixins;

import by.dragonsurvivalteam.dragonsurvival.server.handlers.ServerFlightHandler;
import com.eve.noflydimensions.ConfigData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerFlightHandler.class)
public class noflymixin {

    @Inject(method = "isFlying", at = @At("HEAD"), cancellable = true)
    private static void noFlyDimensionCheck(LivingEntity player, CallbackInfoReturnable<Boolean> cir) {
        if (ConfigData.noFlyList.contains(player.level.dimension().location().toString())) {
            cir.setReturnValue(false);
        }
    }

    // seems to be weird bug where hunger drains,, idk?? might just be the mods fault lol
//    @Inject(method = "playerFoodExhaustion", at = @At("HEAD"), cancellable = true)
//    private static void mixin(TickEvent.PlayerTickEvent playerTickEvent, CallbackInfo ci){
//        if (ConfigData.noFlyList.contains(playerTickEvent.player.level.dimension().location().toString())) {
//            ci.cancel();
//            System.out.println("cancelled");
//        }
//    }
}
