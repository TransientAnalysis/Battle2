package application;

import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuController implements Initializable{

	@FXML
	private ImageView background,rightarrow;
	@FXML
	private Button bouken,mochimono,gousei,tensyoku,save,go,character1,character2;
	@FXML
	private VBox menubox;
	@FXML
	private HBox secondbox;
	@FXML
	private Label money,name1,name2,LV1,LV2,job1,job2,minititle,description;
	@FXML
	private ProgressBar HPBar1,SPBar1,EXPBar1,HPBar2,SPBar2,EXPBar2;
	@FXML
	private ListView<String> listview;

	/*
	private List<Button> menus=Arrays.asList(bouken,mochimono,gousei,tensyoku,save);
	private List<Label> names=Arrays.asList(name1,name2);
	private List<Label> LVs=Arrays.asList(LV1,LV2);
	private List<ProgressBar> HPBars =Arrays.asList(HPBar1,HPBar2);
	private List<ProgressBar> SPBars=Arrays.asList(SPBar1,SPBar2);
	private List<ProgressBar> EXPBars=Arrays.asList(EXPBar1,EXPBar2);
	private List<Label> jobs=Arrays.asList(job1,job2);
	*/
	private boolean[] menuflags=new boolean[5];

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		money.setText("所持金:"+String.valueOf(Game.Money));

		name1.setText(String.valueOf(Game.actors.get(0).getName()));
		LV1.setText("LV."+String.valueOf(Game.actors.get(0).getLV()));
		HPBar1.setProgress(Game.actors.get(0).getanystatus(1)/Game.actors.get(0).getanystatus(0));
		HPBar1.setTooltip(new Tooltip(String.valueOf(Game.actors.get(0).getanystatus(1))+"/"+String.valueOf(Game.actors.get(0).getanystatus(0))));
		SPBar1.setProgress(Game.actors.get(0).getanystatus(3)/Game.actors.get(0).getanystatus(2));
		SPBar1.setTooltip(new Tooltip(String.valueOf(Game.actors.get(0).getanystatus(3))+"/"+String.valueOf(Game.actors.get(0).getanystatus(2))));
		EXPBar1.setProgress(Game.actors.get(0).getEXP()/Game.actors.get(0).getMaxEXP());
		EXPBar1.setTooltip(new Tooltip(String.valueOf(Game.actors.get(0).getEXP())+"/"+String.valueOf(Game.actors.get(0).getMaxEXP())));
		job1.setText(Game.actors.get(0).getjob().getName());
		job1.setTooltip(new Tooltip(Game.actors.get(0).getjob().getComment()));

		name2.setText(String.valueOf(Game.actors.get(1).getName()));
		LV2.setText("LV."+String.valueOf(Game.actors.get(1).getLV()));
		HPBar2.setProgress(Game.actors.get(1).getanystatus(1)/Game.actors.get(1).getanystatus(0));
		HPBar2.setTooltip(new Tooltip(String.valueOf(Game.actors.get(1).getanystatus(1))+"/"+String.valueOf(Game.actors.get(1).getanystatus(0))));
		SPBar2.setProgress(Game.actors.get(1).getanystatus(3)/Game.actors.get(1).getanystatus(2));
		SPBar2.setTooltip(new Tooltip(String.valueOf(Game.actors.get(1).getanystatus(3))+"/"+String.valueOf(Game.actors.get(1).getanystatus(2))));
		EXPBar2.setProgress(Game.actors.get(1).getEXP()/Game.actors.get(1).getMaxEXP());
		EXPBar2.setTooltip(new Tooltip(String.valueOf(Game.actors.get(1).getEXP())+"/"+String.valueOf(Game.actors.get(1).getMaxEXP())));
		job2.setText(Game.actors.get(1).getjob().getName());
		job2.setTooltip(new Tooltip(Game.actors.get(0).getjob().getComment()));

		flagsReset();

	}

	@FXML
	private void adventureClicked(ActionEvent e){
		if(menuflags[0]==false){
			menuflags[0]=true;
			listview.setItems(FXCollections.observableArrayList());
			Game.maps.forEach(a->{
				listview.getItems().add(a.getName());
			});
			listview.getSelectionModel().selectedItemProperty().addListener((o,oldValue,newValue)->{
				if(newValue!=null){
					minititle.setText(newValue);
					Game.mapID=Game.mapsID.get(newValue).intValue();
					description.setText(Game.maps.get(Game.mapID).getComment());
					go.setDisable(false);
					go.setVisible(true);
				}
			});;

			rightarrow.setLayoutY(menubox.getLayoutY()+bouken.getLayoutY());
			rightarrow.setVisible(true);
			secondbox.setDisable(false);
			secondbox.setVisible(true);
		}
		else{
			flagsReset();
			boxReset();
		}
	}

	@FXML
	private void goClicked(ActionEvent e){
		if(Game.mapID==0){

		}
		else{

			Main.stage.setTitle("まっぷ");
			try {
				Game.maxDistance=Game.maps.get(Game.mapID).getFar();
				Game.Distance=1;

				Main.prevScene=Main.stage.getScene();
				Main.root = (AnchorPane)FXMLLoader.load(getClass().getResource("Map.fxml"));
				Scene map=new Scene(Main.root,640,480);
				map.getStylesheets().add(getClass().getResource("./application.css").toExternalForm());
				Main.stage.setScene(map);
				Main.stage.show();
				WritableImage snapshot = map.snapshot(null);
				ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("./map.png"));
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}

	}

	private void flagsReset(){
		for(int i=0;i<menuflags.length;i++){
			menuflags[i]=false;
		}
	}

	private void boxReset(){
		rightarrow.setVisible(false);
		go.setDisable(true);
		go.setVisible(false);
		secondbox.setDisable(true);
		secondbox.setVisible(false);
		minititle.setText("ミニタイトル");
		description.setText("説明");
	}
}
