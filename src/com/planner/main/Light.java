package com.planner.main;

import javafx.scene.paint.Color;

public class Light extends Marker {

	public Light(double x, double y) {
		super(x, y, ID.Light, Color.CORAL);
		Count.Light++;
	}

}
