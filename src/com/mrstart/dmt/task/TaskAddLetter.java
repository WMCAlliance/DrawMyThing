package com.mrstart.dmt.task;

import com.mrstart.dmt.game.Game;
import org.bukkit.scheduler.BukkitRunnable;

public class TaskAddLetter extends BukkitRunnable
{


    private Game g;

	public TaskAddLetter(Game g) {
		this.g = g;
	}

	public void run() {
		g.addRandomLetter();
	}
}