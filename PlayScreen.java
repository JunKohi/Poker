import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayScreen extends Component{//ゲーム画面クラス
	BufferedImage backscreen = null;//ゲーム画面の背景
	BufferedImage returnscreen = null;//もう一度・ホーム画面に戻る画面
	Image[] card;//カードの画像
	Image[] instructiontext;//指示のテキスト画像
	Image[] role;//役の画像
	String psname = null;
	String rsname = null;
	int i = 0;
	int gameflag = 0;//ゲームフラグ
	int[] x = new int[11];//x座標の動き
	int[] y = new int[11]; 
	int[] dealtcard = new int[10];//カードの種類を格納する
	int[] strength = new int[2];//役の強さを格納する
	
	public PlayScreen() {//ゲーム画面を定義
		psname = "Backgroundplayscreen.png";//プレイ画面の背景
		rsname = "Returnscreen.png";
		
		try {
			backscreen = ImageIO.read(new File(psname));//画像の読み込み
			returnscreen = ImageIO.read(new File(rsname));//画像の読み込み
		} catch (IOException e) {
			System.out.println("image file not found. ");//失敗処理
		}
		
		card = new Image[53];
		instructiontext = new Image[7];
		role = new Image[10];
		Toolkit cardlist = Toolkit.getDefaultToolkit();
		card[0] = cardlist.getImage(getClass().getResource("Backcard-0.png"));//カードの裏面
		card[1] = cardlist.getImage(getClass().getResource("Spade1-1.png"));//スペード
		card[2] = cardlist.getImage(getClass().getResource("Spade2-2.png"));
		card[3] = cardlist.getImage(getClass().getResource("Spade3-3.png"));
		card[4] = cardlist.getImage(getClass().getResource("Spade4-4.png"));
		card[5] = cardlist.getImage(getClass().getResource("Spade5-5.png"));
		card[6] = cardlist.getImage(getClass().getResource("Spade6-6.png"));
		card[7] = cardlist.getImage(getClass().getResource("Spade7-7.png"));
		card[8] = cardlist.getImage(getClass().getResource("Spade8-8.png"));
		card[9] = cardlist.getImage(getClass().getResource("Spade9-9.png"));
		card[10] = cardlist.getImage(getClass().getResource("Spade10-10.png"));
		card[11] = cardlist.getImage(getClass().getResource("Spade11-11.png"));
		card[12] = cardlist.getImage(getClass().getResource("Spade12-12.png"));
		card[13] = cardlist.getImage(getClass().getResource("Spade13-13.png"));
		card[14] = cardlist.getImage(getClass().getResource("Clover1-14.png"));//クローバー
		card[15] = cardlist.getImage(getClass().getResource("Clover2-15.png"));
		card[16] = cardlist.getImage(getClass().getResource("Clover3-16.png"));
		card[17] = cardlist.getImage(getClass().getResource("Clover4-17.png"));
		card[18] = cardlist.getImage(getClass().getResource("Clover5-18.png"));
		card[19] = cardlist.getImage(getClass().getResource("Clover6-19.png"));
		card[20] = cardlist.getImage(getClass().getResource("Clover7-20.png"));
		card[21] = cardlist.getImage(getClass().getResource("Clover8-21.png"));
		card[22] = cardlist.getImage(getClass().getResource("Clover9-22.png"));
		card[23] = cardlist.getImage(getClass().getResource("Clover10-23.png"));
		card[24] = cardlist.getImage(getClass().getResource("Clover11-24.png"));
		card[25] = cardlist.getImage(getClass().getResource("Clover12-25.png"));
		card[26] = cardlist.getImage(getClass().getResource("Clover13-26.png"));
		card[27] = cardlist.getImage(getClass().getResource("Dia1-27.png"));//ダイヤ
		card[28] = cardlist.getImage(getClass().getResource("Dia2-28.png"));
		card[29] = cardlist.getImage(getClass().getResource("Dia3-29.png"));
		card[30] = cardlist.getImage(getClass().getResource("Dia4-30.png"));
		card[31] = cardlist.getImage(getClass().getResource("Dia5-31.png"));
		card[32] = cardlist.getImage(getClass().getResource("Dia6-32.png"));
		card[33] = cardlist.getImage(getClass().getResource("Dia7-33.png"));
		card[34] = cardlist.getImage(getClass().getResource("Dia8-34.png"));
		card[35] = cardlist.getImage(getClass().getResource("Dia9-35.png"));
		card[36] = cardlist.getImage(getClass().getResource("Dia10-36.png"));
		card[37] = cardlist.getImage(getClass().getResource("Dia11-37.png"));
		card[38] = cardlist.getImage(getClass().getResource("Dia12-38.png"));
		card[39] = cardlist.getImage(getClass().getResource("Dia13-39.png"));
		card[40] = cardlist.getImage(getClass().getResource("Heart1-40.png"));//ハート
		card[41] = cardlist.getImage(getClass().getResource("Heart2-41.png"));
		card[42] = cardlist.getImage(getClass().getResource("Heart3-42.png"));
		card[43] = cardlist.getImage(getClass().getResource("Heart4-43.png"));
		card[44] = cardlist.getImage(getClass().getResource("Heart5-44.png"));
		card[45] = cardlist.getImage(getClass().getResource("Heart6-45.png"));
		card[46] = cardlist.getImage(getClass().getResource("Heart7-46.png"));
		card[47] = cardlist.getImage(getClass().getResource("Heart8-47.png"));
		card[48] = cardlist.getImage(getClass().getResource("Heart9-48.png"));
		card[49] = cardlist.getImage(getClass().getResource("Heart10-49.png"));
		card[50] = cardlist.getImage(getClass().getResource("Heart11-50.png"));
		card[51] = cardlist.getImage(getClass().getResource("Heart12-51.png"));
		card[52] = cardlist.getImage(getClass().getResource("Heart13-52.png"));
		MediaTracker tracker = new MediaTracker(this);//カード版
		for (i= 0; i < 53; i++ ){
			tracker.addImage(card[i],i);
			try {
				tracker.waitForID(i);
			} catch (InterruptedException e) {}
		}
		
		Toolkit textlist = Toolkit.getDefaultToolkit();
		instructiontext[0] = textlist.getImage(getClass().getResource("ChooseCardText.png"));//カード交換テキスト
		instructiontext[1] = textlist.getImage(getClass().getResource("EndExchangeText.png"));
		instructiontext[2] = textlist.getImage(getClass().getResource("StopButton.png"));
		instructiontext[3] = textlist.getImage(getClass().getResource("NextButton.png"));
		instructiontext[4] = textlist.getImage(getClass().getResource("win.png"));
		instructiontext[5] = textlist.getImage(getClass().getResource("lose.png"));
		instructiontext[6] = textlist.getImage(getClass().getResource("draw.png"));
		MediaTracker texttracker = new MediaTracker(this);//テキスト版
		for (i= 0; i < 7; i++ ){
			texttracker.addImage(instructiontext[i],i);
			try {
				texttracker.waitForID(i);
			} catch (InterruptedException e) {}
		}
		
		//役の画像
		Toolkit rolelist = Toolkit.getDefaultToolkit();
		role[0] = rolelist.getImage(getClass().getResource("Nopair.png"));
		role[1] = rolelist.getImage(getClass().getResource("Onepair.png"));
		role[2] = rolelist.getImage(getClass().getResource("Twopair.png"));
		role[3] = rolelist.getImage(getClass().getResource("Threecard.png"));
		role[4] = rolelist.getImage(getClass().getResource("Straight.png"));
		role[5] = rolelist.getImage(getClass().getResource("Flush.png"));
		role[6] = rolelist.getImage(getClass().getResource("FullHouse.png"));
		role[7] = rolelist.getImage(getClass().getResource("Fourcard.png"));
		role[8] = rolelist.getImage(getClass().getResource("StraightFlush.png"));
		role[9] = rolelist.getImage(getClass().getResource("RoyalStraightFlush.png"));
		MediaTracker roletracker = new MediaTracker(this);//カード版
		for (i= 0; i < 10; i++ ){
			roletracker.addImage(role[i],i);
			try {
				roletracker.waitForID(i);
			} catch (InterruptedException e) {}
		}
		
		gameflag = 0;
		x[0] = 0;
		y[0] = 0;
		for(i = 1; i < 11; i++){
			x[i] = 839;
			y[i] = 111;
		}
	}
	
	public void setX(int xdata, int num){
		x[num] = xdata;
	}
	
	public void setY(int ydata, int num){
		y[num] = ydata;
	}
	
	public void setCard(int cardnum, int num){
		dealtcard[num] = cardnum;
	}
	
	public void setstrength(int strength0, int strength1) {
		strength[0] = strength0;
		strength[1] = strength1;
	}
	
	public void setgameflag(int flag) {
		gameflag = flag;
	}
	
	public void paint(Graphics graphics) {//画面を描く
		if(gameflag == 11){
			graphics.drawImage(returnscreen, 0, 0, null);//もう一度・ホームに戻る画面
		}
		else{
			graphics.drawImage(backscreen, 0, 0, null);//プレイ画面の背景
		}
		
		if(gameflag == 0 || gameflag == 1 || gameflag == 2 || gameflag == 3 || gameflag == 5 || gameflag == 6 || gameflag == 7 || gameflag == 8){//カードの移動
			graphics.drawImage(card[0], x[1], y[1], null);
			graphics.drawImage(card[0], x[2], y[2], null);
			graphics.drawImage(card[0], x[3], y[3], null);
			graphics.drawImage(card[0], x[4], y[4], null);
			graphics.drawImage(card[0], x[5], y[5], null);
			graphics.drawImage(card[0], x[6], y[6], null);
			graphics.drawImage(card[0], x[7], y[7], null);
			graphics.drawImage(card[0], x[8], y[8], null);
			graphics.drawImage(card[0], x[9], y[9], null);
			graphics.drawImage(card[0], x[10], y[10], null);
			graphics.drawImage(instructiontext[2], 914, 482, null);
		}
		else if(gameflag == 4){//配ったカードをオープンする＆交換するカードを選択する
			graphics.drawImage(card[0], x[1], y[1], null);
			graphics.drawImage(card[0], x[2], y[2], null);
			graphics.drawImage(card[0], x[3], y[3], null);
			graphics.drawImage(card[0], x[4], y[4], null);
			graphics.drawImage(card[0], x[5], y[5], null);
			graphics.drawImage(card[dealtcard[0]], x[6], y[6], null);
			graphics.drawImage(card[dealtcard[1]], x[7], y[7], null);
			graphics.drawImage(card[dealtcard[2]], x[8], y[8], null);
			graphics.drawImage(card[dealtcard[3]], x[9], y[9], null);
			graphics.drawImage(card[dealtcard[4]], x[10], y[10], null);
			graphics.drawImage(instructiontext[0], 215, 242, null);
			graphics.drawImage(instructiontext[1], 215, 473, null);
			graphics.drawImage(instructiontext[2], 914, 482, null);
		}
		else if(gameflag == 9){//新たなカードをオープンさせる
			graphics.drawImage(card[dealtcard[5]], x[1], y[1], null);
			graphics.drawImage(card[dealtcard[6]], x[2], y[2], null);
			graphics.drawImage(card[dealtcard[7]], x[3], y[3], null);
			graphics.drawImage(card[dealtcard[8]], x[4], y[4], null);
			graphics.drawImage(card[dealtcard[9]], x[5], y[5], null);
			graphics.drawImage(card[dealtcard[0]], x[6], y[6], null);
			graphics.drawImage(card[dealtcard[1]], x[7], y[7], null);
			graphics.drawImage(card[dealtcard[2]], x[8], y[8], null);
			graphics.drawImage(card[dealtcard[3]], x[9], y[9], null);
			graphics.drawImage(card[dealtcard[4]], x[10], y[10], null);
			graphics.drawImage(instructiontext[2], 914, 482, null);
		}
		else if(gameflag == 10){//役判定を行い、勝敗判定を行う
			graphics.drawImage(card[dealtcard[5]], x[1], y[1], null);
			graphics.drawImage(card[dealtcard[6]], x[2], y[2], null);
			graphics.drawImage(card[dealtcard[7]], x[3], y[3], null);
			graphics.drawImage(card[dealtcard[8]], x[4], y[4], null);
			graphics.drawImage(card[dealtcard[9]], x[5], y[5], null);
			graphics.drawImage(card[dealtcard[0]], x[6], y[6], null);
			graphics.drawImage(card[dealtcard[1]], x[7], y[7], null);
			graphics.drawImage(card[dealtcard[2]], x[8], y[8], null);
			graphics.drawImage(card[dealtcard[3]], x[9], y[9], null);
			graphics.drawImage(card[dealtcard[4]], x[10], y[10], null);
			//役判定
			graphics.drawImage(role[strength[0]], 215, 460, null);//プレイヤーの役
			graphics.drawImage(role[strength[1]], 215, 30, null);//相手の役
			//勝敗判定
			if(strength[0] > strength[1]){
				graphics.drawImage(instructiontext[4], 215, 242, null);//win
			}
			else if(strength[0] < strength[1]){
				graphics.drawImage(instructiontext[5], 215, 242, null);//lose
			}
			else{
				graphics.drawImage(instructiontext[6], 215, 242, null);//draw
			}
			graphics.drawImage(instructiontext[3], 914, 482, null);
		}
	}
}
