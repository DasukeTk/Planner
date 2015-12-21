package com.planner.main;

import javafx.scene.paint.Color;

public class Plug extends Marker{

	public Plug(double x, double y) {
		super(x, y, ID.Plug, Color.RED);
		Count.Plug++;
	}
	
}
