package com.firecontrol.minecraftdrp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

import com.firecontrol.minecraftdrp.Proxy.ICommonProxy;
import com.firecontrol.minecraftdrp.RPC.RPCClient;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MCVERSIONS)
public class MinecraftDRP {

	public static final Logger logger = LogManager.getLogger(Reference.INITIALS);
	public static final RPCClient DRPC = new RPCClient();
	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
	private static ICommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit();
		// if (event.getSide().equals(Side.CLIENT))
		// FMLClientHandler.instance().connectToServerAtStartup("localhost", 25565);
	}

}
