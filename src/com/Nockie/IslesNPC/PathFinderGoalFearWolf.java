package com.Nockie.IslesNPC;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Wolf;

import net.minecraft.server.v1_11_R1.EntityCreature;
import net.minecraft.server.v1_11_R1.PathfinderGoal;

public class PathFinderGoalFearWolf extends PathfinderGoal {
	private EntityCreature a;
	private boolean scared;
	public PathFinderGoalFearWolf(EntityCreature a){
		this.a = a;
		this.a(1);

	}
	
	/**
	 * Performs initial operations.
	 */
	@Override
	public boolean a() {
		if (this.a.bu()){
			return false;
		} else {
			return checkWolf();
		}
	}
	/**
	 * Checks if a Wolf is within 8 blocks of the entity.
	 */
	public boolean checkWolf(){
		scared = false;
        Location EntityArea = new Location(Bukkit.getWorld("world"),this.a.locX,this.a.locY,this.a.locZ);
        World world = Bukkit.getServer().getWorld("world");
		for(org.bukkit.entity.Entity wolf: world.getNearbyEntities(EntityArea, 8, 8, 8)){
			if(wolf instanceof Wolf){
				this.scared = true;
			}
		}
		return scared;
	}
	/**
	 * Opposite of a.
	 */
	public boolean b(){
		return false;
		
	}
	/**
	 * Makes villager jump
	 */
	public void c(){
		if(a.onGround){
			a.setLocation(a.locX,a.locY+1,a.locZ, a.pitch,a.yaw);
		}
	}
}

