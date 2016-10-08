package org.cyberpwn.hadron.fix;

import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.phantomapi.construct.Controller;

public class ChunkPatcher extends Controller implements Listener
{
	private final SkullExploitPatch plugin;
	
	public ChunkPatcher(Controller parent, SkullExploitPatch skullExploitPatch)
	{
		super(parent);
		
		this.plugin = skullExploitPatch;
	}
	
	@EventHandler
	public void onLoad(ChunkLoadEvent e)
	{
		plugin.cleanChunk(e.getChunk());
	}
	
	@EventHandler
	public void onLoad(WorldLoadEvent e)
	{
		for(Chunk c : e.getWorld().getLoadedChunks())
		{
			plugin.cleanChunk(c);
		}
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
