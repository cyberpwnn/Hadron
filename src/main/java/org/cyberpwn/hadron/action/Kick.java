package org.cyberpwn.hadron.action;

import org.bukkit.entity.Player;
import org.phantomapi.construct.Controllable;
import org.phantomapi.util.C;

public class Kick extends Action
{
	public Kick(Controllable parentController)
	{
		super(parentController);
	}
	
	@Override
	public void act(Player p)
	{
		p.kickPlayer(C.LIGHT_PURPLE + "Hadron Colliders have their risks.");
	}
}
