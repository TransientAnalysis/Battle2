package application;

import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;

public class TitleController implements Initializable{
	@FXML
	private Button startButton,continueButton;

	@FXML
	private ImageView background,startImage,continueImage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//maxHP,maxSP,power,defense,mind,speed
		Game.jobs.put(0,new Job("無職",0,"ニートです",1,1,1,1,1,1));
		Game.jobs.put(1,new Job("剣士",1,"剣技を習得する基本ジョブです", 1.05, 1.05, 1.05, 1.05, 0.9, 0.95));
		Game.jobs.put(4,new Job("魔術師",4,"魔法っぽいけど魔法じゃない技を使います", 0.95, 1.05, 0.80, 0.85, 1.1, 1));
		Game.jobs.put(7,new Job("クソガキ",7,"クソです", 1.1, 1.1, 1, 1, 0.5, 1.2));
		Game.jobs.put(12,new Job("盗賊",12,"『盗む』を習得する中級合成ジョブです", 0.9, 1.2, 1.1, 0.95, 1, 1.3));
		Game.jobs.put(17,new Job("武術家",17,"肉体派です", 1.3, 1.2, 1.2, 1.2, 1.1, 1));
		Game.jobs.put(21,new Job("ガンナー",21,"世界観をぶち壊していきます", 0.8, 1.2, 1, 0.8, 1, 1.1));
		Game.jobs.put(101,new Job("雑草",101,"そこら辺に生えています", 0.5, 0.5, 0.5, 0.5, 0.5, 2));
		Game.jobs.put(102,new Job("犬",102,"犬です", 1.2, 1, 1.2, 1.1, 0.5, 1.3));
		System.out.println("職業セット完了");

		Game.maps.add(new MapArea("店",0,0,true,"敵が出現しないエリアです\nアイテムを購入できます"));
		Game.maps.add(new MapArea("庭",1,20,false,"引きこもっていないで外に出ましょう"));

		Game.enemies.add(new Battler("雑草",0,Game.jobs.get(101),30,0,10,3,5,5));
		Game.enemies.add(new Battler("木",1,Game.jobs.get(102),100,10,10,5,4,10));

		Game.maps.forEach(a->{
			Game.mapsID.put(a.getName(),a.getID());
		});
	}


	@FXML
	private void startClicked(ActionEvent e){
		Main.stage.setTitle("きゃらくたーめいきんぐ");
		try {
			Main.prevScene=Main.stage.getScene();
			Main.root = (AnchorPane)FXMLLoader.load(getClass().getResource("CharacterMaking.fxml"));
			Scene making=new Scene(Main.root,640,480);
			making.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Main.stage.setScene(making);
			Main.stage.show();
			WritableImage snapshot = making.snapshot(null);
			ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("making.png"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	@FXML
	private void continueClicked(ActionEvent e){
		Alert alt=new Alert(AlertType.ERROR,"まだ作っていませんごめんなさい",ButtonType.NO);
		alt.setHeaderText(null);
		alt.setTitle("未実装です");
		alt.showAndWait();
	}


}
