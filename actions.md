# 1. Create an Action #
Make sure its in the action package

* You dont need to handle configs
``` java
package org.cyberpwn.hadron.action;

import org.bukkit.entity.Player;
import org.cyberpwn.phantom.construct.Controllable;
import org.cyberpwn.phantom.util.C;

//In the Action package
//Extend Action
public class Kick extends Action
{
    //Literally just for super or other construction
	public Kick(Controllable parentController)
	{
		super(parentController);
	}
	
    //Override act(Player p)
	@Override
	public void act(Player p)
	{
        //Do something
		p.kickPlayer(C.LIGHT_PURPLE + "Hadron Colliders have their risks.");
	}
}
```

# 2. Register the Action #
Edit the ActionCollider.java file 

* Define the field
* Init the action
* Register it

``` java
package org.cyberpwn.hadron;

public class ActionCollider extends Controller
{
    //Define the field
	private final CloseInventory closeInventory;
	private final Kick kick;
	private final ResetVelocity resetVelocity;
	private final Kill kill;
	
	public ActionCollider(Controllable parentController)
	{
		super(parentController);
		
        //Init the action
		closeInventory = new CloseInventory(this);
		kick = new Kick(this);
		resetVelocity = new ResetVelocity(this);
		kill = new Kill(this);
		
        //Register it
		register(closeInventory);
		register(kick);
		register(resetVelocity);
		register(kill);
	}
```