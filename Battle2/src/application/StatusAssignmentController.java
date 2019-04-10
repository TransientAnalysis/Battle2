package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class StatusAssignmentController extends Controller implements Initializable{
	@FXML
	private Label actorName,BP;
	@FXML
	private Label maxHP,maxSP,power,defense,mind,speed;
	@FXML
	private HBox hbox;
	@FXML
	private Button left,right,maxHPminus,maxHPplus,maxSPminus,maxSPplus,powerminus,powerplus,defenseminus,defenseplus,mindminus,mindplus,speedminus,speedplus;

	private int selectedCharacter=0;
	private List<Button> plusbuttons=new ArrayList<Button>();
	private List<Button> minusbuttons=new ArrayList<Button>();
	private List<List<IntegerProperty>> statuses=new ArrayList<List<IntegerProperty>>();
	private List<Label> labels=new ArrayList<Label>();
	int[] statusnum= {0, 2, 4, 5, 6, 7};

	@Override
	public void initialize(URL location, ResourceBundle resources){
		left.setGraphic(new ImageView(readImage("leftarrow.png")));
		right.setGraphic(new ImageView(readImage("rightarrow.png")));
		Image minus=readImage("minus.png");
		Image plus=readImage("plus.png");
		maxHPminus.setGraphic(new ImageView(minus));
		maxHPplus.setGraphic(new ImageView(plus));
		maxSPminus.setGraphic(new ImageView(minus));
		maxSPplus.setGraphic(new ImageView(plus));
		powerminus.setGraphic(new ImageView(minus));
		powerplus.setGraphic(new ImageView(plus));
		defenseminus.setGraphic(new ImageView(minus));
		defenseplus.setGraphic(new ImageView(plus));
		mindminus.setGraphic(new ImageView(minus));
		mindplus.setGraphic(new ImageView(plus));
		speedminus.setGraphic(new ImageView(minus));
		speedplus.setGraphic(new ImageView(plus));

		minusbuttons=Arrays.asList(maxHPminus,maxSPminus,powerminus,defenseminus,mindminus,speedminus);
		plusbuttons=Arrays.asList(maxHPplus, maxSPplus, powerplus, defenseplus, mindplus, speedplus);
		
		for(int i=0; i<Game.actors.size(); i++) {
			statuses.add(new ArrayList<IntegerProperty>());
			for(int j=0; j<statusnum.length; j++) {
				statuses.get(i).add(new SimpleIntegerProperty(Game.actors.get(i).getanystatus(statusnum[j])));
			}
		}
				
		labels=Arrays.asList(maxHP, maxSP, power, defense, mind, speed);
		for(int i=0; i<labels.size(); i++) {//各ラベルのテキストを表示中のステータスの数値にバインド
			labels.get(i).textProperty().bind(statuses.get(selectedCharacter).get(i).asString());
		}
		
		updateStatus();
		
	}
	
	private void updateStatus() {//ラベルの各ステータスを更新
		actorName.setText(String.valueOf(Game.actors.get(selectedCharacter).getName()));
		BP.setText("残り"+Game.actors.get(selectedCharacter).getBP()+"ポイント");

		for(int i=0; i<labels.size(); i++) {//各ラベルのテキストを表示中のステータスの数値にバインド
			labels.get(i).textProperty().bind(statuses.get(selectedCharacter).get(i).asString());
		}

		if(Game.actors.get(selectedCharacter).getBP()>0){
			minusbuttons.forEach(a->a.setDisable(false));
			plusbuttons.forEach(a->a.setDisable(false));
		}
		else {
			plusbuttons.forEach(a->a.setDisable(true));
		}
		
		for(int i=0; i<statusnum.length; i++) {
			if(Integer.parseInt(labels.get(i).getText())==Game.actors.get(selectedCharacter).getanystatus(statusnum[i])){
				minusbuttons.get(i).setDisable(true);
			}
			else {
				minusbuttons.get(i).setDisable(false);
			}
		}	
	}
	
	private void minusAnyStatus(int num) {
		int textedValue=Integer.parseInt(labels.get(num).getText());
		int nowBP=Game.actors.get(selectedCharacter).getBP();
		
		if(num==0) {
			textedValue-=5;
		}
		else if(num==1) {
			textedValue-=3;
		}
		else {
			textedValue--;
		}
		nowBP++;
		statuses.get(selectedCharacter).get(num).set(textedValue);
		Game.actors.get(selectedCharacter).setBP(nowBP);
		updateStatus();
	}
	
	private void plusAnyStatus(int num) {
		int textedValue=Integer.parseInt(labels.get(num).getText());
		int nowBP=Game.actors.get(selectedCharacter).getBP();
		
		if(num==0) {
			textedValue+=5;
		}
		else if(num==1) {
			textedValue+=3;
		}
		else {
			textedValue++;
		}
		nowBP--;
		statuses.get(selectedCharacter).get(num).set(textedValue);
		Game.actors.get(selectedCharacter).setBP(nowBP);
		updateStatus();
	}

	@FXML
	private void onLeftButtonClicked(ActionEvent e) {
		right.setDisable(false);
		selectedCharacter--;
		if(selectedCharacter<1) {
			left.setDisable(true);
		}
		updateStatus();
	}

	@FXML
	private void onRightButtonClicked(ActionEvent e) {
		left.setDisable(false);
		selectedCharacter++;
		if(selectedCharacter==Game.actors.size()-1) {
			right.setDisable(true);
		}
		updateStatus();
	}

	@FXML
	private void onmaxHPminusClicked(ActionEvent e){
		minusAnyStatus(0);
	}
	@FXML
	private void onmaxHPplusClicked(ActionEvent e){
		plusAnyStatus(0);
	}
	@FXML
	private void onmaxSPminusClicked(ActionEvent e){
		minusAnyStatus(1);
	}
	@FXML
	private void onmaxSPplusClicked(ActionEvent e){
		plusAnyStatus(1);
	}
	@FXML
	private void onpowerminusClicked(ActionEvent e){
		minusAnyStatus(2);
	}
	@FXML
	private void onpowerplusClicked(ActionEvent e){
		plusAnyStatus(2);
	}
	@FXML
	private void ondefenseminusClicked(ActionEvent e){
		minusAnyStatus(3);
	}
	@FXML
	private void ondefenseplusClicked(ActionEvent e){
		plusAnyStatus(3);
	}
	@FXML
	private void onmindminusClicked(ActionEvent e){
		minusAnyStatus(4);
	}
	@FXML
	private void onmindplusClicked(ActionEvent e){
		plusAnyStatus(4);
	}
	@FXML
	private void onspeedminusClicked(ActionEvent e){
		minusAnyStatus(5);
	}
	@FXML
	private void onspeedplusClicked(ActionEvent e){
		plusAnyStatus(5);
	}
	@FXML
	private void onnextButton(ActionEvent e){
		boolean nextflag=false;

		Alert alt=new Alert(AlertType.WARNING,"ステータス振り分けを終了します",ButtonType.OK,ButtonType.NO);
		alt.setHeaderText(null);
		alt.setTitle("確認");
		if(alt.showAndWait().get()==ButtonType.OK){
			nextflag=true;
			
			for(int i=0; i<Game.actors.size(); i++) {
				Game.actors.get(i).setanystatus(0, statuses.get(i).get(0).get());
				Game.actors.get(i).setanystatus(1, statuses.get(i).get(0).get());
				Game.actors.get(i).setanystatus(2, statuses.get(i).get(1).get());
				Game.actors.get(i).setanystatus(3, statuses.get(i).get(1).get());
				Game.actors.get(i).setanystatus(4, statuses.get(i).get(2).get());
				Game.actors.get(i).setanystatus(5, statuses.get(i).get(3).get());
				Game.actors.get(i).setanystatus(6, statuses.get(i).get(4).get());
				Game.actors.get(i).setanystatus(7, statuses.get(i).get(5).get());
			}
		}

		if(nextflag==true){
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
	}
}
