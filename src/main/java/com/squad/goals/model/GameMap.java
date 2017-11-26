package com.squad.goals.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GameMap {

	@Id
	@Column(name = "index")
	private Long index;

	@Column(name = "map_name")
    private String name;

	@Column(name = "corner0x")
    private double corner0x;

	@Column(name = "corner0y")
    private double corner0y;

	@Column(name = "corner1x")
    private double corner1x;

	@Column(name = "corner1y")
    private double corner1y;

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCorner0x() {
        return corner0x;
    }

    public void setCorner0x(double corner0x) {
        this.corner0x = corner0x;
    }

    public double getCorner0y() {
        return corner0y;
    }

    public void setCorner0y(double corner0y) {
        this.corner0y = corner0y;
    }

    public double getCorner1x() {
        return corner1x;
    }

    public void setCorner1x(double corner1x) {
        this.corner1x = corner1x;
    }

    public double getCorner1y() {
        return corner1y;
    }

    public void setCorner1y(double corner1y) {
        this.corner1y = corner1y;
    }
}
