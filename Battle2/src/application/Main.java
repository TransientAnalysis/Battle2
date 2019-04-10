package application;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	public static Stage stage;
	private static AnchorPane root;
	public static Scene prevScene;
	private static int px=640, py=480;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		try {
			stage=primaryStage;
			stage.sizeToScene();//謎の余白を消す
			stage.setResizable(false);//ウィンドウの拡大・縮小を許可
			Image img=readImage("icon.png");
			stage.getIcons().add(img);
			stage.setOnCloseRequest(we->{
				abortClosing(we);
			});
			sendTitleController("たいとる");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	//jarから画像を読み込むクラス
	private Image readImage(String path) {
		Image img=null;
		img = new Image(getClass().getResourceAsStream("resources/"+path));
		return img;
	}

	private void abortClosing(WindowEvent we) {
		//ウィンドウを閉じる際にメッセージ表示
		Alert al=new Alert(AlertType.CONFIRMATION);
		al.setHeaderText(null);
		al.setContentText("おわるよ");
		al.setTitle("めっせーじ");
		Optional<ButtonType> result=al.showAndWait();
		if(result.get()==ButtonType.OK) {
			System.out.print("終わったよ");
		}
		else {
			we.consume();
		}
	}

	public void sendTitleController(String labelText){
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("Title.fxml"));
			root.setStyle("-fx-font-family:'Meiryo'");
			Scene top=new Scene(root, px, py);
			top.getStylesheets().add(getClass().getResource("resources/application.css").toExternalForm());
			stage.setTitle(labelText);
			stage.setScene(top);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMenuController(String labelText){
		try{
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene menu=new Scene(root,640,480);
			menu.getStylesheets().add(getClass().getResource("resources/application.css").toExternalForm());
			stage.setTitle(labelText);
			stage.setScene(menu);
			stage.show();
		} catch(IOException e){
			e.printStackTrace();
		}
	}


}
