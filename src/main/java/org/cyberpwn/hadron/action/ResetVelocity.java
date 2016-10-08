package org.cyberpwn.hadron.action;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.phantomapi.construct.Controllable;

public class ResetVelocity extends Action
{
	public ResetVelocity(Controllable parentController)
	{
		super(parentController);
	}
	
	@Override
	public void act(Player p)
	{
		p.setVelocity(new Vector(0, 0, 0));
	}
}
