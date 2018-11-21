package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;

public class StatusAssignmentController implements Initializable{
	@FXML
	private Label actorName,BP;
	@FXML
	private Label maxHP,maxSP,power,defense,mind,speed;
	@FXML
	private Button maxHPminus,maxHPplus,maxSPminus,maxSPplus,powerminus,powerplus,defenseminus,defenseplus,mindminus,mindplus,speedminus,speedplus;


	//private List<Button> minusbuttons=new ArrayList<Button>();
	private List<Button> plusbuttons=new ArrayList<Button>();

	@Override
	public void initialize(URL location, ResourceBundle resources){

		//System.out.println(actorName.getText());
		actorName.setText(String.valueOf(Game.actors.get(0).getName()));
		BP.setText("残り"+Game.actors.get(0).getBP()+"ポイント");
		maxHP.setText(String.valueOf(Game.actors.get(0).getanystatus(0)));
		maxSP.setText(String.valueOf(Game.actors.get(0).getanystatus(2)));
		power.setText(String.valueOf(Game.actors.get(0).getanystatus(4)));
		defense.setText(String.valueOf(Game.actors.get(0).getanystatus(5)));
		mind.setText(String.valueOf(Game.actors.get(0).getanystatus(6)));
		speed.setText(String.valueOf(Game.actors.get(0).getanystatus(7)));

		//minusbuttons=Arrays.asList(maxHPminus,maxSPminus,powerminus,defenseminus,mindminus,speedminus);
		plusbuttons=Arrays.asList(maxHPplus,maxSPplus,powerplus,defenseplus,mindplus,speedplus);

		if(Game.actors.get(0).getBP()>0){
			plusbuttons.forEach(a->a.setDisable(false));
		}


	}

