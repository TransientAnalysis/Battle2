package application;

import java.io.InputStream;
public class ResourceLoader {

	private static ResourceLoader singleton;

	public ResourceLoader() {


	}

	public static ResourceLoader getInstance(){

		if (singleton == null){
			singleton = new ResourceLoader();
		}

		return singleton;
	}


	private static final String DIR_ICON = "icon/";
	/**
	 * アイコンファイルのリソース用InputStreamを呼び出す。
	 */
	public InputStream getResourceStreamIcon(final String iconfilename){

		return singleton.getClass().getResourceAsStream(  DIR_ICON + iconfilename);

	}

	private static final String DIR_SOUND = "sound/";
	/**
	 * 音声ファイルのリソース用InputStreamを呼び出す。
	 */
	public InputStream getResourceStreamSound(final String soundfilename){

		return singleton.getClass().getResourceAsStream(  DIR_SOUND + soundfilename);

	}


}