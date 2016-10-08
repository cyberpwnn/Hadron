package org.cyberpwn.hadron.action;

import org.bukkit.entity.Player;

public interface Actionable
{
	public void act(Player p);
	public void preAct(Player p);
}
