package com.archmageinc.playerlocations.info;

public class PositionInfo {
    Double x;
    Double y;
    Double z;
    String dimension;

    public Double getX() {
        return x;
    }

    public PositionInfo setX(Double x) {
        this.x = x;
        return this;
    }

    public Double getY() {
        return y;
    }

    public PositionInfo setY(Double y) {
        this.y = y;
        return this;
    }

    public Double getZ() {
        return z;
    }

    public PositionInfo setZ(Double z) {
        this.z = z;
        return this;
    }

    public String getDimension() {
        return dimension;
    }

    public PositionInfo setDimension(String dimension) {
        this.dimension = dimension;
        return this;
    }
    
}
