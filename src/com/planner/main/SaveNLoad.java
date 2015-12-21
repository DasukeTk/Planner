package com.planner.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
 
public class SaveNLoad {

    public static void launchAppSave() {
    	if(Images.image1 == null){
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Can't Save");
    		alert.setHeaderText("Couldn't find plan.png");
    		alert.setContentText("Please open a plan image before saving.");

    		alert.showAndWait();
    		return;
    	}
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Plann files (*.plann)", "*.plann");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Show save file dialog
        File file = fileChooser.showSaveDialog(null);
        
        if(file != null){
            SaveFile(makeSave(), file);
        }
    }
    
    public static void launchAppLoad(Canvas canvas) {	
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Plann files (*.plann)", "*.plann");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Show save file dialog
        File file = fileChooser.showOpenDialog(null);
        
        if(file != null){
            LoadFile(file, canvas);
        }
    }
    
    public static void launchAppSaveCanvas(Canvas canvas) {
    	WritableImage wim = new WritableImage((int) canvas.getWidth(),(int) canvas.getHeight());
    	canvas.snapshot(null, wim);
    	
    	FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Show save file dialog
        File file = fileChooser.showSaveDialog(null);
        
        if(file != null){
        	try {
                ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
            } catch (Exception s) {
            }
        }
    }
    
    public static void launchAppSaveGridPane(GridPane gridpane) {
    	WritableImage wim = new WritableImage((int) gridpane.getWidth(),(int) gridpane.getHeight());
    	gridpane.snapshot(null, wim);
    	
    	FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Show save file dialog
        File file = fileChooser.showSaveDialog(null);
        
        if(file != null){
        	try {
                ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
            } catch (Exception s) {
            }
        }
    }

	public static String makeSave(){
    	String save = "File\n";
    	save += Images.loadImageString + "\n";
    	save += "Markers\n";
    	for(Marker marker : Marker.marker){
    		save += marker.id + "\n" + marker.x_coord + "\n" + marker.y_coord + "\n";
    	}
		return save;
    }
        
    private static void SaveFile(String content, File file){
        try {
            FileWriter fileWriter = null;
             
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {}        
    }
    
    private static void LoadFile(File file, Canvas canvas){
    	Marker.marker.clear();
    	Marker.deleted.clear();
    	Count.clearCount();
    	
    	String load = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuilder sb = new StringBuilder();
    	    String line = br.readLine();
    	    while (line != null) {
    	        sb.append(line);
    	        sb.append(System.lineSeparator());
    	        line = br.readLine();
    	    }
    	    load = sb.toString();
    	    br.close();
		} catch (IOException e) { }
		String loadStr[] = load.split("\\r?\\n");
		int count = 0;
		if(loadStr[count].equals("File")){
			count++;
		} else { return; }
		if(loadStr[count] == null){ return; }
		Images.loadImageString = loadStr[count];
		Images.image1 = new Image(Images.loadImageString, 0, 0, false, false);
		count++;
		if(loadStr[count].equals("Markers")){
			count++;
		} else { return; }
		while(count < loadStr.length){
			if(loadStr[count].equals("Plug")){
				Marker.makeMarker(new Plug(Double.parseDouble(loadStr[count + 1]), Double.parseDouble(loadStr[count + 2])), canvas);
			} else if (loadStr[count].equals("Switch")){
				Marker.makeMarker(new Switch(Double.parseDouble(loadStr[count + 1]), Double.parseDouble(loadStr[count + 2])), canvas);
			} else if (loadStr[count].equals("SwitchFourWay")){
				Marker.makeMarker(new SwitchFourWay(Double.parseDouble(loadStr[count + 1]), Double.parseDouble(loadStr[count + 2])), canvas);
			} else if (loadStr[count].equals("SwitchThreeWay")){
				Marker.makeMarker(new SwitchThreeWay(Double.parseDouble(loadStr[count + 1]), Double.parseDouble(loadStr[count + 2])), canvas);
			} else if (loadStr[count].equals("Light")){
				Marker.makeMarker(new Light(Double.parseDouble(loadStr[count + 1]), Double.parseDouble(loadStr[count + 2])), canvas);
			} else if (loadStr[count].equals("LightFan")){
				Marker.makeMarker(new LightFan(Double.parseDouble(loadStr[count + 1]), Double.parseDouble(loadStr[count + 2])), canvas);
			} else if (loadStr[count].equals("LightCan")){
				Marker.makeMarker(new LightCan(Double.parseDouble(loadStr[count + 1]), Double.parseDouble(loadStr[count + 2])), canvas);
			} else if (loadStr[count].equals("PlugGFI")){
				Marker.makeMarker(new PlugGFI(Double.parseDouble(loadStr[count + 1]), Double.parseDouble(loadStr[count + 2])), canvas);
			} else if (loadStr[count].equals("PlugTwoForty")){
				Marker.makeMarker(new PlugTwoForty(Double.parseDouble(loadStr[count + 1]), Double.parseDouble(loadStr[count + 2])), canvas);
			} else if (loadStr[count].equals("PlugWeatherProof")){
				Marker.makeMarker(new PlugWeatherProof(Double.parseDouble(loadStr[count + 1]), Double.parseDouble(loadStr[count + 2])), canvas);
			}
			count += 3;
		}
		Marker.reRender(canvas);
    }

}
