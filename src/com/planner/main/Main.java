package com.planner.main;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
 
public class Main extends Application {
	
	Rectangle2D bounds;
	static RadioButton rbPlug;
	static RadioButton rbPlugTwoForty;
	static RadioButton rbPlugWeatherProof;
	static RadioButton rbPlugGFI;
	static RadioButton rbSwitch;
	static RadioButton rbSwitchFourWay;
	static RadioButton rbSwitchThreeWay;
	static RadioButton rbLight;
	static RadioButton rbLightCan;
	static RadioButton rbLightFan;
	
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Planner");
        
        Screen screen = Screen.getPrimary();
        bounds = screen.getVisualBounds();
        primaryStage.setMaximized(true);
        
        BorderPane border = new BorderPane();
        Canvas canvas = Canvas(primaryStage);
        
        
        MenuBar menuBar = MenuBar(canvas);
        ScrollPane sp = new ScrollPane();
        sp.setMaxWidth(bounds.getWidth() - 230);
        sp.setMaxHeight(bounds.getHeight());
        sp.setContent(canvas);

        
        VBox vbox = VBox(canvas);
        
        border.setTop(menuBar);
        border.setCenter(sp);
        
        StackPane right = new StackPane();
        right.getChildren().addAll(vbox);
        right.setMinWidth(200);
        border.setRight(right);

