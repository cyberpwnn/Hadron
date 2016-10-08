package org.cyberpwn.hadron;

import org.phantomapi.construct.PhantomPlugin;

public class Hadron extends PhantomPlugin
{
	private HadronCollider hadronController;
	private static Hadron instance;
	
	public void enable()
	{
		this.hadronController = new HadronCollider(this);
		
		register(hadronController);
		
		instance = this;
	}
	
	public void disable()
	{
		
	}
	
	public static Hadron instance()
	{
		return instance;
	}

	public HadronCollider getHadronController()
	{
		return hadronController;
	}
}
