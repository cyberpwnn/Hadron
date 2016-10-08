package org.cyberpwn.hadron;

import org.cyberpwn.hadron.fix.SkullExploitPatch;
import org.phantomapi.clust.Configurable;
import org.phantomapi.clust.DataCluster;
import org.phantomapi.construct.Controllable;
import org.phantomapi.construct.Controller;

public class HadronCollider extends Controller implements Configurable
{
	private DataCluster cc;
	
	private final ActionCollider actionController;
	private final CheckCollider checkController;
	private final CommandCollider commandController;
	private final SkullExploitPatch skullExploitPatch;
	
	public HadronCollider(Controllable parentController)
	{
		super(parentController);
		
		this.actionController = new ActionCollider(this);
		this.checkController = new CheckCollider(this);
		this.commandController = new CommandCollider(this);
		this.skullExploitPatch = new SkullExploitPatch(this);
		
		register(actionController);
		register(checkController);
		register(commandController);
		register(skullExploitPatch);
	}
	
	public ActionCollider getActionController()
	{
		return actionController;
	}
	
	public CheckCollider getCheckController()
	{
		return checkController;
	}

	@Override
	public void onNewConfig()
	{
		// Dynamic
	}

	@Override
	public void onReadConfig()
	{
		//Dynamic
	}

	@Override
	public DataCluster getConfiguration()
	{
		return cc;
	}

	@Override
	public String getCodeName()
	{
		return "config";
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
