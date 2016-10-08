package org.cyberpwn.hadron.check;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.phantomapi.clust.Comment;
import org.phantomapi.clust.Keyed;
import org.phantomapi.construct.Controllable;
import org.phantomapi.lang.GList;
import org.phantomapi.lang.GMap;
import org.phantomapi.util.M;

public class FastPlace extends Check
{
private GMap<Player, Long> ms;
	
	@Comment("Change the amount of times a player can break blocks per second.")
	@Keyed("fast-place.max-iops")
	public int maxIops = 22;
	
	public FastPlace(Controllable parentController)
	{
		super(parentController);
		
		this.ms = new GMap<Player, Long>();
	}
	
	public void onNewConfig()
	{
		super.onNewConfig();
		
		cc.set("check.actions", new GList<String>().qadd("cancel"), "If the player interacts with an inventory too many times per second, \nwhat should happen?");
	}
	
	@EventHandler
	public void on(PlayerQuitEvent e)
	{
		ms.remove(e.getPlayer());
	}
	
	@EventHandler
	public void on(BlockPlaceEvent e)
	{
		if(!ms.containsKey(e.getPlayer()))
		{
			ms.put(e.getPlayer(), M.ms());
			
			return;
		}
		
		if(M.ms() - ms.get(e.getPlayer()) < 1000 / maxIops)
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
