package com.firecontrol.minecraftdrp;

import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.util.text.TextFormatting;

public class Reference {

	// MOD INFO
	public static final String INITIALS = "MCDRP";
	public static final String MOD_ID = "minecraftdrp";
	public static final String NAME = "Minecraft DRP";
	public static final String VERSION = "1.0.0";
	public static final String MCVERSIONS = "[1.12,1.13)";
	public static final String CLIENT_PROXY = "com.firecontrol." + MOD_ID + ".Proxy.ClientProxy";
	public static final String SERVER_PROXY = "com.firecontrol." + MOD_ID + ".Proxy.ServerProxy";

	// RPC INFO
	public static final String CLIENT_ID = "384844450876358667";

	// CHAT HELP
	public static final String MOD_PREFIX = "[" + TextFormatting.BLUE + "Minecraft DRP" + TextFormatting.RESET + "] ";

	// VARIABLES
	public static DiscordRichPresence serverPresence = null;
	public static Boolean connectedToServer = false;
}
