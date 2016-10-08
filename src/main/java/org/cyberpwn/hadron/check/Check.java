package org.cyberpwn.hadron.check;

import org.bukkit.entity.Player;
import org.cyberpwn.hadron.Hadron;
import org.cyberpwn.hadron.action.Action;
import org.phantomapi.clust.Configurable;
import org.phantomapi.clust.DataCluster;
import org.phantomapi.construct.Controllable;
import org.phantomapi.construct.Controller;
import org.phantomapi.lang.GList;
import org.phantomapi.util.F;

public class Check extends Controller implements Configurable, Checked
{
	protected DataCluster cc;
	private Boolean enabled;
	protected GList<String> actions;
	
	public Check(Controllable parentController)
	{
		super(parentController);
		
		this.cc = new DataCluster();
		this.enabled = true;
	}
	
	@Override
	public void start()
	{
		loadCluster(this, "checks");
		
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
		cc.set("check.enable", true, "You can turn off this check here. If this check is turned off, \nit will not detect anything that relies on this check to function.");
		cc.set("check.actions", actions, "Actions to fire if the check does not pass.");
	}

	@Override
	public void onReadConfig()
	{
		enabled = cc.getBoolean("check.enable");
		actions = new GList<String>(cc.getStringList("check.actions"));
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
	public boolean check(Player p)
	{
		return true;
	}

	@Override
	public void executeActions(Player p)
	{
		Hadron.instance().getHadronController().getActionController().executeActions(actions, p);
	}
	
	public GList<Action> getActions()
	{
		return Hadron.instance().getHadronController().getActionController().getActions(actions);
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
