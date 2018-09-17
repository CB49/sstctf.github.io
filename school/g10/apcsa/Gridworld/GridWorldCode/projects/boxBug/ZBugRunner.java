/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.BoundedGrid;
import java.awt.Color;
import java.util.ArrayList;
import java.lang.Math;
/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ZBugRunner
{
    //public ArrayList<Color> colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};
    public static void main(String[] args)
    {

        ActorWorld world = new ActorWorld(new BoundedGrid<>(20,20));
        //ZBUG
        ZBug brill = new ZBug(2);
        brill.setColor(Color.MAGENTA);
        world.add(new Location(((int)(Math.random()*15)+1),((int)(Math.random()*15))), brill);
        world.show();

        ZBug phil = new ZBug(3);
        phil.setColor(Color.BLACK);
        world.add(new Location(((int)(Math.random()*15)+1),((int)(Math.random()*15))), phil);
        world.show();

        ZBug neil = new ZBug(4);
        neil.setColor(Color.GREEN);
        world.add(new Location(((int)(Math.random()*15)+1),((int)(Math.random()*15))), neil);
        world.show();

        ZBug creal = new ZBug(5);
        creal.setColor(Color.PINK);
        world.add(new Location(((int)(Math.random()*15)),((int)(Math.random()*15))), creal);
        world.show();

        ZBug notbob = new ZBug(6);
        notbob.setColor(Color.ORANGE);
        world.add(new Location(((int)(Math.random()*15)),((int)(Math.random()*15))), notbob);
        world.show();
    }
}