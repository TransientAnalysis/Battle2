package application;

import javafx.scene.image.Image;

public class Controller {

	//jarから画像を読み込むクラス
	protected Image readImage(String path) {
		Image img=null;
		img = new Image(this.getClass().getResourceAsStream("resources/"+path));
		return img;
	}

	protected String readCSS(String path) {
		return this.getClass().getResource(path).toExternalForm();
	}
}
