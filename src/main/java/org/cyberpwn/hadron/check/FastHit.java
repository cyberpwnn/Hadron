package org.cyberpwn.hadron.check;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.phantomapi.clust.Comment;
import org.phantomapi.clust.Keyed;
import org.phantomapi.construct.Controllable;
import org.phantomapi.lang.GList;
import org.phantomapi.lang.GMap;
import org.phantomapi.physics.VectorMath;
import org.phantomapi.util.M;

public class FastHit extends Check
{
	@Comment("The minimum time a player waits before hitting again in milliseconds")
	@Keyed("fast-hit.min-damage-interval")
	public long minDamageInterval = 245;
	
	@Comment("The max time frame from the last entity to check vector distance\nSetting this over 1000ms causes black magic and witchcraft (tainted flux)")
	@Keyed("fast-hit.vector-time-frame")
	public long maxVectorTimeFrame = 320;
	
	@Comment("If the hit entity was hit very close to the last within the time frame, \nThe multiple would be < 3% of the multiple\nIf the last entity was the opposite direction, it would be the number supplied")
	@Keyed("fast-hit.vector-comparison-multiple")
	public double vectorComparisonMultiple = 206.8;
	
	private GMap<Player, Long> msx;
	private GMap<Player, Entity> prev;
	
	public FastHit(Controllable parentController)
	{
		super(parentController);
		
		msx = new GMap<Player, Long>();
		prev = new GMap<Player, Entity>();
	}
	
	public void onNewConfig()
	{
		super.onNewConfig();
		
		cc.set("check.actions", new GList<String>().qadd("cancel"), "If the player does kill aura, \nwhat should happen?");
	}
	
	public void place(Player p)
	{
		msx.put(p, M.ms());
	}
	
	public long get(Player p)
	{
		if(!msx.containsKey(p))
		{
			msx.put(p, M.ms() - 1000);
		}
		
		return msx.get(p);
	}
	
	public long diff(Player p)
	{
		return M.ms() - get(p);
	}
	
	@EventHandler
	public void on(PlayerJoinEvent e)
	{
		place(e.getPlayer());
	}
	
	@EventHandler
	public void on(PlayerQuitEvent e)
	{
		msx.remove(e.getPlayer());
		prev.remove(e.getPlayer());
	}
	
	public int diffComp(Player p, Entity e)
	{
		if(diff(p) < maxVectorTimeFrame)
		{
			if(prev.containsKey(p))
			{
				Entity r = prev.get(p);
				
				if(r != null)
				{
					double dist = VectorMath.direction(r.getLocation(), p.getLocation()).distance(VectorMath.direction(e.getLocation(), p.getLocation()));
					
					return (int) (vectorComparisonMultiple * dist);
				}
			}
		}
		
		return 0;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void on(EntityDamageByEntityEvent e)
	{
		if(e.getDamager() instanceof Player)
		{
			Player p = (Player) e.getDamager();
			
			if(diff(p) < minDamageInterval + diffComp(p, e.getEntity()))
			{
				e.setCancelled(actions.contains("cancel"));
				executeActions(p);
			}
			
			else
			{
				place(p);
				prev.put(p, e.getEntity());
			}
		}
	}
}
