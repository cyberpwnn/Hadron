package org.cyberpwn.hadron.action;

import org.bukkit.entity.Player;
import org.phantomapi.clust.Configurable;
import org.phantomapi.clust.DataCluster;
import org.phantomapi.construct.Controllable;
import org.phantomapi.construct.Controller;
import org.phantomapi.util.C;
import org.phantomapi.util.F;

public class Action extends Controller implements Configurable, Actionable
{
	private DataCluster cc;
	private Boolean enabled;
	
	public Action(Controllable parentController)
	{
		super(parentController);
		
		this.cc = new DataCluster();
		this.enabled = true;
	}
	
	@Override
	public void start()
	{
		loadCluster(this, "actions");
		
		if(enabled)
		{
			super.start();
		}
		
		else
		{
			f("Disabled");
		}
	}
	
	@Override
	public void stop()
	{
		if(enabled)
		{
			super.stop();
		}
	}

	@Override
	public void onNewConfig()
	{
		cc.set("action.enable", true, "You can turn off this action here. If this check is turned off, \nit will not do anything that a check relies on function.\nThe check will still work, but it wont fire this action.");
		cc.set("action.message.text", "&cAction Executed", "Sends a message to the player whom the action was executed to");
		cc.set("action.message.enabled", false, "Enable messaging to this action");
	}

	@Override
	public void onReadConfig()
	{
		enabled = cc.getBoolean("action.enable");
	}

	@Override
	public DataCluster getConfiguration()
	{
		return cc;
	}

	@Override
	public String getCodeName()
	{
		return F.cname(getClass().getSimpleName());
	}

	@Override
	public void preAct(Player p)
	{
		if(enabled)
		{
			act(p);
			
			if(cc.getBoolean("action.message.enabled"))
			{
				p.sendMessage(C.DARK_GRAY + "[" + C.LIGHT_PURPLE + "Hadron" + C.DARK_GRAY + "]: " + C.GRAY + F.color(cc.getString("action.message.text")));
			}
		}
	}
	
	@Override
	public void act(Player p)
	{
		
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
