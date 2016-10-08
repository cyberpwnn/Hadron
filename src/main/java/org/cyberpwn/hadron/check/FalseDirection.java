package org.cyberpwn.hadron.check;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.phantomapi.clust.Comment;
import org.phantomapi.clust.Keyed;
import org.phantomapi.construct.Controllable;
import org.phantomapi.lang.GList;
import org.phantomapi.physics.VectorMath;
import org.phantomapi.world.W;

public class FalseDirection extends Check
{
	@Comment("From 0 to 1, yaw padding")
	@Keyed("false-direction.yaw-padding")
	public double maxYawRatio = 0.288;
	
	public FalseDirection(Controllable parentController)
	{
		super(parentController);
	}
	
	public void onNewConfig()
	{
		super.onNewConfig();
		
		cc.set("check.actions", new GList<String>().qadd("cancel"), "If the player fails this check, \nwhat should happen?");
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void on(EntityDamageByEntityEvent e)
	{
		if(e.getDamager() instanceof Player)
		{
			Player p = (Player) e.getDamager();
			
			double dist = 1.0 - (p.getLocation().getDirection().distance(VectorMath.direction(e.getEntity().getLocation(), p.getLocation())) / 2.0);
			
			if(!W.isLookingAt(p, e.getEntity(), 4.5, maxYawRatio) && dist > maxYawRatio)
			{
				e.setCancelled(actions.contains("cancel"));
				executeActions(p);
			}
		}
	}
}
