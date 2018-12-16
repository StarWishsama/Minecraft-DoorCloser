package net.tenrem.doorcloser;

import java.util.ArrayList;
import org.bukkit.Material;
import java.util.List;

public class Settings 
{
	public static int secondsToRemainOpen = 10;
	public static boolean ignoreIfInCreative = false;
	public static boolean ignoreIfSneaking = false;
	public static boolean playSound = true;

	public static boolean synchronizeDoubleDoorOpen = true;
	public static boolean synchronizeDoubleDoorClose = true;

	public static List<Material> doorsInScope = new ArrayList<Material>();
	public static List<Material> gatesInScope = new ArrayList<Material>();
	public static List<Material> trapDoorsInScope = new ArrayList<Material>();
	
	public static DoorCloserPlugin ThisPlugin;

	
	public static void Reload()
	{
		if (ThisPlugin != null)
		{
			ThisPlugin.reloadConfig();
		
			ReadConfigValues();
			
			ThisPlugin.getLogger().info("已经重载了配置文件.");
		}
	}
	
	public static void ReadConfigValues()
	{		
		if (ThisPlugin == null)
			return;
		
		// save the default config, if it's not already present
		ThisPlugin.saveDefaultConfig();		
		
		// read settings
		
		Settings.secondsToRemainOpen = ThisPlugin.getConfig().getInt("Time");
		Settings.ignoreIfInCreative = ThisPlugin.getConfig().getBoolean("IgnoreIfInCreative");
		Settings.ignoreIfSneaking = ThisPlugin.getConfig().getBoolean("IgnoreIfSneaking");
		Settings.playSound = ThisPlugin.getConfig().getBoolean("PlaySound");

		Settings.synchronizeDoubleDoorOpen = ThisPlugin.getConfig().getBoolean("SynchronizeDoubleDoorOpen");
		Settings.synchronizeDoubleDoorClose = ThisPlugin.getConfig().getBoolean("SynchronizeDoubleDoorClose");

		
		List<String> trapDoorsInScopeStrings = (List<String>) ThisPlugin.getConfig().getStringList("TrapDoorBlocks");
		List<String> gatesInScopeStrings = (List<String>) ThisPlugin.getConfig().getStringList("GateBlocks");
		List<String> doorsInScopeStrings = (List<String>) ThisPlugin.getConfig().getStringList("DoorBlocks");

		trapDoorsInScope.clear();
		gatesInScope.clear();
		doorsInScope.clear();
		
		for (String val : trapDoorsInScopeStrings)
		{
			Material m = Material.matchMaterial(val);	
			
			if (m != null)
			{
				Settings.trapDoorsInScope.add(m);
			}
			else
			{
				ThisPlugin.getLogger().warning("在配置中发现了错误的值 (活板门) '" + val + "'.");
			}
		}
		
		for (String val : gatesInScopeStrings)
		{
			Material m = Material.matchMaterial(val);

			if (m != null)
			{
				Settings.gatesInScope.add(m);
			}
			else
			{
				ThisPlugin.getLogger().warning("在配置中发现了错误的值 (栅栏门) '" + val + "'.");
			}
		}
		
		for (String val : doorsInScopeStrings)
		{
			Material m = Material.matchMaterial(val);

			if (m != null)
			{
				Settings.doorsInScope.add(m);
			}
			else
			{
				ThisPlugin.getLogger().warning("在配置中发现了错误的值 (门) '" + val + "'.");
			}
	
		}
		
		
		// log the read settings out to the server log
		ThisPlugin.getLogger().info("保持打开的秒数: " + Settings.secondsToRemainOpen);
		ThisPlugin.getLogger().info("是否忽略创造模式玩家: " + Settings.ignoreIfInCreative);
		ThisPlugin.getLogger().info("是否忽略潜行中的玩家: " + Settings.ignoreIfSneaking);
		ThisPlugin.getLogger().info("播放音效: " + Settings.playSound);
		
		if (Settings.trapDoorsInScope.isEmpty() && Settings.gatesInScope.isEmpty() && Settings.doorsInScope.isEmpty())
		{
			ThisPlugin.getLogger().warning("没有配置需要自动关闭的门, 栅栏门或者陷阱门, 你确定你的配置是正确的吗?");
		}
		else
		{
			ThisPlugin.getLogger().info("已配置的陷阱门个数: " + Settings.trapDoorsInScope.size());
			ThisPlugin.getLogger().info("已配置的栅栏门个数: " + Settings.gatesInScope.size());
			ThisPlugin.getLogger().info("已配置的门个数: " + Settings.doorsInScope.size());
		}
				
	}
	
	
}
