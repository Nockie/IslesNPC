package com.Nockie.IslesNPC;
import org.bukkit.plugin.java.JavaPlugin;

import com.Nockie.IslesNPC.NMSUtils.MobType;
public class Main extends JavaPlugin {
	public void onEnable(){
		getLogger().info("Hello Stephen!");
		NMSUtils.registerEntity("IslesNPC", MobType.VILLAGER, IslesNPC2.class, false);

	}
	public void onDisable(){
		
	}

}