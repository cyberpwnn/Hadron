package org.cyberpwn.hadron;

import org.cyberpwn.hadron.check.BowBoost;
import org.cyberpwn.hadron.check.FalseDirection;
import org.cyberpwn.hadron.check.FastBreak;
import org.cyberpwn.hadron.check.FastDrop;
import org.cyberpwn.hadron.check.FastHit;
import org.cyberpwn.hadron.check.FastPlace;
import org.phantomapi.construct.Controllable;
import org.phantomapi.construct.Controller;

public class CheckCollider extends Controller
{
	private final FastDrop fastDrop;
	private final FastBreak fastBreak;
	private final FastPlace fastPlace;
	private final FastHit fastHit;
	private final BowBoost bowBoost;
	private final FalseDirection falseDirection;
	
	public CheckCollider(Controllable parentController)
	{
		super(parentController);

		this.fastDrop = new FastDrop(this);
		this.fastPlace = new FastPlace(this);
		this.fastBreak = new FastBreak(this);
		this.fastHit = new FastHit(this);
		this.falseDirection = new FalseDirection(this);
		this.bowBoost = new BowBoost(this);
		
		register(fastDrop);
		register(fastPlace);
		register(fastBreak);
		register(fastHit);
		register(falseDirection);
		register(bowBoost);
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
