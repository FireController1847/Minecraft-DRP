package com.firecontrol.minecraftdrp.Handlers;

import org.lwjgl.opengl.Display;

import com.firecontrol.minecraftdrp.MinecraftDRP;
import com.firecontrol.minecraftdrp.Reference;

import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ClientDisconnectionFromServerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PlayerEventHandler {

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onLogin(PlayerLoggedInEvent event) {
		MinecraftDRP.logger.info("CLIENT LOGIN EVENT");
		World world = event.player.getEntityWorld();
		String worldName = world.getWorldInfo().getWorldName();
		DiscordRichPresence presence = new DiscordRichPresence();
		presence.startTimestamp = System.currentTimeMillis() / 1000;
		presence.details = worldName;
		presence.state = "Singeplayer";
		presence.largeImageKey = "unknown";
		presence.largeImageText = worldName;
		presence.smallImageKey = "crafting_small";
		presence.smallImageText = Display.getTitle();
		MinecraftDRP.DRPC.setPresence(presence);
		event.player.sendMessage(new TextComponentString(Reference.MOD_PREFIX + "Now broadcasting your world on "
				+ TextFormatting.DARK_AQUA + "Discord" + TextFormatting.RESET + " as " + TextFormatting.GOLD
				+ presence.details + TextFormatting.RESET + "."));
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void connectToServerEvent(ClientConnectedToServerEvent event) {
		ServerData data = Minecraft.getMinecraft().getCurrentServerData();
		if (data == null)
			return;
		Reference.serverPresence = new DiscordRichPresence();
		Reference.serverPresence.startTimestamp = System.currentTimeMillis() / 1000;
		Reference.serverPresence.details = data.serverName;
		Reference.serverPresence.state = "Multiplayer";
		Reference.serverPresence.largeImageKey = "unknown";
		Reference.serverPresence.largeImageText = (data.serverMOTD != null
				? StringUtils.stripControlCodes(data.serverMOTD)
				: (data.serverName != null ? data.serverName : "Unknown Server"));
		Reference.serverPresence.smallImageKey = "crafting_small";
		Reference.serverPresence.smallImageText = Display.getTitle();
		Reference.serverPresence.joinSecret = data.serverIP;
		Reference.connectedToServer = true;
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void clientTick(ClientTickEvent event) {
		if (Reference.connectedToServer) {
			Minecraft mc = Minecraft.getMinecraft();
			if (mc.player == null || mc.world == null)
				return;
			mc.player.sendMessage(new TextComponentString(Reference.MOD_PREFIX + "Now broadcasting your world on "
					+ TextFormatting.DARK_AQUA + "Discord" + TextFormatting.RESET + " as " + TextFormatting.GOLD
					+ Reference.serverPresence.details + TextFormatting.RESET + "."));
			MinecraftDRP.DRPC.setPresence(Reference.serverPresence);
			Reference.connectedToServer = false;
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void disconnectFromServerEvent(ClientDisconnectionFromServerEvent event) {
		Reference.serverPresence = null;
		DiscordRichPresence presence = new DiscordRichPresence();
		presence.state = "Main Menu";
		presence.largeImageKey = "crafting_large";
		presence.largeImageText = Display.getTitle();
		MinecraftDRP.DRPC.setPresence(presence);
	}

}
