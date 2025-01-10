package com.eve.noflymod.network;

import com.eve.noflymod.ConfigData;
import com.eve.noflymod.common.Screens;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class OpenBeltSlotInventory
{
    public OpenBeltSlotInventory()
    {
    }

    public OpenBeltSlotInventory(FriendlyByteBuf buf)
    {
    }

    public void encode(FriendlyByteBuf buf)
    {
    }

    public boolean handle(Supplier<NetworkEvent.Context> context)
    {
        if (ConfigData.customBeltSlotEnabled)
        {
            context.get().enqueueWork(() -> {
                Screens.openSlotScreen(context.get().getSender());
            });
        }
        return true;
    }
}
