package com.eve.noflydimensions;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.theillusivec4.curios.api.SlotTypeMessage;

@Mod(noFlyDimensions.MODID)
public class noFlyDimensions
{
    public static final String MODID = "noflydimensions";

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);


    public static noFlyDimensions instance;

    public static final Logger logger = LogManager.getLogger(MODID);

    public noFlyDimensions()
    {
        instance = this;

        ModLoadingContext modLoadingContext = ModLoadingContext.get();
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ITEMS.register(modEventBus);
        MENU_TYPES.register(modEventBus);

        modEventBus.addListener(this::construct);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
        modEventBus.addListener(this::modConfig);
        modEventBus.addListener(this::imcEnqueue);
        modEventBus.addListener(this::registerCapabilities);


        modLoadingContext.registerConfig(ModConfig.Type.SERVER, ConfigData.SERVER_SPEC);
        modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigData.COMMON_SPEC);

    }

    private void construct(FMLConstructModEvent event)
    {

    }


    public void modConfig(ModConfigEvent event)
    {
        ModConfig config = event.getConfig();
        if (config.getSpec() == ConfigData.COMMON_SPEC)
            ConfigData.refreshCommon();
        else if (config.getSpec() == ConfigData.SERVER_SPEC)
            ConfigData.refreshServer();
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event)
    {

    }

    public void commonSetup(FMLCommonSetupEvent event)
    {
        int messageNumber = 0;
       logger.debug("Final message number: " + messageNumber);

        //TODO File configurationFile = event.getSuggestedConfigurationFile();
        //Config.loadConfig(configurationFile);


    }

    public void clientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() -> {

        });
    }

    private void imcEnqueue(InterModEnqueueEvent event)
    {
        InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> new SlotTypeMessage.Builder("belt").size(1).build());
    }



    public static ResourceLocation location(String path)
    {
        return new ResourceLocation(MODID, path);
    }


}
