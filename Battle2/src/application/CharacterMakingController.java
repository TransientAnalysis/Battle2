package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.*;

public class CharacterMakingController implements Initializable{

	private boolean nextflag=false;

	@FXML
	private Label sub;
	@FXML
	private TextField name1,name2;
	@FXML
	private ComboBox<String> Character1,Character2;
	@FXML
	private ImageView bodyImage1,bodyImage2;
	@FXML
	private ToggleGroup group1,group2;
	@FXML
	private RadioButton job1_1,job1_2,job1_3,job2_1,job2_2,job2_3;
	@FXML
	private Button nextButton;

	@Override
	public void initialize(URL location, ResourceBundle resources){
		//キャラクター生成
		Game.actors.add(new Battler("名無男",Game.jobs.get(0),100,5,5,5,5,3));
		Game.actors.add(new Battler("名無子",Game.jobs.get(0),100,5,5,5,5,3));

		//名前入力部生成
		name1.setText(Game.actors.get(0).getName());
		name2.setText(Game.actors.get(1).getName());
		name1.setTooltip(new Tooltip("主人公の名前です"));
		name2.setTooltip(new Tooltip("相棒の名前です"));
		name1.textProperty().addListener((r,oldValue,newValue)->{
			//System.out.println(newValue);
			Game.actors.get(0).setName(newValue);
		});
		name2.textProperty().addListener((r,oldValue,newValue)->{
			//System.out.println(newValue);
			Game.actors.get(1).setName(newValue);
		});
		//Game.actors.get(0).setName("名無男");
		//Game.actors.get(1).setName("名無子");

		Character1.getItems().add("男性1");//項目の追加
		Character1.getItems().add("女性1");
		Character2.getItems().add("男性2");
		Character2.getItems().add("女性2");

		Character1.setTooltip(new Tooltip("主人公の性別です"));
		Character2.setTooltip(new Tooltip("相棒の性別です"));

		Character1.getSelectionModel().selectedIndexProperty().addListener((r,oldValue,newValue)->{
			//System.out.println(newValue);
			switch(newValue.intValue()){
				case 0:
					bodyImage1.setImage(new Image("file:male1.png"));
					Game.actors.get(0).setgender(0);
					break;
				case 1:
					bodyImage1.setImage(new Image("file:female1.png"));
					Game.actors.get(0).setgender(1);
					break;
			}
		});
		Character2.getSelectionModel().selectedIndexProperty().addListener((r,oldValue,newValue)->{
			//System.out.println(newValue);
			switch(newValue.intValue()){
				case 0:
					bodyImage2.setImage(new Image("file:male2.png"));
					Game.actors.get(1).setgender(0);
					break;
				case 1:
					bodyImage2.setImage(new Image("file:female2.png"));
					Game.actors.get(1).setgender(1);
					break;
			}
		});


		job1_1.setTooltip(new Tooltip(Game.jobs.get(1).getComment()));//剣士
		job1_2.setTooltip(new Tooltip(Game.jobs.get(4).getComment()));//魔術士
		job1_3.setTooltip(new Tooltip(Game.jobs.get(7).getComment()));//クソガキ
		job2_1.setTooltip(new Tooltip(Game.jobs.get(12).getComment()));//盗賊
		job2_2.setTooltip(new Tooltip(Game.jobs.get(17).getComment()));//武術家
		job2_3.setTooltip(new Tooltip(Game.jobs.get(21).getComment()));//ガンナー
		System.out.println("ツールチップセット完了");

		group1.selectedToggleProperty().addListener((r,oldValue,newValue)->{
			if(job1_1.isSelected()==true){
				Game.actors.get(0).setjob(Game.jobs.get(1));
			}
			else if(job1_2.isSelected()==true){
				Game.actors.get(0).setjob(Game.jobs.get(4));
			}
			else if(job1_3.isSelected()==true){
				Game.actors.get(0).setjob(Game.jobs.get(7));
			}
			//System.out.println(Game.actors.get(0).getjob().getName());
		});
		group2.selectedToggleProperty().addListener((r,oldValue,newValue)->{
			if(job2_1.isSelected()==true){
				Game.actors.get(1).setjob(Game.jobs.get(12));
			}
			else if(job2_2.isSelected()==true){
				Game.actors.get(1).setjob(Game.jobs.get(17));
			}
			else if(job2_3.isSelected()==true){
				Game.actors.get(1).setjob(Game.jobs.get(21));
			}
			//System.out.println(Game.actors.get(1).getjob().getName());
		});
		//Game.actors.get(0).setjob(Game.jobs.get(0));
		//Game.actors.get(1).setjob(Game.jobs.get(0));
		//System.out.println(Game.actors.get(0).getjob().getName());
		/*
		if(Game.actors.get(0).getjob().getID()!=999&&Game.actors.get(1).getjob().getID()!=999){
			System.out.println("1:"+Game.actors.get(0).getjob().getName()+"\n2:"+Game.actors.get(1).getjob().getName());
		}
		*/
		System.out.println("アクションリスナーセット完了");


		Image img=null;
		//imageを張り替えるときは、『.project』と同じディレクトリを参照
		img=new Image("file:select.png");//http://totomo.net/11260-javafxcombochoice.htm

		bodyImage1.setImage(img);
		bodyImage2.setImage(img);

		nextButton.setTooltip(new Tooltip("次に進みます"));
	}