	@FXML
	private void onmaxHPminusClicked(ActionEvent e){
		int textedmaxHP=Integer.parseInt(maxHP.getText());
		int nowBP=Game.actors.get(0).getBP();
		if(textedmaxHP>Game.actors.get(0).getanystatus(0)){
			textedmaxHP--;
			nowBP++;
			maxHP.setText(String.valueOf(textedmaxHP));
			BP.setText("残り"+nowBP+"ポイント");
			Game.actors.get(0).setBP(nowBP);
			plusbuttons.forEach(a->a.setDisable(false));
			//System.out.println(Game.actors.get(0).getanystatus(0)+"と"+textedmaxHP);
			if(textedmaxHP==Game.actors.get(0).getanystatus(0)){
				//System.out.println("awfhoafoahfaoiuf");
				maxHPminus.setDisable(true);
			}
		}
	}
	@FXML
	private void onmaxHPplusClicked(ActionEvent e){
		int textedmaxHP=Integer.parseInt(maxHP.getText());
		int nowBP=Game.actors.get(0).getBP();
		if(nowBP>0){
			textedmaxHP++;
			nowBP--;
			maxHP.setText(String.valueOf(textedmaxHP));
			BP.setText("残り"+nowBP+"ポイント");
			Game.actors.get(0).setBP(nowBP);
			maxHPminus.setDisable(false);
			if(nowBP==0){
				plusbuttons.forEach(a->a.setDisable(true));
			}
			else{
				maxHPminus.setDisable(false);
			}
		}
	}
	@FXML
	private void onmaxSPminusClicked(ActionEvent e){
		int textedmaxSP=Integer.parseInt(maxSP.getText());
		int nowBP=Game.actors.get(0).getBP();
		if(textedmaxSP>Game.actors.get(0).getanystatus(2)){
			textedmaxSP--;
			nowBP++;
			maxSP.setText(String.valueOf(textedmaxSP));
			BP.setText("残り:"+nowBP+"ポイント");
			Game.actors.get(0).setBP(nowBP);
			plusbuttons.forEach(a->a.setDisable(false));
			if(textedmaxSP==Game.actors.get(0).getanystatus(2)){
				maxSPminus.setDisable(true);
			}
		}
	}
	@FXML
	private void onmaxSPplusClicked(ActionEvent e){
		int textedmaxSP=Integer.parseInt(maxSP.getText());
		int nowBP=Game.actors.get(0).getBP();
		if(nowBP>0){
			textedmaxSP++;
			nowBP--;
			maxSP.setText(String.valueOf(textedmaxSP));
			BP.setText("残り"+nowBP+"ポイント");
			Game.actors.get(0).setBP(nowBP);
			maxSPminus.setDisable(false);
			if(nowBP==0){
				plusbuttons.forEach(a->a.setDisable(true));
			}
			else{
				maxSPminus.setDisable(false);
			}
		}
	}
	@FXML
	private void onpowerminusClicked(ActionEvent e){
		int textedpower=Integer.parseInt(power.getText());
		int nowBP=Game.actors.get(0).getBP();
		if(textedpower>Game.actors.get(0).getanystatus(4)){
			textedpower--;
			nowBP++;
			power.setText(String.valueOf(textedpower));
			BP.setText("残り"+nowBP+"ポイント");
			Game.actors.get(0).setBP(nowBP);
			plusbuttons.forEach(a->a.setDisable(false));
			if(textedpower==Game.actors.get(0).getanystatus(4)){
				powerminus.setDisable(true);
			}
		}
	}
	@FXML
	private void onpowerplusClicked(ActionEvent e){
		int textedpower=Integer.parseInt(power.getText());
		int nowBP=Game.actors.get(0).getBP();
		if(nowBP>0){
			textedpower++;
			nowBP--;
			power.setText(String.valueOf(textedpower));
			BP.setText("残り"+nowBP+"ポイント");
			Game.actors.get(0).setBP(nowBP);
			powerminus.setDisable(false);
			if(nowBP==0){
				plusbuttons.forEach(a->a.setDisable(true));
			}
			else{
				powerminus.setDisable(false);
			}
		}
	}
	@FXML
	private void ondefenseminusClicked(ActionEvent e){
		int texteddefense=Integer.parseInt(defense.getText());
		int nowBP=Game.actors.get(0).getBP();
		if(texteddefense>Game.actors.get(0).getanystatus(5)){
			texteddefense--;
			nowBP++;
			defense.setText(String.valueOf(texteddefense));
			BP.setText("残り"+nowBP+"ポイント");
			Game.actors.get(0).setBP(nowBP);
			plusbuttons.forEach(a->a.setDisable(false));
			if(texteddefense==Game.actors.get(0).getanystatus(5)){
				defenseminus.setDisable(true);
			}
		}
	}
	@FXML
	private void ondefenseplusClicked(ActionEvent e){
		int texteddefense=Integer.parseInt(defense.getText());
		int nowBP=Game.actors.get(0).getBP();
		if(nowBP>0){
			texteddefense++;
			nowBP--;
			defense.setText(String.valueOf(texteddefense));
			BP.setText("残り"+nowBP+"ポイント");
			Game.actors.get(0).setBP(nowBP);
			defenseminus.setDisable(false);
			if(nowBP==0){
				plusbuttons.forEach(a->a.setDisable(true));
			}
			else{
				defenseminus.setDisable(false);
			}
		}
	}
	@FXML
	private void onmindminusClicked(ActionEvent e){
		int textedmind=Integer.parseInt(mind.getText());
		int nowBP=Game.actors.get(0).getBP();
		if(textedmind>Game.actors.get(0).getanystatus(6)){
			textedmind--;
			nowBP++;
			mind.setText(String.valueOf(textedmind));
			BP.setText("残り"+nowBP+"ポイント");
			Game.actors.get(0).setBP(nowBP);
			plusbuttons.forEach(a->a.setDisable(false));
			if(textedmind==Game.actors.get(0).getanystatus(6)){
				mindminus.setDisable(true);
			}
		}
	}
	@FXML
	private void onmindplusClicked(ActionEvent e){
		int textedmind=Integer.parseInt(mind.getText());
		int nowBP=Game.actors.get(0).getBP();
		if(nowBP>0){
			textedmind++;
			nowBP--;
			mind.setText(String.valueOf(textedmind));
			BP.setText("残り"+nowBP+"ポイント");
			Game.actors.get(0).setBP(nowBP);
			mindminus.setDisable(false);
			if(nowBP==0){
				plusbuttons.forEach(a->a.setDisable(true));
			}
			else{
				mindminus.setDisable(false);
			}
		}
	}
	@FXML
	private void onspeedminusClicked(ActionEvent e){
		int textedspeed=Integer.parseInt(speed.getText());
		int nowBP=Game.actors.get(0).getBP();
		if(textedspeed>Game.actors.get(0).getanystatus(7)){
			textedspeed--;
			nowBP++;
			speed.setText(String.valueOf(textedspeed));
			BP.setText("残り"+nowBP+"ポイント");
			Game.actors.get(0).setBP(nowBP);
			plusbuttons.forEach(a->a.setDisable(false));
			if(textedspeed==Game.actors.get(0).getanystatus(7)){
				speedminus.setDisable(true);
			}
		}
	}
	@FXML
	private void onspeedplusClicked(ActionEvent e){
		int textedspeed=Integer.parseInt(speed.getText());
		int nowBP=Game.actors.get(0).getBP();
		if(nowBP>0){
			textedspeed++;
			nowBP--;
			speed.setText(String.valueOf(textedspeed));
			BP.setText("残り"+nowBP+"ポイント");
			Game.actors.get(0).setBP(nowBP);
			speedminus.setDisable(false);
			if(nowBP==0){
				plusbuttons.forEach(a->a.setDisable(true));
			}
			else{
				speedplus.setDisable(false);
			}
		}
	}
	@FXML
	private void onnextButton(ActionEvent e){
		boolean nextflag=false;

		Alert alt=new Alert(AlertType.WARNING,"ステータス振り分けを終了します",ButtonType.OK,ButtonType.NO);
		alt.setHeaderText(null);
		alt.setTitle("確認");
		if(alt.showAndWait().get()==ButtonType.OK){
			nextflag=true;

			Game.actors.get(0).setanystatus(0,Integer.parseInt(maxHP.getText()));
			Game.actors.get(0).setanystatus(1,Integer.parseInt(maxHP.getText()));
			Game.actors.get(0).setanystatus(2,Integer.parseInt(maxSP.getText()));
			Game.actors.get(0).setanystatus(3,Integer.parseInt(maxSP.getText()));
			Game.actors.get(0).setanystatus(4,Integer.parseInt(power.getText()));
			Game.actors.get(0).setanystatus(5,Integer.parseInt(defense.getText()));
			Game.actors.get(0).setanystatus(6,Integer.parseInt(mind.getText()));
			Game.actors.get(0).setanystatus(7,Integer.parseInt(speed.getText()));
		}

		if(nextflag==true){
			Main.stage.setTitle("めにゅ～");
			try {
				Main.root = (AnchorPane)FXMLLoader.load(getClass().getResource("Menu.fxml"));
				Scene menu=new Scene(Main.root,640,480);
				menu.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
				Main.stage.setScene(menu);
				Main.stage.show();
				WritableImage snapshot = menu.snapshot(null);
				ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("./menu.png"));
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}
