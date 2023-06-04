import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BreakScreen extends Component{//中断画面クラス
	BufferedImage breakscreen = null;//画像の読み込み
	String imageFilename = null;//画像のstring
	
	public BreakScreen() {//ブレイクスクリーン
		imageFilename = "BreakScreen.png";//中断画面
		try {
			breakscreen = ImageIO.read(new File(imageFilename));//画像の読み込み
		} catch (IOException e) {
			System.out.println("image file not found. ");//失敗処理
		}
	}
	
	public void paint(Graphics graphics) {//画面を描く
		graphics.drawImage(breakscreen, 0, 0, null);
	}
}
