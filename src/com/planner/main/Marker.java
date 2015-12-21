package com.planner.main;

import java.util.ArrayList;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Marker {
	
	Color color;
	double x_coord;
	double y_coord;
	ID id;
	
	public Marker(double x, double y, ID id, Color color){
		this.x_coord = x;
		this.y_coord = y;
		this.id = id;
		this.color = color;
	}
	
	public static ArrayList<Marker> marker = new ArrayList<Marker>();
	public static ArrayList<Marker> deleted = new ArrayList<Marker>();
	
	public static void makeMarker(Marker object, Canvas canvas){
		marker.add(object);
		object.renderMarker(canvas, object);
	}
	
	public static boolean deleteMarker(double x, double y){
		boolean flag = true;
		for(Marker marker : marker){
			if(x < marker.x_coord + 8 && x > marker.x_coord - 8 && 
			   y < marker.y_coord + 8 && y > marker.y_coord - 8) {
				deleted.add(marker);
				flag = false;
			}
		}
		for(Marker marker : deleted){
			ID id = marker.id;
			if(id == ID.Plug){
				Count.Plug--;
			}
			if(id == ID.Switch){
				Count.Switch--;
			}
			if(id == ID.PlugGFI){
				Count.PlugGFI--;
			}
			if(id == ID.PlugWeatherProof){
				Count.PlugWeatherProof--;
			}
			if(id == ID.PlugTwoForty){
				Count.PlugTwoForty--;
			}
			if(id == ID.SwitchFourWay){
				Count.SwitchFourWay--;
			}
			if(id == ID.SwitchThreeWay){
				Count.SwitchThreeWay--;
			}
			if(id == ID.Light){
				Count.Light--;
			}
			if(id == ID.LightCan){
				Count.LightCan--;
			}
			if(id == ID.LightFan){
				Count.LightFan--;
			}
		}
		marker.removeAll(deleted);
		deleted.clear();
		return flag;
	}
	
	public static void reRender(Canvas canvas){
		canvas = Main.drawPlan(canvas);
		for(Marker marker : marker){
			marker.renderMarker(canvas, marker);
		}
	}

	public void renderMarker(Canvas canvas, Marker marker){
		GraphicsContext gc = canvas.getGraphicsContext2D();
    	gc.setFill(this.color);
    	gc.fillOval(this.x_coord - 8, this.y_coord - 8, 16, 16);
	}

}
