package org.cyberpwn.hadron.check;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.phantomapi.construct.Controllable;
import org.phantomapi.lang.GList;

public class BowBoost extends Check
{
	public BowBoost(Controllable parentController)
	{
		super(parentController);
	}
	
	public void onNewConfig()
	{
		super.onNewConfig();
		
		cc.set("check.actions", new GList<String>().qadd("cancel").qadd("reset-velocity"), "If the player bow boosts?");
	}
	
	@EventHandler
	public void on(EntityDamageByEntityEvent e)
	{
		Entity entity = e.getEntity();
		Entity damager = e.getDamager();
		
		if(entity.getType().equals(EntityType.PLAYER) && damager.getType().equals(EntityType.ARROW))
		{
			Arrow arrow = (Arrow) damager;
			Player player = (Player) entity;
			
			if(arrow.getShooter() instanceof Player)
			{
				Player shooter = (Player) arrow.getShooter();
				
				if(shooter.equals(player))
				{
					e.setCancelled(actions.contains("cancel"));
					executeActions(player);
				}
			}
		}
	}
}
