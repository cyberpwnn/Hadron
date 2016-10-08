package org.cyberpwn.hadron.check;

import org.bukkit.entity.Player;

public interface Checked
{
	public boolean check(Player p);
	public void executeActions(Player p);
}
