package com.firecontrol.minecraftdrp.Proxy;

import club.minnced.discord.rpc.DiscordRichPresence;
import com.firecontrol.minecraftdrp.Handlers.PlayerEventHandler;
import com.firecontrol.minecraftdrp.MinecraftDRP;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.Display;

public class ClientProxy implements ICommonProxy {

    @Override
    public void preInit() {
        // TODO Auto-generated method stub

    }

    @Override
    public void init() {
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
    }

    @Override
    public void postInit() {
        MinecraftDRP.DRPC.initialize();
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.state = "Main Menu";
        presence.largeImageKey = "crafting_large";
        presence.largeImageText = Display.getTitle();
        MinecraftDRP.DRPC.setPresence(presence);
    }

}
