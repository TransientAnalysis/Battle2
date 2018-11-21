package application;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	private static Main instance;
	public static Stage stage;
	public static AnchorPane root;
	public static Scene prevScene;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			instance=this;

			stage=primaryStage;
			setUserAgentStylesheet(STYLESHEET_CASPIAN);
			stage.sizeToScene();//謎の余白を消す
			stage.setResizable(false);

			sendTitleController("たいとる");

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void sendTitleController(String labelText){
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("Title.fxml"));
			Scene top=new Scene(root,640,480);
			top.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle(labelText);
			stage.setScene(top);
			stage.show();
			WritableImage snapshot = top.snapshot(null);
	  		ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("top.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMenuController(String labelText){
		try{
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene menu=new Scene(root,640,480);
			menu.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle(labelText);
			stage.setScene(menu);
			stage.show();
			WritableImage snapshot = menu.snapshot(null);
	  		ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("menu.png"));
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	public static Main getInstance(){
		return instance;
	}

}
