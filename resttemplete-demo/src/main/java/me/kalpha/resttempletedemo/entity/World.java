package me.kalpha.resttempletedemo.entity;


public class World {

    public World() {
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    String world;

    public World(String world) {
        this.world = world;
    }

    @Override
    public String toString() {
        return "World{" +
                "world='" + world + '\'' +
                '}';
    }
}