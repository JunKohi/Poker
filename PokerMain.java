import java.lang.*;
import java.awt.*;
import javax.swing.*;

public class PokerMain{
	public static void main(String[] args) throws InterruptedException{//メイン
		Poker pokersystem = new Poker();//スクリーンの表示
		//pokersystem.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//pokersystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pokersystem.setVisible(true);
	}
}
