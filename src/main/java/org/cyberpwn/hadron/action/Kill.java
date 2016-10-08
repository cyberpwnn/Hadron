package org.cyberpwn.hadron.action;

import org.bukkit.entity.Player;
import org.phantomapi.construct.Controllable;

public class Kill extends Action
{
	public Kill(Controllable parentController)
	{
		super(parentController);
	}
	
	@Override
	public void act(Player p)
	{
		p.setHealth(0);
	}
}
