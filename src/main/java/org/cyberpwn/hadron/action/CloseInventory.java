package org.cyberpwn.hadron.action;

import org.bukkit.entity.Player;
import org.phantomapi.construct.Controllable;

public class CloseInventory extends Action
{
	public CloseInventory(Controllable parentController)
	{
		super(parentController);
	}
	
	@Override
	public void act(Player p)
	{
		p.closeInventory();
	}
}
