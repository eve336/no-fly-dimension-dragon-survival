package com.eve.noflydimensions;

import com.google.common.collect.Lists;
import net.minecraft.util.StringRepresentable;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModList;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class ConfigData
{
    public static final ServerConfig SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;
    public static List<? extends String> noFlyList = new ArrayList<>();

    static
    {
        final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();
    }


    static
    {
    }

    public static final CommonConfig COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static
    {
        final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static ThreeWayChoice anvilUpgrading = ThreeWayChoice.AUTO;
    public static boolean enableAnvilUpgrading = true;

    public static ThreeWayChoice gridCrafting = ThreeWayChoice.AUTO;
    public static boolean enableNormalCrafting = true;

    public static ThreeWayChoice customBeltSlotMode = ThreeWayChoice.AUTO;
    public static boolean customBeltSlotEnabled = true;

    public static boolean curiosPresent()
    {
        return ModList.get().isLoaded("curios");
    }

    public static boolean sewingKitPresent()
    {
        return ModList.get().isLoaded("sewingkit");
    }

    public static class ServerConfig
    {



        ServerConfig(ForgeConfigSpec.Builder builder)
        {
            builder.push("general");
            builder.pop();
        }
    }

    // Any config that has to deal with datapack stuffs
    public static class CommonConfig
    {
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> noFlyList;

        CommonConfig(ForgeConfigSpec.Builder builder)
        {
            builder.push("general");
            noFlyList = builder
                    .comment("List of dimensions where flight is disabled")
                    .defineList("noFlyList", Lists.newArrayList(), o -> o instanceof String);
            builder.pop();
        }
    }



    public static void refreshClient()
    {

    }

    public static void refreshCommon()
    {

        noFlyList = COMMON.noFlyList.get();

        enableAnvilUpgrading = anvilUpgrading == ThreeWayChoice.ON ||
                (anvilUpgrading == ThreeWayChoice.AUTO && !sewingKitPresent());

        enableNormalCrafting = gridCrafting == ThreeWayChoice.ON ||
                (gridCrafting == ThreeWayChoice.AUTO && !sewingKitPresent());

        customBeltSlotEnabled = customBeltSlotMode == ThreeWayChoice.ON ||
                (customBeltSlotMode == ThreeWayChoice.AUTO && !curiosPresent());
    }

    public static void refreshServer()
    {
//        blackList = SERVER.blacklist.get().stream().map(ConfigData::parseItemStack).collect(Collectors.toSet());
//        whiteList = SERVER.whitelist.get().stream().map(ConfigData::parseItemStack).collect(Collectors.toSet());
    }

    public enum ThreeWayChoice implements StringRepresentable
    {
        OFF("off"),
        AUTO("auto"),
        ON("on");

        private final String name;

        ThreeWayChoice(String name)
        {
            this.name = name;
        }

        @Override
        public String getSerializedName()
        {
            return name;
        }

    }
}
