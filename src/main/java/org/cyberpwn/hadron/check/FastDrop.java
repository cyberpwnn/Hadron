package org.cyberpwn.hadron.check;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.phantomapi.clust.Comment;
import org.phantomapi.clust.Keyed;
import org.phantomapi.construct.Controllable;
import org.phantomapi.lang.GList;
import org.phantomapi.lang.GMap;
import org.phantomapi.util.M;

public class FastDrop extends Check
{
	private GMap<Player, Long> ms;
	
	@Comment("Change the amount of times a player can drop an item per second.")
	@Keyed("fast-drop.max-drops-per-second")
	public int dropsPerSecond = 4;
	
	public FastDrop(Controllable parentController)
	{
		super(parentController);
		
		this.ms = new GMap<Player, Long>();
	}
	
	public void onNewConfig()
	{
		super.onNewConfig();
		
		cc.set("check.actions", new GList<String>().qadd("cancel"), "If the player drops too many drops per second, what should happen?");
	}
	
	@EventHandler
	public void on(PlayerQuitEvent e)
	{
		ms.remove(e.getPlayer());
	}
	
	@EventHandler
	public void on(PlayerDropItemEvent e)
	{
		if(!ms.containsKey(e.getPlayer()))
		{
			ms.put(e.getPlayer(), M.ms());
			
			return;
		}
		
		if(M.ms() - ms.get(e.getPlayer()) < 1000 / dropsPerSecond)
		{
			e.setCancelled(actions.contains("cancel"));
			executeActions(e.getPlayer());
		}
		
		else
		{
			ms.put(e.getPlayer(), M.ms());
		}
	}
}
