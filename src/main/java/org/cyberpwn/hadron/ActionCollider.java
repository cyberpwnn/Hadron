package org.cyberpwn.hadron;

import org.bukkit.entity.Player;
import org.cyberpwn.hadron.action.Action;
import org.cyberpwn.hadron.action.CloseInventory;
import org.cyberpwn.hadron.action.Kick;
import org.cyberpwn.hadron.action.Kill;
import org.cyberpwn.hadron.action.ResetVelocity;
import org.phantomapi.clust.Configurable;
import org.phantomapi.construct.Controllable;
import org.phantomapi.construct.Controller;
import org.phantomapi.lang.GList;

public class ActionCollider extends Controller
{
	private final CloseInventory closeInventory;
	private final Kick kick;
	private final ResetVelocity resetVelocity;
	private final Kill kill;
	
	public ActionCollider(Controllable parentController)
	{
		super(parentController);
		
		closeInventory = new CloseInventory(this);
		kick = new Kick(this);
		resetVelocity = new ResetVelocity(this);
		kill = new Kill(this);
		
		register(closeInventory);
		register(kick);
		register(resetVelocity);
		register(kill);
	}
	
	public void executeActions(GList<String> names, Player p)
	{
		for(String i : names)
		{
			executeAction(i, p);
		}
	}
	
	public void executeAction(String name, Player p)
	{
		Action a = getAction(name);
		
		if(a != null)
		{
			a.preAct(p);
		}
	}
	
	public GList<Action> getActions(GList<String> actions)
	{
		GList<Action> action = new GList<Action>();
		
		for(String i : actions)
		{
			Action a = getAction(i);
			
			if(a != null)
			{
				action.add(a);
			}
		}
		
		return action;
	}
	
	public Action getAction(String name)
	{
		for(Controllable i : controllers)
		{
			if(i instanceof Configurable)
			{
				Configurable c = (Configurable) i;
				
				if((i instanceof Action) && name.equalsIgnoreCase(c.getCodeName()))
				{
					return (Action) i;
				}
			}
		}
		
		return null;
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
