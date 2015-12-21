package com.planner.main;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Stats{
	
	static GridPane gridpane;

	public static void run(){
		Stage stage = new Stage();
		stage.setTitle("Stats");
		
		VBox vbox = vbox();
		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.show();
	}
	
	public static VBox vbox(){
		VBox vbox = new VBox();
		MenuBar menuBar = new MenuBar();
		
		Menu menuFile = new Menu("File");
		MenuItem savegrid = new MenuItem("Save Stats", new ImageView(new Image("file:images/menu/newplan.png")));
		savegrid.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent t) {
        		SaveNLoad.launchAppSaveGridPane(gridpane);
            }
        });
     
        menuFile.getItems().addAll(savegrid);
        
        
        menuBar.getMenus().addAll(menuFile);
        
        gridpane = gridpane();
		
		
		vbox.getChildren().addAll(menuBar,gridpane);
		return vbox;
	}
	
	public static GridPane gridpane(){
		GridPane gridpane = new GridPane();
		gridpane.setHgap(20);
		gridpane.setVgap(10);
		gridpane.setPadding(new Insets(0, 10, 0, 10));
		
		Text markers = new Text("Marker:");
		markers.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
	    gridpane.add(markers, 0, 0);
	    
	    Text counts = new Text("Count:");
	    counts.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
	    gridpane.add(counts, 1, 0);
	    
	    Text prices = new Text("Price:");
	    prices.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
	    gridpane.add(prices, 2, 0);
	    
	    Text totals = new Text("Total:");
	    totals.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
	    gridpane.add(totals, 3, 0);
	    
	    ArrayList<String> list = Count.makeList();
	    int j = 1;
	    for(int i = 0; i < list.size(); i++){
	    	Text marker = new Text(list.get(i));
	    	marker.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
		    gridpane.add(marker, 0, j);
		    i++;
		    Text count = new Text(list.get(i));
		    count.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
		    gridpane.add(count, 1, j);
		    j++;
	    }
	    
		return gridpane;
	}

}
