import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExplanationScreen extends Component{//ゲーム説明画面クラス
	Image[] explanation;//説明の画像
	int explanationflag = 0;
	int i = 0;
	public ExplanationScreen() {//ゲーム画面を定義
		explanation = new Image[6];
		Toolkit explanationlist = Toolkit.getDefaultToolkit();
		explanation[0] = explanationlist.getImage(getClass().getResource("GameDescription1.png"));
		explanation[1] = explanationlist.getImage(getClass().getResource("GameDescription2.png"));
		explanation[2] = explanationlist.getImage(getClass().getResource("GameDescription3.png"));
		explanation[3] = explanationlist.getImage(getClass().getResource("GameDescription4.png"));
		explanation[4] = explanationlist.getImage(getClass().getResource("GameDescription5.png"));
		explanation[5] = explanationlist.getImage(getClass().getResource("GameDescription6.png"));
		MediaTracker tracker = new MediaTracker(this);//カード版
		for (i= 0; i < 6; i++ ){
			tracker.addImage(explanation[i],i);
			try {
				tracker.waitForID(i);
			} catch (InterruptedException e) {}
		}
		explanationflag = 0;
	}
	
	public void setexplanationflag(int flag){
		explanationflag = flag;
	}
	
	public void paint(Graphics graphics) {//画面を描く
		if(explanationflag == 1){
			graphics.drawImage(explanation[1], 0, 0, null);
		}
		else if(explanationflag == 2){
			graphics.drawImage(explanation[2], 0, 0, null);
		}
		else if(explanationflag == 3){
			graphics.drawImage(explanation[3], 0, 0, null);
		}
		else if(explanationflag == 4){
			graphics.drawImage(explanation[4], 0, 0, null);
		}
		else if(explanationflag == 5){
			graphics.drawImage(explanation[5], 0, 0, null);
		}
		else{
			graphics.drawImage(explanation[0], 0, 0, null);
		}
	}
}
