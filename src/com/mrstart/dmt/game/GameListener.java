package com.mrstart.dmt.game;

import com.mrstart.dmt.ChatUtil;
import com.mrstart.dmt.DrawMyThing;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

// Referenced classes of package com.mrstart.dmt.game:
//            Game

public class GameListener
    implements Listener
{
	private DrawMyThing instance;
	private Game game;
	private DyeColor color;

    public GameListener(DrawMyThing instance)
    {
        color = DyeColor.BLACK;
        this.instance = instance;
    }

	@EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerLogOut(PlayerQuitEvent event)
    {
        if(event.getPlayer().hasMetadata("inbmt")) {
			instance.getGameByName(event.getPlayer().getMetadata("inbmt").get(0).asString()).leave(event.getPlayer());
		}
    }

	@EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerPlaceBlock(BlockPlaceEvent event)
    {
        if(event.getPlayer().hasMetadata("inbmt") && instance.getGameByName(event.getPlayer().getMetadata("inbmt").get(0).asString()) != null) {
			event.setCancelled(true);
		}
    }

	@EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerInteract(PlayerInteractEvent event)
    {
		//Set<Material> set = null;
		HashSet<Byte> set = null;
        if(!event.getPlayer().hasMetadata("inbmt")) {
			return;
		}
        if(instance.getGameByName(event.getPlayer().getMetadata("inbmt").get(0).asString()) == null) {
			return;
		}
        if(instance.getGameByName(event.getPlayer().getMetadata("inbmt").get(0).asString()).getBuilder() == null) {
			return;
		}
        if(!instance.getGameByName(event.getPlayer().getMetadata("inbmt").get(0).asString()).getBuilder().getName().equals(event.getPlayer().getName())) { //TODO Improve
			return;
		}
        if(event.getPlayer().getItemInHand() == null) {
			return;
		}
        if(event.getPlayer().getItemInHand().getType() == Material.STICK && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK))
        {
			List<Block> blocks = new ArrayList();
            Block b = (Block)event.getPlayer().getTargetBlock(set, 100);
			blocks.add(b);
            Block b1 = b.getLocation().add(1.0D, 0.0D, 0.0D).getBlock();
			blocks.add(b1);
            Block b2 = b.getLocation().add(0.0D, 1.0D, 0.0D).getBlock();
			blocks.add(b2);
            Block b3 = b.getLocation().subtract(1.0D, 0.0D, 0.0D).getBlock();
			blocks.add(b3);
            Block b4 = b.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock();
			blocks.add(b4);
            if(b.getType() != Material.WOOL) {
				return;
			}
			for(Block bl : blocks) {
				if(bl.getType() == Material.WOOL) {
					bl.setTypeIdAndData(Material.WOOL.getId(), color.getData(), true);
				}
			}
            if(instance.getConfig().getBoolean("tool-sound")) {
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.FIZZ, 1.0F, 1.0F);
			}
            return;
        }
        if(event.getPlayer().getItemInHand().getType() == Material.WATER_BUCKET && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK))
        {
			for(Object ob : game.getBuildZone().toArray()) {
				((Block)ob).setData((byte)0);
			}
		}
        if(event.getPlayer().getItemInHand().getType() == Material.WOOL && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK))
        {
            if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) {
				return;
			}
            Block b = (Block)event.getPlayer().getTargetBlock(set, 100);
            if(b.getType() != Material.WOOL) {
				return;
			}
            b.setTypeIdAndData(Material.WOOL.getId(), DyeColor.WHITE.getData(), true);
            if(instance.getConfig().getBoolean("tool-sound")) {
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BAT_TAKEOFF, 1.0F, 1.0F);
			}
            return;
        }
        if(event.getPlayer().getItemInHand().getType() == Material.BLAZE_ROD && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK))
        {
			List<Block> blocks = new ArrayList();
            Block b = (Block)event.getPlayer().getTargetBlock(set, 100);
			blocks.add(b);
            Block b1 = b.getLocation().add(1.0D, 0.0D, 0.0D).getBlock();
			blocks.add(b1);
            Block b2 = b.getLocation().add(0.0D, 1.0D, 0.0D).getBlock();
			blocks.add(b2);
            Block b3 = b.getLocation().subtract(1.0D, 0.0D, 0.0D).getBlock();
			blocks.add(b3);
            Block b4 = b.getLocation().subtract(0.0D, 1.0D, 0.0D).getBlock();
			blocks.add(b4);
            Block b5 = b.getLocation().subtract(1.0D, 0.0D, 0.0D).add(0.0D, 1.0D, 0.0D).getBlock();
			blocks.add(b5);
            Block b6 = b.getLocation().add(1.0D, 1.0D, 0.0D).getBlock();
			blocks.add(b6);
            Block b7 = b.getLocation().subtract(1.0D, 1.0D, 0.0D).getBlock();
			blocks.add(b7);
            Block b8 = b.getLocation().add(1.0D, 0.0D, 0.0D).subtract(0.0D, 1.0D, 0.0D).getBlock();
			blocks.add(b8);
			Block b9 = b.getLocation().add(0.0D, 2.0D, 0.0D).getBlock();
			blocks.add(b9);
			Block b10 = b.getLocation().subtract(2.0D, 0.0D, 0.0D).getBlock();
			blocks.add(b10);
			Block b11 = b.getLocation().subtract(0.0D, 2.0D, 0.0D).getBlock();
			blocks.add(b11);
			Block b12 = b.getLocation().add(2.0D, 0.0D, 0.0D).getBlock();
			blocks.add(b12);
			
			for(Block bl : blocks) {
				if(bl.getType() == Material.WOOL) {
					bl.setTypeIdAndData(Material.WOOL.getId(), color.getData(), true);
				}
			}
            if(instance.getConfig().getBoolean("tool-sound")) {
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.FIZZ, 1.0F, 1.0F);
			}
            return;
        }
        if(event.getPlayer().getItemInHand().getType() == Material.WATCH) {
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                instance.cci.clear();
                instance.addCCIItems();
                event.getPlayer().openInventory(instance.cci);
            }
        }
    }

	@EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event)
    {
        if(event.getPlayer().hasMetadata("inbmt") && !event.getMessage().startsWith("/dmt") && !event.getPlayer().hasPermission("dmt.admin"))
        {
            ChatUtil.send(event.getPlayer(), (new StringBuilder()).append(ChatColor.RED).append("You cannot execute commands while ingame!").toString());
            event.setCancelled(true);
        }
    }

	@EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerBreakBlock(BlockBreakEvent event)
    {
        if(event.getPlayer().hasMetadata("inbmt")) {
			event.setCancelled(true);
		}
    }

	@EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerDropItem(PlayerDropItemEvent event)
    {
        if(event.getPlayer().hasMetadata("inbmt")) {
			event.setCancelled(true);
		}
    }

	@EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerHit(EntityDamageEvent event)
    {
        if(event.getEntity() instanceof Player)
        {
            Player p = (Player)event.getEntity();
            if(p.hasMetadata("inbmt")) {
				event.setCancelled(true);
			}
        }
    }

	@EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerHungerChange(FoodLevelChangeEvent event)
    {
        if(event.getEntity().hasMetadata("inbmt")) {
			event.setCancelled(true);
		}
    }

	@EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerChat(AsyncPlayerChatEvent event)
    {
        if(event.getPlayer().hasMetadata("inbmt") && instance.getGameByName(event.getPlayer().getMetadata("inbmt").get(0).asString()) != null && instance.getGameByName(event.getPlayer().getMetadata("inbmt").get(0).asString()).isStarted()) {
			if (instance.getGameByName(event.getPlayer().getMetadata("inbmt").get(0).asString()).getBuilder().getName().equals(event.getPlayer().getName())) { // TODO Improve
				ChatUtil.send(event.getPlayer(), (new StringBuilder()).append(ChatColor.RED).append("You cannot chat while you are a drawer!").toString());
				event.setCancelled(true);
			} else {
				event.setCancelled(true);
				String word = instance.getGameByName(event.getPlayer().getMetadata("inbmt").get(0).asString()).getWord();
				if (instance.getGameByName(event.getPlayer().getMetadata("inbmt").get(0).asString()).hasFound(event.getPlayer())) {
					ChatUtil.send(event.getPlayer(), (new StringBuilder()).append(ChatColor.RED).append("You have already found the word!").toString());
				} else if (event.getMessage().toLowerCase().contains(word)) {
					instance.getGameByName(event.getPlayer().getMetadata("inbmt").get(0).asString()).wordFoundBy(event.getPlayer());
				} else {
					instance.getGameByName(event.getPlayer().getMetadata("inbmt").get(0).asString()).sendMessage((new StringBuilder()).append(ChatColor.BOLD).append(event.getPlayer().getName()).append(": ").append(ChatColor.RESET).append(event.getMessage().toLowerCase()).toString());
				}
			}
		}
    }

	/*@EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerMove(PlayerMoveEvent event)
    {
        if(event.getPlayer().hasMetadata("inbmt") && instance.getGameByName(event.getPlayer().getMetadata("inbmt").get(0).asString()) != null && instance.getGameByName(event.getPlayer().getMetadata("inbmt").get(0).asString()).isStarted() && event.getPlayer().getDisplayName().contains("[BUILDER]"))
        {
            if(event.getTo().getX() == event.getFrom().getX() && event.getTo().getY() == event.getFrom().getY() && event.getTo().getZ() == event.getFrom().getZ()) {
				return;
			}
            event.setTo(event.getFrom());
        }
    }*/

	@EventHandler (priority = EventPriority.NORMAL)
    public void onInvClick(InventoryClickEvent event)
    {
        Player p = (Player)event.getWhoClicked();
        if(p.hasMetadata("inbmt")) {
			if (event.getInventory().getName().equals(instance.cci.getName())) { //TODO Improve
				if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) {
					return;
				}
				if(event.getCurrentItem().getItemMeta().getDisplayName().contains("White"))
				{
					setPencilColor(DyeColor.WHITE);
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
					p.closeInventory();
					return;
				}
				if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Black"))
				{
					setPencilColor(DyeColor.BLACK);
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
					p.closeInventory();
					return;
				}
				if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Red"))
				{
                    setPencilColor(DyeColor.RED);
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
					p.closeInventory();
					return;
				}
				if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Orange"))
				{
					setPencilColor(DyeColor.ORANGE);
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
                    p.closeInventory();
					return;
				}
				if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Yellow"))
				{
					setPencilColor(DyeColor.YELLOW);
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
					p.closeInventory();
					return;
                }
				if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Green"))
				{
					setPencilColor(DyeColor.LIME);
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
					p.closeInventory();
					return;
				}
				if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Blue"))
                {
					setPencilColor(DyeColor.LIGHT_BLUE);
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
					p.closeInventory();
					return;
				}
				if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Purple"))
				{
					setPencilColor(DyeColor.PURPLE);
                    p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
					p.closeInventory();
					return;
				}
				if(event.getCurrentItem().getItemMeta().getDisplayName().contains("Brown"))
				{
					setPencilColor(DyeColor.BROWN);
					p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 1.0F);
					p.closeInventory();
                    return;
                }
			} else {
				event.setCancelled(true);
				p.closeInventory();
				return;
			}
		}
    }

    public void setPencilColor(DyeColor color)
    {
        this.color = color;
    }

    public DyeColor getColor()
    {
        return color;
    }

}