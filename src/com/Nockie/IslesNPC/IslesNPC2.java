package com.Nockie.IslesNPC;


import net.minecraft.server.v1_11_R1.EntityHuman;
import net.minecraft.server.v1_11_R1.EntityVillager;
import net.minecraft.server.v1_11_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_11_R1.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_11_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_11_R1.PathfinderGoalRandomStrollLand;


public class IslesNPC2 extends EntityVillager{
		EntityHuman target;
	    public IslesNPC2(net.minecraft.server.v1_11_R1.World world) {
	        super(world);

	    }
	    /**
	     * Acts as AI for NPC
	     */
	    @Override
	    protected void r() {
	    	//Causes the NPC to jump near wolves
	        this.goalSelector.a(0, new PathFinderGoalFearWolf(this));
	        //Causes the NPC to run from creatures
	        this.goalSelector.a(1, new PathfinderGoalFearCreeper(this));
	        //Causes the NPC to follow players
	        this.goalSelector.a(2, new PathfinderGoalFollowPlayer(this));
	        //Causes the NPC to walk around randomly
	        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0));
	        // Causes the NPC to walk around randomly.
	        this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0));
	        // Causes the NPC to look at players
	        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0f));
	        // Causes the NPC to look around.
	        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
	    }
	    
}
