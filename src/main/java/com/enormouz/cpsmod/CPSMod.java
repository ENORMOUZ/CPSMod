package com.enormouz.cpsmod;

import com.enormouz.cpsmod.features.CPSCounter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "cpsmod", name = "CPSMod", version = "1.0.0", acceptedMinecraftVersions="[1.8.9]", clientSideOnly=true)
public class CPSMod {
    public static final String MOD_NAME = "cpsmod";
    public static final String VERSION = "1.0.0";
    public static final Minecraft mc = Minecraft.getMinecraft();

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        register(this);
        register(new CPSCounter());
    }

    public static void register(Object target) {
        MinecraftForge.EVENT_BUS.register(target);
    }

    private static void registerCommand(ICommand command) {
        ClientCommandHandler.instance.registerCommand(command);
    }

    private static void registerKeyBinding(KeyBinding keyBind) {
        ClientRegistry.registerKeyBinding(keyBind);
    }
}

