package com.firecontrol.minecraftdrp.RPC;

import com.firecontrol.minecraftdrp.MinecraftDRP;
import com.firecontrol.minecraftdrp.Reference;
import com.firecontrol.minecraftdrp.Handlers.RPCEventHandler;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class RPCClient {

	private Thread callback;

	public void initialize() {
		DiscordEventHandlers handlers = new DiscordEventHandlers();

		handlers = RPCEventHandler.setupEvents(handlers);

		DiscordRPC.INSTANCE.Discord_Initialize(Reference.CLIENT_ID, handlers, true, null);

		if (callback == null) {
			callback = new Thread(() -> {
				while (!Thread.currentThread().isInterrupted()) {
					DiscordRPC.INSTANCE.Discord_RunCallbacks();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ignored) {
					}
				}
			}, "RPC-Callback-Handler");

			callback.start();
		}

		MinecraftDRP.logger.info("Created new client.");

		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				DiscordRPC.INSTANCE.Discord_Shutdown();
			}
		});
	}

	public void setPresence(DiscordRichPresence newPresence) {
		DiscordRPC.INSTANCE.Discord_UpdatePresence(newPresence);
	}

}
