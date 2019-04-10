package application;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class MapController extends Controller implements Initializable{

	@FXML
	private HBox battlearea;
	@FXML
	private ImageView foreground;
	@FXML
	private Button character1,character2,prevButton,nextButton;
	@FXML
	private Label money,name1,name2,LV1,LV2,job1,job2,description,distance;
	@FXML
	private ProgressBar HPBar1,SPBar1,EXPBar1,HPBar2,SPBar2,EXPBar2;

	private boolean bossflag=false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		money.setText("所持金:"+String.valueOf(Game.Money));

		String filepath="map"+String.valueOf(Game.mapID)+".png";
		foreground.setImage(readImage(filepath));

		refleshStatus();

		prevButton.setTooltip(new Tooltip("0まで戻ると家に帰ります"));
		nextButton.setTooltip(new Tooltip("進みます"));

		distance.setTooltip(new Tooltip("最奥にボスがいます"));

		battlearea.getStylesheets().add(readCSS("resources/button.css"));

		setDistance();
	}

	@FXML
	public void character1Clicked(ActionEvent e){

	}

	@FXML
	public void character2Clicked(ActionEvent e){

	}

	@FXML
	public void prevClicked(ActionEvent e){
		Game.Distance--;
		if(Game.Distance<1){
			Main.stage.setTitle("めにゅ～");
			try {
				AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("Menu.fxml"));
				Scene menu=new Scene(pane, 640, 480);
				Main.stage.setScene(menu);
				Main.stage.show();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		else{
			setDistance();
			if(new Random().nextInt(5)==0){
				encount();
			}
		}
	}

	@FXML
	public void nextClicked(ActionEvent e){
		Game.Distance++;
		if(Game.Distance>=Game.maxDistance){
			bossflag=true;
			setDistance();
			encount();
		}
		else{
			setDistance();
			if(new Random().nextInt(5)==0){
				encount();
			}
		}
	}

	private void refleshStatus() {

		//TODO progresssbarをprogressindicatorbarに変更しよう


		name1.setText(String.valueOf(Game.actors.get(0).getName()));
		LV1.setText("LV."+String.valueOf(Game.actors.get(0).getLV()));
		HPBar1.setProgress(Game.actors.get(0).getanystatus(1)/Game.actors.get(0).getanystatus(0));

		HPBar1.setTooltip(new Tooltip(String.valueOf(Game.actors.get(0).getanystatus(1))+"/"+String.valueOf(Game.actors.get(0).getanystatus(0))));
		SPBar1.setProgress(Game.actors.get(0).getanystatus(3)/Game.actors.get(0).getanystatus(2));
		SPBar1.setTooltip(new Tooltip(String.valueOf(Game.actors.get(0).getanystatus(3))+"/"+String.valueOf(Game.actors.get(0).getanystatus(2))));
		EXPBar1.setProgress((double)Game.actors.get(0).getEXP()/Game.actors.get(0).getMaxEXP());
		EXPBar1.setTooltip(new Tooltip(String.valueOf(Game.actors.get(0).getEXP())+"/"+String.valueOf(Game.actors.get(0).getMaxEXP())));
		job1.setText(Game.actors.get(0).getjob().getName());
		job1.setTooltip(new Tooltip(Game.actors.get(0).getjob().getComment()));

		name2.setText(String.valueOf(Game.actors.get(1).getName()));
		LV2.setText("LV."+String.valueOf(Game.actors.get(1).getLV()));
		HPBar2.setProgress(Game.actors.get(1).getanystatus(1)/Game.actors.get(1).getanystatus(0));
		HPBar2.setTooltip(new Tooltip(String.valueOf(Game.actors.get(1).getanystatus(1))+"/"+String.valueOf(Game.actors.get(1).getanystatus(0))));
		SPBar2.setProgress(Game.actors.get(1).getanystatus(3)/Game.actors.get(1).getanystatus(2));
		SPBar2.setTooltip(new Tooltip(String.valueOf(Game.actors.get(1).getanystatus(3))+"/"+String.valueOf(Game.actors.get(1).getanystatus(2))));
		EXPBar2.setProgress((double)Game.actors.get(1).getEXP()/Game.actors.get(1).getMaxEXP());
		EXPBar2.setTooltip(new Tooltip(String.valueOf(Game.actors.get(1).getEXP())+"/"+String.valueOf(Game.actors.get(1).getMaxEXP())));
		job2.setText(Game.actors.get(1).getjob().getName());
		job2.setTooltip(new Tooltip(Game.actors.get(0).getjob().getComment()));
	}

	public void setDistance(){
		distance.setText(String.valueOf(Game.Distance)+"/"+String.valueOf(Game.maxDistance));
	}

	public void encount(){
		/*
		HashMap<Integer,Integer> actionPoints=new HashMap<Integer,Integer>();

		Game.actors.forEach(a->{

		});
		*/

		prevButton.setDisable(true);
		prevButton.setVisible(false);
		nextButton.setDisable(true);
		nextButton.setVisible(false);
		TreeMap<Integer,Battler> troop=new TreeMap<Integer,Battler>();
		if(bossflag==false){
			description.setText("敵が出現!!!");
			int rand=new Random().nextInt(4);
			for(int i=0;i<rand+1;i++){
				Battler clonebattler=Game.enemies.get(0).clone();
				troop.put(i,clonebattler);
			}
		}
		else{
			description.setText("ボスだ!!!!!!!!!!!!");
			Battler clonebattler=Game.enemies.get(1).clone();
			troop.put(0,clonebattler);
		}

		battlearea.setVisible(true);
		for(int key:troop.keySet()){
			Battler enemy=troop.get(key);
			ImageView img=new ImageView(readImage("enemy"+String.valueOf(enemy.getID())+".png"));
			SuperButton btn=new SuperButton();
			btn.setMinWidth(100);
			btn.setMinHeight(200);
			btn.setText(enemy.getName());
			btn.setTextFill(Paint.valueOf("WHITE"));
			btn.setLayoutY(150);
			btn.setContentDisplay(ContentDisplay.TOP);
			btn.setGraphic(img);
			btn.setField(key);
			btn.setOnAction(e->{
				FadeTransition ten=new FadeTransition();
				ten.setNode(btn);
				ten.setDuration(Duration.millis(100));
				ten.setFromValue(1);
				ten.setToValue(0);
				ten.setCycleCount(3);
				ten.setOnFinished(fin->{
					if(troop.get(btn.getField()).getanystatus(1)>0){
						btn.setOpacity(1);
					}
				});
				ten.play();
				int hp=enemy.getanystatus(1);
				int damage=Game.actors.get(0).getanystatus(4);
				enemy.setanystatus(1,hp-damage);
 				description.setText(enemy.getName()+"に"+damage+"のダメージ");
				if(enemy.getanystatus(1)<=0){
					enemy.setanystatus(1, 0);
					int exp=enemy.getEXP();
					description.setText(description.getText()+"\n"+"倒した");
					description.setText(description.getText()+"それぞれ"+exp+"EXP手に入れた");
					Game.actors.forEach(a->a.addEXP(exp));
					//TODO progressbarを継承して最大値と現在値をもつクラスを作ってpropertyに保存したい

					refleshStatus();//レベルや経験値の更新

					FadeTransition tr=new FadeTransition();
 					tr.setNode(btn);
					tr.setDuration(Duration.millis(1000));
					tr.setFromValue(1);
					tr.setToValue(0);
					tr.setCycleCount(1);
					tr.setOnFinished(eh->{
						//https://qiita.com/pepepe/items/337134b4fccbfee83a2d
						int allhp=0;
						for(int key2:troop.keySet()){
							allhp+=troop.get(key2).getanystatus(1);
						}
						if(allhp==0){
							description.setText("勝利ッ!!");
							battlearea.setVisible(false);
							prevButton.setDisable(false);
							prevButton.setVisible(true);
							nextButton.setDisable(false);
							nextButton.setVisible(true);
							battlearea.getChildren().clear();
							if(bossflag==true){
								Main.stage.setTitle("ゲームクリアおめでとう");
								try{
									AnchorPane pane = (AnchorPane)FXMLLoader.load(getClass().getResource("GameClear.fxml"));
									Scene clear=new Scene(pane,640,480);
									Main.stage.setScene(clear);
									Main.stage.show();
								}catch (IOException e2) {
									e2.printStackTrace();
								}
							}
						}
					});
					tr.play();
					btn.setDisable(true);
				}
			});
			battlearea.getChildren().add(btn);
		}
		//System.out.println("number:"+troop.size());
		FadeTransition transition=new FadeTransition();
		transition.setNode(battlearea);
		transition.setDuration(Duration.millis(300));
		transition.setFromValue(0);
		transition.setToValue(1);
		transition.play();
		//setBattle();

	}

	public void setBattle(){

	}

}
