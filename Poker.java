import java.lang.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Poker extends JFrame implements Runnable, MouseListener{
	TitleScreen ts = new TitleScreen();//タイトル画面の定義
	PlayScreen ps = new PlayScreen();//プレイ画面の定義
	SetupScreen ss = new SetupScreen();//設定画面の定義
	ExplanationScreen es = new ExplanationScreen();//説明画面の定義
	BreakScreen bs = new BreakScreen();//中断画面の定義
	DeterminedCard determinedcard = new DeterminedCard();//カードの種類を決める
	PokerRoles pokerroles = new PokerRoles();
	ComputerMoves cm = new ComputerMoves();
	GameSound gs = new GameSound();
	int maingameflag = 0;//メインのゲーム進行フラグ
	int gameflag = 0;//ゲーム進行フラグ
	int explanationflag = 0;//ゲーム説明のフラグ
	int breakflag = 0;//中断画面のフラグ
	int cardlist;//使用するカードの種類を決める
	int mx, my;//マウスがクリックされた位置
	Thread runner = null;//ゲームの進行
	int dx, dy;//カードの動き
	int[] x = new int[11];//x座標の動き
	int[] y = new int[11]; 
	
	int exchangeY = 20;//交換したいカードを動かす
	int changeflag1 = 0, changeflag2 = 0, changeflag3 = 0,
		changeflag4 = 0, changeflag5 = 0, changeflag6 = 0,
		changeflag7 = 0, changeflag8 = 0, changeflag9 = 0,
		changeflag10 = 0;//交換したいカードの判定フラグ
	int[] dealtcard = new int[10];//カードの種類を格納する
	int mychangecard = 0;//どのカードを交換したかのフラグ
	int comchangecard = 0;//どのカードを交換したかのフラグ
	int[] strength = new int[2];//役の強さ
	int i = 0;
	
	public Poker() {//ゲームシステムの初期フレーム設定
		setTitle("Poker");//タイトル
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//システムを閉じる
		setSize(1037,613);//1023,613
		setLocationRelativeTo(null);
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.add(ts, BorderLayout.CENTER);
		cardlist = 0;
		dx = -31;
		dy = 37;
		initVariable();//初期化
		start();//ゲーム開始
		gs.start();
		addMouseListener(this);//マウスリスナーを配置
	}
	
	public void start(){
		if(runner == null){
			runner = new Thread(this);
			runner.start();
		}
	}
	
	public void run(){
		while(runner != null){
			if(maingameflag == 3 && breakflag == 0){
				move();
				repaint();
				try {
					runner.sleep(100);
				} catch(InterruptedException e){}
			}
			else{
				repaint();
				try {
					runner.sleep(100);
				} catch(InterruptedException e){}
			}
		}
	}
	
	public void initVariable(){//初期化
		determinedcard.cardlistset(cardlist);
		gameflag = 0;
		ps.setgameflag(gameflag);
		changeflag1 = 0;
		changeflag2 = 0;
		changeflag3 = 0;
		changeflag4 = 0;
		changeflag5 = 0;
		changeflag6 = 0;
		changeflag7 = 0; 
		changeflag8 = 0; 
		changeflag9 = 0;
		changeflag10 = 0;
		mychangecard = 0;
		comchangecard = 0;
		x[0] = 0;
		y[0] = 0;
		for(i = 1; i < 11; i++){
			x[i] = 839;
			y[i] = 111;
			ps.setX(x[i], i);
			ps.setY(y[i], i);
		}
		
		for(i = 0; i < 10; i++){
			dealtcard[i] = 0;
		}
		determinedcard.cardreset();
		dealtcard[0] = determinedcard.setcard1();
		dealtcard[1] = determinedcard.setcard2();
		dealtcard[2] = determinedcard.setcard3();
		dealtcard[3] = determinedcard.setcard4();
		dealtcard[4] = determinedcard.setcard5();
		dealtcard[5] = determinedcard.setcard6();
		dealtcard[6] = determinedcard.setcard7();
		dealtcard[7] = determinedcard.setcard8();
		dealtcard[8] = determinedcard.setcard9();
		dealtcard[9] = determinedcard.setcard10();
		for(i = 0; i < 10; i++){
			ps.setCard(dealtcard[i], i);
		}
		
		for(i = 0; i < 2; i++){
			strength[i] = 0;
		}
		ps.setstrength(strength[0], strength[1]);
	}
	
	public void move(){//カードに動きを付ける
		if(gameflag == 0){//上段にカードを配る
			if(x[1] > 219){
				x[1] += dx;
				ps.setX(x[1], 1);
			}
			else if(x[1] == 219){
				gameflag = 1;
				ps.setgameflag(gameflag);
				System.out.println("上段終了" + gameflag);
			}
			
			if(x[2] > 343){
				x[2] += dx;
				ps.setX(x[2], 2);
			}
			
			if(x[3] > 467){
				x[3] += dx;
				ps.setX(x[3], 3);
			}
			
			if(x[4] > 591){
				x[4] += dx;
				ps.setX(x[4], 4);
			}
			
			if(x[5] > 715){
				x[5] += dx;
				ps.setX(x[5], 5);
			}
		}
		else if(gameflag == 1){//下にカードを移動させる
			if(y[10] < 333){
				y[6] += dy;
				y[7] += dy;
				y[8] += dy;
				y[9] += dy;
				y[10] += dy;
				for(i = 6; i < 11; i++){
					ps.setY(y[i], i);
				}
			}
			else if(y[10] == 333){
				gameflag = 2;
				ps.setgameflag(gameflag);
				System.out.println("下移動完了" + gameflag);
			}
		}
		else if(gameflag == 2){//下段のカードを配り終える
			if(x[6] > 219){
				x[6] += dx;
				ps.setX(x[6], 6);
			}
			else if(x[6] == 219){
				gameflag = 3;
				ps.setgameflag(gameflag);
				System.out.println("下段終了" + gameflag);
			}
			
			if(x[7] > 343){
				x[7] += dx;
				ps.setX(x[7], 7);
			}
			
			if(x[8] > 467){
				x[8] += dx;
				ps.setX(x[8], 8);
			}
			
			if(x[9] > 591){
				x[9] += dx;
				ps.setX(x[9], 9);
			}
			
			if(x[10] > 715){
				x[10] += dx;
				ps.setX(x[10], 10);
			}
		}
		else if(gameflag == 3){//コンピューターの交換するカードを選択する
			cm.changecard(dealtcard[5], dealtcard[6], dealtcard[7], dealtcard[8], dealtcard[9]);
			changeflag6 = cm.flagset0();
			changeflag7 = cm.flagset1();
			changeflag8 = cm.flagset2();
			changeflag9 = cm.flagset3();
			changeflag10 = cm.flagset4();
			gameflag = 4;
			ps.setgameflag(gameflag);
		}
		else if(gameflag == 4){//配ったカードをオープンする＆交換するカードを選択する
			
		}
		else if(gameflag == 5){//新たなカードをセットする
			if(changeflag1 == 1){//1枚目のカードを右にセットする
				y[6] = 333;
				x[6] = 839;
				ps.setX(x[6], 6);
				ps.setY(y[6], 6);
			}
			
			if(changeflag2 == 1){//2枚目のカードを右にセットする
				y[7] = 333;
				x[7] = 839;
				ps.setX(x[7], 7);
				ps.setY(y[7], 7);
			}
			
			if(changeflag3 == 1){//3枚目のカードを右にセットする
				y[8] = 333;
				x[8] = 839;
				ps.setX(x[8], 8);
				ps.setY(y[8], 8);
			}
			
			if(changeflag4 == 1){//4枚目のカードを右にセットする
				y[9] = 333;
				x[9] = 839;
				ps.setX(x[9], 9);
				ps.setY(y[9], 9);
			}
			
			if(changeflag5 == 1){//5枚目のカードを右にセットする
				y[10] = 333;
				x[10] = 839;
				ps.setX(x[10], 10);
				ps.setY(y[10], 10);
			}
			
			if(changeflag10 == 1){//5枚目のカードを右にセットする
				y[5] = 111;
				x[5] = 839;
				ps.setX(x[5], 5);
				ps.setY(y[5], 5);
				comchangecard = 5;
			}
			
			if(changeflag9 == 1){//4枚目のカードを右にセットする
				y[4] = 111;
				x[4] = 839;
				ps.setX(x[4], 4);
				ps.setY(y[4], 4);
				comchangecard = 4;
			}
			
			if(changeflag8 == 1){//3枚目のカードを右にセットする
				y[3] = 111;
				x[3] = 839;
				ps.setX(x[3], 3);
				ps.setY(y[3], 3);
				comchangecard = 3;
			}
			
			if(changeflag7 == 1){//2枚目のカードを右にセットする
				y[2] = 111;
				x[2] = 839;
				ps.setX(x[2], 2);
				ps.setY(y[2], 2);
				comchangecard = 2;
			}
			
			if(changeflag6 == 1){//1枚目のカードを右にセットする
				y[1] = 111;
				x[1] = 839;
				ps.setX(x[1], 1);
				ps.setY(y[1], 1);
				comchangecard = 1;
			}
			
			gameflag = 6;
			ps.setgameflag(gameflag);
		}
		else if(gameflag == 6){//コンピューターのカードを移動させる
			if(comchangecard == 0){
				gameflag = 7;
				ps.setgameflag(gameflag);
				System.out.println("交換カード移動終了" + gameflag);
			}
			
			if(x[1] > 219 && changeflag6 == 1){
				x[1] += dx;
				ps.setX(x[1], 1);
			}
			else if(x[1] == 219 && comchangecard == 1){
				gameflag = 7;
				ps.setgameflag(gameflag);
				System.out.println("交換カード移動終了" + gameflag);
			}
			
			if(x[2] > 343 && changeflag7 == 1){
				x[2] += dx;
				ps.setX(x[2], 2);
			}
			else if(x[2] == 343 && comchangecard == 2){
				gameflag = 7;
				ps.setgameflag(gameflag);
				System.out.println("交換カード移動終了" + gameflag);
			}
			
			if(x[3] > 467 && changeflag8 == 1){
				x[3] += dx;
				ps.setX(x[3], 3);
			}
			else if(x[3] == 467 && comchangecard == 3){
				gameflag = 7;
				ps.setgameflag(gameflag);
				System.out.println("交換カード移動終了" + gameflag);
			}
			
			if(x[4] > 591 && changeflag9 == 1){
				x[4] += dx;
				ps.setX(x[4], 4);
			}
			else if(x[4] == 591 && comchangecard == 4){
				gameflag = 7;
				ps.setgameflag(gameflag);
				System.out.println("交換カード移動終了" + gameflag);
			}
			
			if(x[5] > 715 && changeflag10 == 1){
				x[5] += dx;
				ps.setX(x[5], 5);
			}
			else if(x[5] == 715 && comchangecard == 5){
				gameflag = 7;
				ps.setgameflag(gameflag);
				System.out.println("交換カード移動終了" + gameflag);
			}
		}
		else if(gameflag == 7){//新たなカードを移動させる
			if(mychangecard == 0){
				gameflag = 8;
				ps.setgameflag(gameflag);
				System.out.println("交換カード移動終了" + gameflag);
			}
			
			if(x[6] > 219 && changeflag1 == 1){
				x[6] += dx;
				ps.setX(x[6], 6);
			}
			else if(x[6] == 219 && mychangecard == 1){
				gameflag = 8;
				ps.setgameflag(gameflag);
				System.out.println("交換カード移動終了" + gameflag);
			}
			
			if(x[7] > 343 && changeflag2 == 1){
				x[7] += dx;
				ps.setX(x[7], 7);
			}
			else if(x[7] == 343 && mychangecard == 2){
				gameflag = 8;
				ps.setgameflag(gameflag);
				System.out.println("交換カード移動終了" + gameflag);
			}
			
			if(x[8] > 467 && changeflag3 == 1){
				x[8] += dx;
				ps.setX(x[8], 8);
			}
			else if(x[8] == 467 && mychangecard == 3){
				gameflag = 8;
				ps.setgameflag(gameflag);
				System.out.println("交換カード移動終了" + gameflag);
			}
			
			if(x[9] > 591 && changeflag4 == 1){
				x[9] += dx;
				ps.setX(x[9], 9);
			}
			else if(x[9] == 591 && mychangecard == 4){
				gameflag = 8;
				ps.setgameflag(gameflag);
				System.out.println("交換カード移動終了" + gameflag);
			}
			
			if(x[10] > 715 && changeflag5 == 1){
				x[10] += dx;
				ps.setX(x[10], 10);
			}
			else if(x[10] == 715 && mychangecard == 5){
				gameflag = 8;
				ps.setgameflag(gameflag);
				System.out.println("交換カード移動終了" + gameflag);
			}
		}
		else if(gameflag == 8){//交換するカードの種類を決める
			if(changeflag1 == 1){
				dealtcard[0] = determinedcard.setcard1();
				ps.setCard(dealtcard[0], 0);
			}
			
			if(changeflag2 == 1){
				dealtcard[1] = determinedcard.setcard2();
				ps.setCard(dealtcard[1], 1);
			}
			
			if(changeflag3 == 1){
				dealtcard[2] = determinedcard.setcard3();
				ps.setCard(dealtcard[2], 2);
			}
			
			if(changeflag4 == 1){
				dealtcard[3] = determinedcard.setcard4();
				ps.setCard(dealtcard[3], 3);
			}
			
			if(changeflag5 == 1){
				dealtcard[4] = determinedcard.setcard5();
				ps.setCard(dealtcard[4], 4);
			}
			
			if(changeflag6 == 1){
				dealtcard[5] = determinedcard.setcard6();
				ps.setCard(dealtcard[5], 5);
			}
			
			if(changeflag7 == 1){
				dealtcard[6] = determinedcard.setcard7();
				ps.setCard(dealtcard[6], 6);
			}
			
			if(changeflag8 == 1){
				dealtcard[7] = determinedcard.setcard8();
				ps.setCard(dealtcard[7], 7);
			}
			
			if(changeflag9 == 1){
				dealtcard[8] = determinedcard.setcard9();
				ps.setCard(dealtcard[8], 8);
			}
			
			if(changeflag10 == 1){
				dealtcard[9] = determinedcard.setcard10();
				ps.setCard(dealtcard[9], 9);
			}
			
			gameflag = 9;
			ps.setgameflag(gameflag);
		}
		else if(gameflag == 9){//新たなカードをオープンする
			strength[0] = pokerroles.judgment(dealtcard[0], dealtcard[1], dealtcard[2], dealtcard[3], dealtcard[4]);
			strength[1] = pokerroles.judgment(dealtcard[5], dealtcard[6], dealtcard[7], dealtcard[8], dealtcard[9]);
			System.out.println("プレイヤーの役："+strength[0]);
			System.out.println("コンピューターの役："+strength[1]);
			if(strength[0] > strength[1]){
				System.out.println("プレイヤーの勝ち");
			}
			else if(strength[0] < strength[1]){
				System.out.println("コンピューターの勝ち");
			}
			else{
				System.out.println("引き分け");
			}
			ps.setstrength(strength[0], strength[1]);
			gameflag = 10;
			ps.setgameflag(gameflag);
		}
		else if(gameflag == 10){//役判定を行い、勝敗判定を行う
			
		}
	}
	
	public void change(Component com) {
		//ContentPaneにはめ込まれたパネルを削除
		getContentPane().removeAll();
		super.add(com);//パネルの追加
		validate();//更新
		repaint();//再描画
	}
	
	public void mouseClicked(MouseEvent e) {
		// マウスのボタンがクリックされた
	}
	public void mousePressed(MouseEvent e) {
		// マウスのボタンが押し下げられた
		System.out.println("クリック座標"+e.getPoint());
		mx = e.getX();
		my = e.getY();
		System.out.println("["+mx+"]["+my+"]");
		if(maingameflag == 0){//タイトル画面
			if(352 <= mx && mx <= 642 && 263 <= my && my <= 317){
				maingameflag = 1;//設定画面に移動
				change(ss);
				System.out.println("設定ボタンが押されました"+maingameflag);
			}
			else if(352 <= mx && mx <= 642 && 343 <= my && my <= 398){
				maingameflag = 2;//説明画面に移動
				change(es);
				System.out.println("説明ボタンが押されました"+maingameflag);
			}
			else if(353 <= mx && mx <= 642 && 423 <= my && my <= 479){
				maingameflag = 3;//プレイ画面に移動
				change(ps);
				System.out.println("スタートボタンが押されました"+maingameflag);
			}
		}
		else if(maingameflag == 1){
			if(155 <= mx && mx <= 879 && 217 <= my && my <= 282){
				cardlist = 0;//すべてのカードを使用
				initVariable();
				maingameflag = 0;
				change(ts);
				System.out.println("0が押されました"+maingameflag);
			}
			else if(155 <= mx && mx <= 879 && 354 <= my && my <= 419){
				cardlist = 1;//スペードとハートのみ
				initVariable();
				maingameflag = 0;
				change(ts);
				System.out.println("1が押されました"+maingameflag);
			}
			else if(155 <= mx && mx <= 879 && 487 <= my && my <= 552){
				cardlist = 2;//A,10,J,Q,Kのみ
				initVariable();
				maingameflag = 0;
				change(ts);
				System.out.println("2が押されました"+maingameflag);
			}
		}
		else if(maingameflag == 2){
			if(explanationflag == 0){//
				if(10 <= mx && mx <= 86 && 525 <= my && my <= 602){
					maingameflag = 0;
					change(ts);
				}
				else if(935 <= mx && mx <= 1018 && 525 <= my && my <= 602){
					explanationflag = 1;
					es.setexplanationflag(explanationflag);
				}
			}
			else if(explanationflag == 1){//
				if(10 <= mx && mx <= 86 && 525 <= my && my <= 602){
					explanationflag = 0;
					es.setexplanationflag(explanationflag);
				}
				else if(935 <= mx && mx <= 1018 && 525 <= my && my <= 602){
					explanationflag = 2;
					es.setexplanationflag(explanationflag);
				}
			}
			else if(explanationflag == 2){//
				if(10 <= mx && mx <= 86 && 525 <= my && my <= 602){
					explanationflag = 1;
					es.setexplanationflag(explanationflag);
				}
				else if(935 <= mx && mx <= 1018 && 525 <= my && my <= 602){
					explanationflag = 3;
					es.setexplanationflag(explanationflag);
				}
			}
			else if(explanationflag == 3){//
				if(10 <= mx && mx <= 86 && 525 <= my && my <= 602){
					explanationflag = 2;
					es.setexplanationflag(explanationflag);
				}
				else if(935 <= mx && mx <= 1018 && 525 <= my && my <= 602){
					explanationflag = 4;
					es.setexplanationflag(explanationflag);
				}
			}
			else if(explanationflag == 4){//
				if(10 <= mx && mx <= 86 && 525 <= my && my <= 602){
					explanationflag = 3;
					es.setexplanationflag(explanationflag);
				}
				else if(935 <= mx && mx <= 1018 && 525 <= my && my <= 602){
					explanationflag = 5;
					es.setexplanationflag(explanationflag);
				}
			}
			else if(explanationflag == 5){//
				if(10 <= mx && mx <= 86 && 525 <= my && my <= 602){
					explanationflag = 4;
					es.setexplanationflag(explanationflag);
				}
				else if(935 <= mx && mx <= 1018 && 525 <= my && my <= 602){
					maingameflag = 0;
					change(ts);
					explanationflag = 0;
					es.setexplanationflag(explanationflag);
				}
			}
		}
		else if(maingameflag == 3){//プレイ画面
			if(breakflag == 0){
				if(0 <= gameflag && gameflag < 10 && 916 <= mx && mx <= 1003 && 506 <= my && my <= 590){//中断
					breakflag = 1;
					change(bs);
				}
				
				if(gameflag == 4){
					if(changeflag1 == 0 && 227 <= mx && mx <= 317 && 363 <= my && my <= 491){//１番目のカードを交換
						changeflag1 = 1;
						y[6] -= exchangeY;
						ps.setY(y[6], 6);
						System.out.println("１番目のカードを交換");
					}
					else if(changeflag1 == 1 && 227 <= mx && mx <= 317 && 343 <= my && my <= 471){//１番目の交換を中止
						changeflag1 = 0;
						y[6] += exchangeY;
						ps.setY(y[6], 6);
						System.out.println("１番目の交換を中止");
					}
					else if(changeflag2 == 0 && 351 <= mx && mx <= 441 && 363 <= my && my <= 491){//2番目のカードを交換
						changeflag2 = 1;
						y[7] -= exchangeY;
						ps.setY(y[7], 7);
						System.out.println("2番目のカードを交換");
					}
					else if(changeflag2 == 1 && 351 <= mx && mx <= 441 && 343 <= my && my <= 471){//2番目の交換を中止
						changeflag2 = 0;
						y[7] += exchangeY;
						ps.setY(y[7], 7);
						System.out.println("2番目の交換を中止");
					}
					else if(changeflag3 == 0 && 475 <= mx && mx <= 566 && 363 <= my && my <= 491){//3番目のカードを交換
						changeflag3 = 1;
						y[8] -= exchangeY;
						ps.setY(y[8], 8);
						System.out.println("3番目のカードを交換");
					}
					else if(changeflag3 == 1 && 475 <= mx && mx <= 566 && 343 <= my && my <= 471){//3番目の交換を中止
						changeflag3 = 0;
						y[8] += exchangeY;
						ps.setY(y[8], 8);
						System.out.println("3番目の交換を中止");
					}
					else if(changeflag4 == 0 && 599 <= mx && mx <= 689 && 363 <= my && my <= 491){//4番目のカードを交換
						changeflag4 = 1;
						y[9] -= exchangeY;
						ps.setY(y[9], 9);
						System.out.println("4番目のカードを交換");
					}
					else if(changeflag4 == 1 && 599 <= mx && mx <= 689 && 343 <= my && my <= 471){//4番目の交換を中止
						changeflag4 = 0;
						y[9] += exchangeY;
						ps.setY(y[9], 9);
						System.out.println("4番目の交換を中止");
					}
					else if(changeflag5 == 0 && 723 <= mx && mx <= 813 && 363 <= my && my <= 491){//5番目のカードを交換
						changeflag5 = 1;
						y[10] -= exchangeY;
						ps.setY(y[10], 10);
						System.out.println("5番目のカードを交換");
					}
					else if(changeflag5 == 1 && 723 <= mx && mx <= 813 && 343 <= my && my <= 471){//5番目の交換を中止
						changeflag5 = 0;
						y[10] += exchangeY;
						ps.setY(y[10], 10);
						System.out.println("5番目の交換を中止");
					}
					else if(350 <= mx && mx <= 683 && 505 <= my && my <= 571){//交換カードの選択をやめる
						if(changeflag5 == 1){
							mychangecard = 5;
						}
						
						if(changeflag4 == 1){
							mychangecard = 4;
						}
						
						if(changeflag3 == 1){
							mychangecard = 3;
						}
						
						if(changeflag2 == 1){
							mychangecard = 2;
						}
						
						if(changeflag1 == 1){
							mychangecard = 1;
						}
						gameflag = 5;//カード交換開始
						ps.setgameflag(gameflag);
						System.out.println("交換終了します"+gameflag);
					}
				}
				else if(gameflag == 10){
					if(908 <= mx && mx <= 1000 && 502 <= my && my <= 587){
						gameflag = 11;
						ps.setgameflag(gameflag);
					}
				}
				else if(gameflag == 11){//「もう一度遊ぶ」と「ホーム画面に戻る」
					if(275 <= mx && mx <= 760 && 205 <= my && my <= 283){//もう一度遊ぶ
						initVariable();
					}
					else if(275 <= mx && mx <= 760 && 353 <= my && my <= 431){//ホーム画面に戻る
						initVariable();
						maingameflag = 0;//タイトル画面に移動
						change(ts);
					}
				}
			}
			else if(breakflag == 1){
				if(275 <= mx && mx <= 760 && 206 <= my && my <= 283){//ゲームを再開
					change(ps);
					breakflag = 0;
				}
				else if( 275 <= mx && mx <= 760 && 353 <= my && my <= 431){//ゲームをやめる
					initVariable();
					maingameflag = 0;//タイトル画面に移動
					change(ts);
					breakflag = 0;
				}
			}
		}
	}
	public void mouseReleased(MouseEvent e) {
		// マウスのボタンが離された
	}
	public void mouseEntered(MouseEvent e) {
		// 画面内にマウスカーソルが侵入した
	}
	public void mouseExited(MouseEvent e) {
		// マウスカーソルが画面外へ出た
	}
}
