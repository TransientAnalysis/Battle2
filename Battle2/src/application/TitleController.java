package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class TitleController extends Controller implements Initializable{

	//private int imageNum=0;

	@FXML
	private AnchorPane pane;
	@FXML
	private ImageView title;
	@FXML
	private Button startButton;
	@FXML
	private Button continueButon;
	@FXML
	private ImageView startImage;
	@FXML
	private ImageView continueImage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//maxHP,maxSP,power,defense,mind,speed
		Game.jobs.put(0,new Job("無職", 0, "ニート", 1, 1, 1, 1, 1, 1));
		Game.jobs.put(1,new Job("剣士", 1, "剣技を習得する基本ジョブ", 1.05, 1.05, 1.05, 1.05, 0.9, 0.95));
		Game.jobs.put(4,new Job("魔術師", 4, "魔術を扱う基本ジョブ", 0.95, 1.05, 0.80, 0.85, 1.1, 1));
		Game.jobs.put(7,new Job("クソガキ", 7, "ナメた態度の基本ジョブ", 1.1, 1.1, 1, 1, 0.5, 1.2));
		Game.jobs.put(12,new Job("盗賊", 12, "『盗む』を習得する中級合成ジョブ", 0.9, 1.2, 1.1, 0.95, 1, 1.3));
		Game.jobs.put(17,new Job("武術家", 17, "肉体派の中級合成ジョブ", 1.3, 1.2, 1.2, 1.2, 1.1, 1));
		Game.jobs.put(21,new Job("ガンナー", 21, "銃の扱いに長けた中級合成ジョブ", 0.8, 1.2, 1, 0.8, 1, 1.1));
		Game.jobs.put(101,new Job("雑草", 101, "そこら辺に生えている", 0.5, 0.5, 0.5, 0.5, 0.5, 2));
		Game.jobs.put(102,new Job("犬", 102, "吠えるよ", 1.2, 1, 1.2, 1.1, 0.5, 1.3));
		System.out.println("職業セット完了");

		Game.maps.add(new MapArea("店", 0, 0, true, "敵が出現しないエリアです\nアイテムを購入できます"));
		Game.maps.add(new MapArea("庭", 1, 20, false, "引きこもっていないで外に出ましょう"));

		Game.enemies.add(new Battler("雑草", 0, Game.jobs.get(101), 30, 0, 10, 3, 5, 5, 3));
		Game.enemies.add(new Battler("木", 1, Game.jobs.get(102), 100, 10, 10, 5, 4, 10, 40));

		Game.maps.forEach(a->{
			Game.mapsID.put(a.getName(), a.getID());
		});
		title.setImage(readImage("title.png"));
		startImage.setImage(readImage("start.png"));
		continueImage.setImage(readImage("continue.png"));
	}


	@FXML
	private void startClicked(ActionEvent e){
		Main.stage.setTitle("きゃらくたーめいきんぐ");
		try {
			Main.prevScene=Main.stage.getScene();
			AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("CharacterMaking.fxml"));
			Scene making=new Scene(pane, 640, 480);
			Main.stage.setScene(making);
			Main.stage.show();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	@FXML
	private void continueClicked(ActionEvent e){
		//記録ファイルの読み込み
		String path_file="save.dat";
		List<String> list=new ArrayList<String>();
		InputStream is=getClass().getResourceAsStream(path_file);
		if(is!=null) {
			try {
				//相対パスをInputStreamに変換
				InputStreamReader isr=new InputStreamReader(is, "UTF-8");
				BufferedReader br=new BufferedReader(isr);
				//ファイル読み込み
				String str;
				while((str=br.readLine())!=null) {
					list.add(str);
				}
				br.close();
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}


}
