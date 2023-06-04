import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SetupScreen extends Component{//設定画面クラス
	BufferedImage backscreen = null;//タイトル背景の読み込み
	String imageFilename = null;//タイトル背景のstring
	
	public SetupScreen() {//設定画面
		imageFilename = "Ruleset.png";//タイトル画面
		try {
			backscreen = ImageIO.read(new File(imageFilename));//画像の読み込み
		} catch (IOException e) {
			System.out.println("image file not found. ");//失敗処理
		}
	}
	
	public void paint(Graphics graphics) {//画面を描く
		graphics.drawImage(backscreen, 0, 0, null);
	}
}
