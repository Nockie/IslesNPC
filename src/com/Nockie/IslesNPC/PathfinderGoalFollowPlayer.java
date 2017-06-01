package com.Nockie.IslesNPC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_11_R1.EntityVillager;
import net.minecraft.server.v1_11_R1.MathHelper;
import net.minecraft.server.v1_11_R1.Navigation;
import net.minecraft.server.v1_11_R1.PathEntity;
import net.minecraft.server.v1_11_R1.PathfinderGoal;

public class PathfinderGoalFollowPlayer extends PathfinderGoal {
	private EntityVillager a;
	private Player target;
	private Navigation navigation;
	public PathfinderGoalFollowPlayer(EntityVillager a){
		this.a = a;
		this.navigation = (Navigation) this.a.getNavigation();
		this.a(1);
	}
	
	/**
     * Initial event.
     */
	@Override
	public boolean a() {
		if (checkPlayer(this.a)){
			c();
		}
		return checkPlayer(this.a);
	}
	/**
	 * Checks if a player is within 10 blocks of a villager
	 * @param npc
	 * 		The EntityVillager which will check for a nearby player
	 */
    public boolean checkPlayer(EntityVillager npc){
    	boolean near = false;
    	for(Entity player: getEntitiesAroundPoint(npc.locX,npc.locY,npc.locZ,10)){
    		if(player instanceof Player){
    			this.target =  (Player) player;
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
        //Sets up box around point
        int smallX = MathHelper.floor((locX - radius) / 16.0D);
        int bigX = MathHelper.floor((locX + radius) / 16.0D);
        int smallZ = MathHelper.floor((locZ - radius) / 16.0D);
        int bigZ = MathHelper.floor((locZ + radius) / 16.0D);
        //Loops through chunks to find entities
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
	 * Opposite of a.
	 */
	public boolean b(){
		return false;
		
	}
	/**
	 * Causes entity to walk to target location.
	 */
	public void c(){	
			Location loc = this.target.getLocation();
			PathEntity pathEntity = this.navigation.a(loc.getX(), loc.getY(), loc.getZ());
			this.navigation.a(pathEntity, 1);
	}
	
}


