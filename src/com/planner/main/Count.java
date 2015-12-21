package com.planner.main;

import java.util.ArrayList;

public class Count {

	public static int Plug = 0;
	public static int PlugGFI = 0;
	public static int PlugWeatherProof = 0;
	public static int PlugTwoForty = 0;
	public static int Switch = 0;
	public static int SwitchThreeWay = 0;
	public static int SwitchFourWay = 0;
	public static int Light = 0;
	public static int LightFan = 0;
	public static int LightCan = 0;
	
	public static void clearCount(){
		Plug = 0;
		PlugGFI = 0;
		PlugWeatherProof = 0;
		PlugTwoForty = 0;
		Switch = 0;
		SwitchThreeWay = 0;
		SwitchFourWay = 0;
		Light = 0;
		LightFan = 0;
		LightCan = 0;
	}
	
	public static ArrayList<String> makeList(){
		ArrayList<String> list = new ArrayList<String>();
		list.add(0, "Plug");
		list.add(1, Integer.toString(Plug));
		list.add(2, "Plug - GFI");
		list.add(3, Integer.toString(PlugGFI));
		list.add(4, "Plug - WP");
		list.add(5, Integer.toString(PlugWeatherProof));
		list.add(6, "Plug - 240V");
		list.add(7, Integer.toString(PlugTwoForty));
		list.add(8, "Switch");
		list.add(9, Integer.toString(Switch));
		list.add(10, "Switch - 3W");
		list.add(11, Integer.toString(SwitchThreeWay));
		list.add(12, "Switch - 4W");
		list.add(13, Integer.toString(SwitchFourWay));
		list.add(14, "Light");
		list.add(15, Integer.toString(Light));
		list.add(16, "Light - Fan");
		list.add(17, Integer.toString(LightFan));
		list.add(18, "Light - Can");
		list.add(19, Integer.toString(LightCan));
		return list;
	}
}
