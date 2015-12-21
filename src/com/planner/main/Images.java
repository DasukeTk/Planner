package com.planner.main;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import java.io.File;
import javafx.stage.FileChooser;

public class Images{

	static Image image1;
	static File image1file;
	static String loadImageString;
 
    public static void launchApp(Canvas canvas) {
    	final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        image1file = fileChooser.showOpenDialog(null);  
        loadImageString = image1file.toURI().toString();
    	image1 = new Image(loadImageString, 0, 0, false, false);
    	canvas = Main.drawPlan(canvas);
    	canvas.setHeight(image1.getHeight());
    	canvas.setWidth(image1.getWidth());
    }
 
    private static void configureFileChooser(
        final FileChooser fileChooser) {      
            fileChooser.setTitle("View Pictures");
            fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
            );                 
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
            );
    }
    

}


