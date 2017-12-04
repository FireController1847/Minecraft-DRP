package com.firecontrol.minecraftdrp.Handlers;

import com.firecontrol.minecraftdrp.MinecraftDRP;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordJoinRequest;

public class RPCEventHandler {

	public static DiscordEventHandlers setupEvents(DiscordEventHandlers handlers) {
		handlers.ready = (() -> {
			onReadyEvent();
		});

		handlers.joinRequest = ((DiscordJoinRequest request) -> {
			onJoinRequest(request);
		});

		handlers.joinGame = ((String secret) -> {
			onJoinGame(secret);
		});

		return handlers;
	}

	private static void onReadyEvent() {
		MinecraftDRP.logger.info("Discord RPC Connection Initiated.");
	}

	private static void onJoinRequest(DiscordJoinRequest request) {
		MinecraftDRP.logger.info("Discord RPC Join Request");
		MinecraftDRP.logger.info(request);
	}

	private static void onJoinGame(String secret) {
		MinecraftDRP.logger.info("On Join Game");
		MinecraftDRP.logger.info(secret);
	}

}