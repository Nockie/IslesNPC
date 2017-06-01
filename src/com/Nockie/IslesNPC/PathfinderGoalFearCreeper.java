package com.Nockie.IslesNPC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;

import net.minecraft.server.v1_11_R1.EntityVillager;
import net.minecraft.server.v1_11_R1.MathHelper;
import net.minecraft.server.v1_11_R1.Navigation;
import net.minecraft.server.v1_11_R1.PathEntity;
import net.minecraft.server.v1_11_R1.PathfinderGoal;

public class PathfinderGoalFearCreeper extends PathfinderGoal {
	private EntityVillager a;
	private Creeper target;
	private Navigation navigation;
	public PathfinderGoalFearCreeper(EntityVillager a){
		this.a = a;
		this.navigation = (Navigation) this.a.getNavigation();
		this.a(1);
	}
	
	/**
	 * Performs initial operations.
	 */
	@Override
	public boolean a() {
		if (checkCreeper(this.a)){
			c();
		}
		return checkCreeper(this.a);
	}
	/**
	 * Finds the entities around a point and returns them as a List of Entities.
	 * @param npc
	 * 		The EntityVillager which will check for a nearby creeper
	 */
    public boolean checkCreeper(EntityVillager npc){
    	boolean near = false;
    	for(Entity player: getEntitiesAroundPoint(npc.locX,npc.locY,npc.locZ,5)){
    		if(player instanceof Creeper){
    			this.target =  (Creeper) player;
    			near = true;
    		}
    	}
    	return near;
    }
	/**
	 * Finds the entities around a point and returns them as a List of Entities.
	 * @param locX
	 * 		The x coordinate of the desired point
	 * @param locY
	 * 		The Y coordinate of the desired point
	 * @param locZ
	 * 		The Z coordinate of the desired point
	 * @param radius
	 * 		The cubic radius around the point which will be checked.
	 */
    public List<Entity> getEntitiesAroundPoint(double locX, double locY, double locZ, double radius) {
        List<Entity> entities = new ArrayList<Entity>();
        World world = Bukkit.getServer().getWorld("world");
        int smallX = MathHelper.floor((locX - radius) / 16.0D);
        int bigX = MathHelper.floor((locX + radius) / 16.0D);
        int smallZ = MathHelper.floor((locZ - radius) / 16.0D);
        int bigZ = MathHelper.floor((locZ + radius) / 16.0D);

        for (int x = smallX; x <= bigX; x++) {
            for (int z = smallZ; z <= bigZ; z++) {
                if (world.isChunkLoaded(x, z)) {
                    entities.addAll(Arrays.asList(world.getChunkAt(x, z).getEntities()));
                }
            }
        }
        return entities;
    }
    /**
     * Opposite of a
     */
	public boolean b(){
		return false;
		
	}
	/**
	 * Causes entity to run away from creeper
	 */

	public void c(){	
			Location loc = this.target.getLocation();
			double movX = 0;
			double movZ = 0;
			if (loc.getX()<this.a.locX){
				movX = .5;
			} else {
				movX = -.5;
			}
			if (loc.getZ()<this.a.locZ){
				movZ = .5;
			} else {
				movZ = -.5;
			}
			PathEntity pathEntity = this.navigation.a(a.locX+movX, a.locY, a.locZ+movZ);
			this.navigation.a(pathEntity, 1);
	}
	
}
