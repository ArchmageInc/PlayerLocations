package com.archmageinc.playerlocations.plugin.info;

public class PlayerInfo {
    String name;
    PositionInfo position;
    Double health;
    Integer level;
    Integer air;
    Integer food;

    public String getName() {
        return name;
    }

    public PlayerInfo setName(String name) {
        this.name = name;
        return this;
    }

    public PositionInfo getPosition() {
        return position;
    }

    public PlayerInfo setPosition(PositionInfo position) {
        this.position = position;
        return this;
    }

    public Double getHealth() {
        return health;
    }

    public PlayerInfo setHealth(Double health) {
        this.health = health;
        return this;
    }

    public Integer getLevel() {
        return level;
    }

    public PlayerInfo setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Integer getAir() {
        return air;
    }

    public PlayerInfo setAir(Integer air) {
        this.air = air;
        return this;
    }
    
    public Integer getFood() {
        return food;
    }

    public PlayerInfo setFood(Integer food) {
        this.food = food;
        return this;
    }
}
