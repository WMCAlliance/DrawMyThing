package com.mrstart.dmt;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class LocationUtil
{

    public static String LocationToString(Location l)
	{
		return (new StringBuilder(String.valueOf(String.valueOf((new StringBuilder(String.valueOf(l.getWorld().getName()))).append(":").append(l.getBlockX()).toString())))).append(":").append(String.valueOf(l.getBlockY())).append(":").append(String.valueOf(l.getBlockZ())).toString(); //TODO Improve
	}

    public static Location StringToLoc(String s)
    {
		Location l = null;
		try
		{
			World world = Bukkit.getWorld(s.split(":")[0]);
			Double x = Double.parseDouble(s.split(":")[1]);
			Double y = Double.parseDouble(s.split(":")[2]);
			Double z = Double.parseDouble(s.split(":")[3]);
			l = new Location(world, x, y, z);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return l;
	}
	
	private LocationUtil()
    {
    }
}