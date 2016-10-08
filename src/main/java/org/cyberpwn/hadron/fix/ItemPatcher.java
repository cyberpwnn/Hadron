package org.cyberpwn.hadron.fix;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.phantomapi.construct.Controller;

public class ItemPatcher extends Controller implements Listener
{
	private final SkullExploitPatch plugin;
	
	public ItemPatcher(Controller parent, SkullExploitPatch skullExploitPatch)
	{
		super(parent);
		
		this.plugin = skullExploitPatch;
	}
	
	@EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
	public void onInteract(PlayerInteractEvent e)
	{
		if(plugin.isExploit(e.getItem()))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onItemDrop(PlayerDropItemEvent e)
	{
		if(plugin.isExploit(e.getItemDrop().getItemStack()))
		{
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onItemDrop(InventoryClickEvent e)
	{
		if(e.getWhoClicked().getType() == EntityType.PLAYER)
		{
			if(plugin.isExploit(e.getCurrentItem()))
			{
				e.setCancelled(true);
			}
		}
	}

	@Override
	public void onStart()
	{
		
	}

	@Override
	public void onStop()
	{
		
	}
}
