package org.cyberpwn.hadron;

import org.phantomapi.command.CommandController;
import org.phantomapi.command.CommandFilter;
import org.phantomapi.command.PhantomCommand;
import org.phantomapi.command.PhantomCommandSender;
import org.phantomapi.construct.Controllable;
import org.phantomapi.lang.GList;
import org.phantomapi.util.C;
import org.phantomapi.util.F;
import org.phantomapi.util.Timer;

public class CommandCollider extends CommandController
{
	public CommandCollider(Controllable parentController)
	{
		super(parentController, "hadron");
	}
	
	@Override
	public String getChatTag()
	{
		return C.DARK_GRAY + "[" + C.LIGHT_PURPLE + "H" + C.DARK_GRAY + "]" + C.GRAY + ": ";
	}
	
	@Override
	public String getChatTagHover()
	{
		return C.LIGHT_PURPLE + "Hadron " + getPlugin().getDescription().getVersion();
	}
	
	@Override
	@CommandFilter.Permission("hadron.reload")
	@CommandFilter.ArgumentRange({0, 1})
	public boolean onCommand(PhantomCommandSender sender, PhantomCommand command)
	{
		String tag = "";
		String[] args = command.getArgs();
		
		if(!sender.hasPermission("hadron.reload"))
		{
			sender.sendMessage(tag + C.RED + "It was the collider this time. I swear.");
		}
		
		else if(args.length == 0)
		{
			sender.sendMessage(tag + C.LIGHT_PURPLE + "Collider " + C.GREEN + " v" + getPlugin().getDescription().getVersion() + " by " + new GList<String>(getPlugin().getDescription().getAuthors()).toString(" & "));
			sender.sendMessage(tag + C.LIGHT_PURPLE + "/" + "hadron,h " + C.GOLD + "reload,r");
		}
		
		else if(args.length > 0)
		{
			if(args[0].equalsIgnoreCase("r") || args[0].equalsIgnoreCase("reload"))
			{
				Timer t = new Timer();
				t.start();
				sender.sendMessage(tag + "Reloading Hadron");
				Hadron.instance().reload();
				t.stop();
				sender.sendMessage(tag + "Done in " + F.nsMs(t.getTime(), 3) + "ms");
			}
			
			else
			{
				sender.sendMessage(tag + C.RED + "Again, no idea what you are talking about.");
			}
		}
		
		return true;
	}
	
	@Override
	public GList<String> getCommandAliases()
	{
		return new GList<String>(new String[]{"h", "ha", "collider", "had"});
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