	@FXML
	private void onnextButton(ActionEvent e){
		if(Game.actors.get(0).getName().length()==0||Game.actors.get(1).getName().length()==0){
			nextflag=false;
			Alert alt=new Alert(AlertType.ERROR,"消したら入力して",ButtonType.OK);
			alt.setHeaderText(null);
			alt.setTitle("名前が空です");
			alt.showAndWait();
		}
		else if(Game.actors.get(0).getName().equals(Game.actors.get(1).getName())){
			nextflag=false;
			Alert alt=new Alert(AlertType.ERROR,"同じ名前だとややこしくなるのでやめてください");
			alt.setHeaderText(null);
			alt.setTitle("名前が被っています");
			alt.showAndWait();
		}
		else if(Game.actors.get(0).getName().length()>6||Game.actors.get(1).getName().length()>6){
			nextflag=false;
			Alert alt=new Alert(AlertType.ERROR,"もう少し短い名前はないんですか?",ButtonType.OK);
			alt.setHeaderText(null);
			alt.setTitle("名前が長すぎます");
			alt.showAndWait();
		}
		else if(Game.actors.get(0).getName().equals("名無男")||Game.actors.get(1).getName().equals("名無子")){
			nextflag=false;
			Alert alt=new Alert(AlertType.ERROR,"デフォルトとはいえ、\n流石にその名前は可哀想です",ButtonType.OK);
			alt.setHeaderText(null);
			alt.setTitle("名前を変えてください");
			alt.showAndWait();
		}
		else if(Game.actors.get(0).getgender()==999){
			nextflag=false;
			Alert alt=new Alert(AlertType.WARNING,"悩まなくていいので早く決めてください",ButtonType.OK);
			alt.setHeaderText(null);
			alt.setTitle("性別が未選択です");
			alt.showAndWait();
		}
		else{
			Alert alt=new Alert(AlertType.WARNING,"以上の内容で決定して良いですか?",ButtonType.OK,ButtonType.NO);
			alt.setHeaderText(null);
			alt.setTitle("最終確認");
			if(alt.showAndWait().get()==ButtonType.OK){
				nextflag=true;
			}
		}
		if(nextflag==true){
			Main.stage.setTitle("のうりょくちふりわけ");
			try {
				Main.root = (AnchorPane)FXMLLoader.load(getClass().getResource("StatusAssignment.fxml"));
				Scene assign=new Scene(Main.root,640,480);
				assign.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
				Main.stage.setScene(assign);
				Main.stage.show();
				WritableImage snapshot = assign.snapshot(null);
				ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("./assignment.png"));
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}

}
