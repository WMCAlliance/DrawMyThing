package com.mrstart.dmt.task;

import com.mrstart.dmt.game.Game;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskNextRound extends BukkitRunnable
{


    private final Game buildZone;

	public TaskNextRound(Game buildZone) {
		this.buildZone = buildZone;
	}

	public void run() {
		buildZone.startRound();
	}
}