package net.tenrem.doorcloser;

import org.bukkit.plugin.java.JavaPlugin;

public class DoorCloserPlugin extends JavaPlugin 
{
	@Override
	public void onEnable() 
	{	
		Settings.ThisPlugin = this;
		
		Settings.ReadConfigValues();
	
		// set up commands
		RegisterCommands();

		// register event listener
		RegisterEvents();

		this.getLogger().info("请注意, 该版本的 DoorCloser 仅支持 v1.13.x!");
	}

	
	private void RegisterEvents()
	{
		getServer().getPluginManager().registerEvents(new InteractListener(this), this);
		
	}
	
	private void RegisterCommands()
	{
		getCommand("dcreload").setExecutor(new CommandReload(this));
	}
	
	
	
	
	@Override
	public void onDisable() 
	{
		// removal of commands and events is done automatically by Bukkit/Spigot
		
		//getLogger().info("onDisable has been invoked!");
	}


}
