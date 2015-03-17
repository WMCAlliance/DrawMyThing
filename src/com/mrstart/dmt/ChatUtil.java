package com.mrstart.dmt;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtil
{


    private static final String PREFIX;

    static 
    {
        PREFIX = (new StringBuilder()).append(ChatColor.BLUE).append("[DMT] ").append(ChatColor.WHITE).toString();
    }

	public static void send(Player p, String message) {
		p.sendMessage((new StringBuilder(String.valueOf(PREFIX))).append(message).toString());
	}

	public static void broadcast(String message) {
		Bukkit.broadcastMessage((new StringBuilder(String.valueOf(PREFIX))).append(message).toString());
	}

	private ChatUtil() {
	}
}