        Scene scene = new Scene(border);        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	public MenuBar MenuBar(Canvas canvas){
    	MenuBar menuBar = new MenuBar();
    	
        Menu menuFile = new Menu("File");
        MenuItem newplan = new MenuItem("New", new ImageView(new Image("file:images/menu/newplan.png")));
        newplan.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent t) {
        		Images.launchApp(canvas);
        		Marker.reRender(canvas);
            }
        });
        MenuItem saveplan = new MenuItem("Save", new ImageView(new Image("file:images/menu/newplan.png")));
        saveplan.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent t) {
        		SaveNLoad.launchAppSave();
            }
        });
        MenuItem loadplan = new MenuItem("Load", new ImageView(new Image("file:images/menu/newplan.png")));
        loadplan.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent t) {
        		SaveNLoad.launchAppLoad(canvas);
        		updateRadios();
            }
        });
        MenuItem savecanvas = new MenuItem("Save Canvas", new ImageView(new Image("file:images/menu/newplan.png")));
        savecanvas.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent t) {
        		SaveNLoad.launchAppSaveCanvas(canvas);
            }
        });
     
        menuFile.getItems().addAll(newplan, saveplan, loadplan, savecanvas);
        
        Menu menuEdit = new Menu("Edit");
        MenuItem clearPlan = new MenuItem("Clear", new ImageView(new Image("file:images/menu/newplan.png")));
        clearPlan.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent t) {
        		Marker.marker.clear();
        		Marker.deleted.clear();
        		Count.clearCount();
        		Marker.reRender(canvas);
        		updateRadios();
            }
        });
        
        menuEdit.getItems().addAll(clearPlan);
 
        Menu menuView = new Menu("View");
        MenuItem stats = new MenuItem("Stats", new ImageView(new Image("file:images/menu/newplan.png")));
        stats.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent t) {
        		Stats.run();
            }
        });
        
        menuView.getItems().addAll(stats);
 
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);
        
        return menuBar;
    }
    
    public Canvas Canvas(Stage primaryStage){
    	Canvas canvas = new Canvas(4000,4000);
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	gc.setFill(Color.ANTIQUEWHITE);
    	gc.fillRect(0,0,bounds.getWidth()-230,bounds.getHeight());
		return canvas;
    }
    
    public static Canvas drawPlan(Canvas canvas){
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	gc.drawImage(Images.image1, 0, 0);
		return canvas;    	
    }
    
    public VBox VBox(Canvas canvas){
    	VBox vbox = new VBox();
    	vbox.setMinWidth(200);
    	vbox.setMaxWidth(200);
    	
    	final ToggleGroup group = new ToggleGroup();
    	rbPlug = new RadioButton("Plug - " + Count.Plug);
    	rbPlug.setToggleGroup(group);
    	rbPlug.setTextFill(Color.RED);
    	rbPlugGFI = new RadioButton("Plug GFI - " + Count.PlugGFI);
    	rbPlugGFI.setToggleGroup(group);
    	rbPlugGFI.setTextFill(Color.FIREBRICK);
    	rbPlugTwoForty = new RadioButton("Plug 240V - " + Count.PlugTwoForty);
    	rbPlugTwoForty.setToggleGroup(group);
    	rbPlugTwoForty.setTextFill(Color.DARKSEAGREEN);
    	rbPlugWeatherProof = new RadioButton("Plug WP - " + Count.PlugWeatherProof);
    	rbPlugWeatherProof.setToggleGroup(group);
    	rbPlugWeatherProof.setTextFill(Color.INDIGO);
    	rbSwitch = new RadioButton("Switch - " + Count.Switch);
    	rbSwitch.setToggleGroup(group);
    	rbSwitch.setTextFill(Color.BLUE);
    	rbSwitchFourWay = new RadioButton("Switch 4W - " + Count.SwitchFourWay);
    	rbSwitchFourWay.setToggleGroup(group);
    	rbSwitchFourWay.setTextFill(Color.BROWN);
    	rbSwitchThreeWay = new RadioButton("Switch 3W - " + Count.SwitchThreeWay);
    	rbSwitchThreeWay.setToggleGroup(group);
    	rbSwitchThreeWay.setTextFill(Color.BLUEVIOLET);
    	rbLight = new RadioButton("Light - " + Count.Light);
    	rbLight.setToggleGroup(group);
    	rbLight.setTextFill(Color.CORAL);
    	rbLightFan = new RadioButton("Light Fan - " + Count.LightFan);
    	rbLightFan.setToggleGroup(group);
    	rbLightFan.setTextFill(Color.DARKCYAN);
    	rbLightCan = new RadioButton("Light Can - " + Count.LightCan);
    	rbLightCan.setToggleGroup(group);
    	rbLightCan.setTextFill(Color.DARKORANGE);
    	
    	group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
    	    public void changed(ObservableValue<? extends Toggle> ov,
    	        Toggle old_toggle, Toggle new_toggle) {
    	            if(rbPlug.isSelected()){
    	            	canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
    	                    public void handle(MouseEvent mouse) {
    	                    	if(Marker.deleteMarker(mouse.getX(), mouse.getY())){
	    	                    	Marker.makeMarker(new Plug(mouse.getX(), mouse.getY()), canvas);
    	                    	} else {
    	                    		Marker.reRender(canvas);
    	                    	}
    	                    	updateRadios();
    	                    }
    	                });
    	            } else if(rbSwitch.isSelected()){
    	            	canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
    	                    public void handle(MouseEvent mouse) {
    	                    	if(Marker.deleteMarker(mouse.getX(), mouse.getY())){
	    	                    	Marker.makeMarker(new Switch(mouse.getX(), mouse.getY()), canvas);
    	                    	} else {
    	                    		Marker.reRender(canvas);
    	                    	}
    	                    	updateRadios();
    	                    }
    	                });
    	            } else if(rbLight.isSelected()){
    	            	canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
    	                    public void handle(MouseEvent mouse) {
    	                    	if(Marker.deleteMarker(mouse.getX(), mouse.getY())){
	    	                    	Marker.makeMarker(new Light(mouse.getX(), mouse.getY()), canvas);
    	                    	} else {
    	                    		Marker.reRender(canvas);
    	                    	}
    	                    	updateRadios();
    	                    }
    	                });
    	            } else if(rbLightFan.isSelected()){
    	            	canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
    	                    public void handle(MouseEvent mouse) {
    	                    	if(Marker.deleteMarker(mouse.getX(), mouse.getY())){
	    	                    	Marker.makeMarker(new LightFan(mouse.getX(), mouse.getY()), canvas);
    	                    	} else {
    	                    		Marker.reRender(canvas);
    	                    	}
    	                    	updateRadios();
    	                    }
    	                });
    	            } else if(rbLightCan.isSelected()){
    	            	canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
    	                    public void handle(MouseEvent mouse) {
    	                    	if(Marker.deleteMarker(mouse.getX(), mouse.getY())){
	    	                    	Marker.makeMarker(new LightCan(mouse.getX(), mouse.getY()), canvas);
    	                    	} else {
    	                    		Marker.reRender(canvas);
    	                    	}
    	                    	updateRadios();
    	                    }
    	                });
    	            } else if(rbPlugGFI.isSelected()){
    	            	canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
    	                    public void handle(MouseEvent mouse) {
    	                    	if(Marker.deleteMarker(mouse.getX(), mouse.getY())){
	    	                    	Marker.makeMarker(new PlugGFI(mouse.getX(), mouse.getY()), canvas);
    	                    	} else {
    	                    		Marker.reRender(canvas);
    	                    	}
    	                    	updateRadios();
    	                    }
    	                });
    	            } else if(rbPlugTwoForty.isSelected()){
    	            	canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
    	                    public void handle(MouseEvent mouse) {
    	                    	if(Marker.deleteMarker(mouse.getX(), mouse.getY())){
	    	                    	Marker.makeMarker(new PlugTwoForty(mouse.getX(), mouse.getY()), canvas);
    	                    	} else {
    	                    		Marker.reRender(canvas);
    	                    	}
    	                    	updateRadios();
    	                    }
    	                });
    	            } else if(rbPlugWeatherProof.isSelected()){
    	            	canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
    	                    public void handle(MouseEvent mouse) {
    	                    	if(Marker.deleteMarker(mouse.getX(), mouse.getY())){
	    	                    	Marker.makeMarker(new PlugWeatherProof(mouse.getX(), mouse.getY()), canvas);
    	                    	} else {
    	                    		Marker.reRender(canvas);
    	                    	}
    	                    	updateRadios();
    	                    }
    	                });
    	            } else if(rbSwitchFourWay.isSelected()){
    	            	canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
    	                    public void handle(MouseEvent mouse) {
    	                    	if(Marker.deleteMarker(mouse.getX(), mouse.getY())){
	    	                    	Marker.makeMarker(new SwitchFourWay(mouse.getX(), mouse.getY()), canvas);
    	                    	} else {
    	                    		Marker.reRender(canvas);
    	                    	}
    	                    	updateRadios();
    	                    }
    	                });
    	            } else if(rbSwitchThreeWay.isSelected()){
    	            	canvas.setOnMousePressed(new EventHandler<MouseEvent>() {
    	                    public void handle(MouseEvent mouse) {
    	                    	if(Marker.deleteMarker(mouse.getX(), mouse.getY())){
	    	                    	Marker.makeMarker(new SwitchThreeWay(mouse.getX(), mouse.getY()), canvas);
    	                    	} else {
    	                    		Marker.reRender(canvas);
    	                    	}
    	                    	updateRadios();
    	                    }
    	                });
    	            }
    	        }
    	});
    	
        vbox.getChildren().addAll(rbPlug, rbPlugGFI, rbPlugTwoForty,
        		rbPlugWeatherProof, rbSwitch, rbSwitchThreeWay, 
        		rbSwitchFourWay, rbLight, rbLightFan, rbLightCan);
    	return vbox;
    }
    
    public static void updateRadios(){
    	rbPlug.setText("Plug - " + Count.Plug);
    	rbPlugGFI.setText("Plug GFI - " + Count.PlugGFI);
    	rbPlugTwoForty.setText("Plug 240V - " + Count.PlugTwoForty);
    	rbPlugWeatherProof.setText("Plug WP - " + Count.PlugWeatherProof);
    	rbSwitch.setText("Switch - " + Count.Switch);
    	rbSwitchFourWay.setText("Switch 4W - " + Count.SwitchFourWay);
    	rbSwitchThreeWay.setText("Switch 3W - " + Count.SwitchThreeWay);
    	rbLight.setText("Light - " + Count.Light);
    	rbLightFan.setText("Light Fan - " + Count.LightFan);
    	rbLightCan.setText("Light Can - " + Count.LightCan);
    }
    
}
