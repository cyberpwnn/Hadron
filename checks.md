# 1. Create the check #
Be sure to place it in the checks package.

* You dont need to register events
* You dont need to save/load configs

``` java
package org.cyberpwn.hadron.check;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.cyberpwn.phantom.clust.Comment;
import org.cyberpwn.phantom.clust.Keyed;
import org.cyberpwn.phantom.construct.Controllable;
import org.cyberpwn.phantom.lang.GList;
import org.cyberpwn.phantom.physics.VectorMath;

//Create a class in the org.cyberpwn.hadron.check package
//Extend check
public class FastMove extends Check
{	
	//You can make configurable variables
	//Its important to do so in most cases
	@Keyed("bad-move.max-speed")
	@Comment("Comment your stuff")
	public double maxSpeed = 0.23;
	
	//Shitty constructor... use it if needed
	public FastMove(Controllable parentController)
	{
		super(parentController);
	}
	
	//THIS IS NEEDED. YOU NEED TO OVERRIDE THIS
	public void onNewConfig()
	{
		//Super call first
		super.onNewConfig();
		
		//Sets the "default" actions to fire.
		//This is so it "kinda" works by default. 
		//These can be actual actions or anything you choose as long as you use them
		
		//For example we use reset-velocity. It is an action
		//We also use "cancel". This is not an action, but we use it below
		cc.set("check.actions", new GList<String>().qadd("reset-velotity").qadd("cancel"));
	}
	
	//Now to make it actually check things
	//You dont need to register anything btw
	@EventHandler
	public void on(PlayerMoveEvent e)
	{
		//Check their speed in the worst possible way
		if(VectorMath.getSpeed(e.getFrom().clone().subtract(e.getTo()).toVector()) > maxSpeed)
		{
			//Cancel the event if the actions list contains "cancel"
			e.setCancelled(actions.contains("cancel"));
			
			//Execute all valid actions in the configurable list
			executeActions(e.getPlayer());
		}
	}
}

```

# 2. Register the new Check #
Edit the CheckCollider.java in the root package

* Create the field
* Initialize the check
* Register the check

``` java
package org.cyberpwn.hadron;

import org.cyberpwn.hadron.check.FastDrop;
import org.cyberpwn.hadron.check.FastMove;
import org.cyberpwn.phantom.construct.Controllable;
import org.cyberpwn.phantom.construct.Controller;

public class CheckCollider extends Controller
{
	//Add your check field here as final
	private final FastDrop fastDrop;
	private final FastMove fastMove;
	
	public CheckCollider(Controllable parentController)
	{
		super(parentController);
		
		//Initialize your check here
		this.fastDrop = new FastDrop(this);
		this.fastMove = new FastMove(this);
		
		//Register it
		register(fastDrop);
		register(fastMove);
	}
}
```