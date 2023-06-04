import java.util.*;

public class ComputerMoves{
	int[] changecardflag = new int[5];//カードを交換するか決める
	int[] card = new int[5];//カードの種類
	int[] sortcard = new int[5];//カードの並べ替え
	int[] cardnum = new int[5];//カードの順番そのままの数
	int[] suit = new int[4];
	int pattern;//どう交換するかのパターン
	int i, num1, num2;
	int straight;//ストレートの確認
	int flash;//フラッシュの確認
	
	public void changecard(int card0, int card1, int card2, int card3, int card4){
		pattern = 0;
		card[0] = card0; //カードの代入をする
		card[1] = card1;
		card[2] = card2;
		card[3] = card3;
		card[4] = card4;
		
		for(i = 0; i < 5; i++){//カードの数を取得し並べ替える
			sortcard[i] = card[i] % 13;
			if(sortcard[i] == 0){
				sortcard[i] = 13;
			}
		}
		//カードを小さい順に並べる
		Arrays.sort(sortcard);
		num1 = sortcard[0];//1番小さい数字
		num2 = sortcard[1];//２番目に小さい数字
		
		for(i = 0; i < 5; i++){//カードの数を取得する+並べ替えはしない
			cardnum[i] = card[i] % 13;
			if(cardnum[i] == 0){
				cardnum[i] = 13;
			}
		}
		
		//カードフラグの初期化
		for(i = 0; i < 5; i++){
			changecardflag[i] = 1;
		}
		
		//スーツの初期化
		for(i = 0; i < 4; i++){
			suit[i] = 0;
		}
		
		//同じスーツが４枚以上であるかの判定
		flash = 0;
		for(i = 0; i< 5; i++){//スペード
			if(0 < card[i] && card[i] < 14){
				flash++;
				suit[0] += 1;
				if(flash > 3){
					pattern = 3;
				}
			}
		}
		
		flash = 0;
		for(i = 0; i< 5; i++){//クラブ
			if(13 < card[i] && card[i] < 27){
				flash++;
				suit[1] += 1;
				if(flash > 3){
					pattern = 3;
				}
			}
		}
		
		flash = 0;
		for(i = 0; i< 5; i++){//ダイヤ
			if(26 < card[i] && card[i] < 40){
				flash++;
				suit[2] += 1;
				if(flash > 3){
					pattern = 3;
				}
			}
		}
		
		flash = 0;
		for(i = 0; i< 5; i++){//ハート
			if(39 < card[i] && card[i] < 53){
				flash++;
				suit[3] += 1;
				if(flash > 3){
					pattern = 3;
				}
			}
		}
		
		//チェンジフラグを代入していく
		if(pattern == 3){
			if(suit[0] > 3){
				for(i = 0; i< 5; i++){//スペード
					if(0 < card[i] && card[i] < 14){
						changecardflag[i] = 0;
					}
				}
			}
			else if(suit[1] > 3){
				for(i = 0; i< 5; i++){//クラブ
					if(13 < card[i] && card[i] < 27){
						changecardflag[i] = 0;
					}
				}
			}
			else if(suit[2] > 3){
				for(i = 0; i< 5; i++){//ダイヤ
					if(26 < card[i] && card[i] < 40){
						changecardflag[i] = 0;
					}
				}
			}
			else {
				for(i = 0; i< 5; i++){//ハート
					if(39 < card[i] && card[i] < 53){
						changecardflag[i] = 0;
					}
				}
			}
		}
		else{//ペアがあるかを判定
			for(i = 1; i < 5; i++){
				if(cardnum[0] == cardnum[i]){
					pattern = 2;
					changecardflag[0] = 0;
					changecardflag[i] = 0;
				}
			}
				
			for(i = 2; i < 5; i++){
				if(cardnum[1] == cardnum[i]){
					pattern = 2;
					changecardflag[1] = 0;
					changecardflag[i] = 0;
				}
			}
			
			for(i = 3; i < 5; i++){
				if(cardnum[2] == cardnum[i]){
					pattern = 2;
					changecardflag[2] = 0;
					changecardflag[i] = 0;
				}
			}
			
			if(cardnum[3] == cardnum[4]){
				pattern = 2;
				changecardflag[3] = 0;
				changecardflag[4] = 0;
			}
		}
		
		if(pattern < 2){//ストレートに近いかを判定
			straight = 0;
			for(i = 0; i< 5; i++){
				if(cardnum[i] == 1 || cardnum[i] == 10 || cardnum[i] == 11 || cardnum[i] == 12 || cardnum[i] == 13){
					straight++;
				}
			}
			
			if(5 == straight){
				pattern = 1;
				for(i = 0; i< 5; i++){
					if(cardnum[i] == 1 || cardnum[i] == 10 || cardnum[i] == 11 || cardnum[i] == 12 || cardnum[i] == 13){
						changecardflag[i] = 0;
					}
				}
			}
			
			straight = 0;
			if(pattern < 1){
				for(i = 0; i< 5; i++){
					if(num1 <= cardnum[i] && cardnum[i] < num1 + 4){
						straight++;
					}
				}
				
				if(4 <= straight){
					pattern = 1;
					for(i = 0; i< 5; i++){
						if(num1 <= cardnum[i] && cardnum[i] <= num1 + 4){
							changecardflag[i] = 0;
						}
					}
				}
			}
			
			straight = 0;
			if(pattern < 1){
				for(i = 0; i< 5; i++){
					if(num2 <= cardnum[i] && cardnum[i] < num2 + 4){
						straight++;
					}
				}
				
				if(4 <= straight){
					pattern = 1;
					for(i = 0; i< 5; i++){
						if(num2 <= cardnum[i] && cardnum[i] <= num2 + 4){
							changecardflag[i] = 0;
						}
					}
				}
			}
		}
		
		if(pattern == 0){//役からほど遠い
			for(i = 0; i < 5; i++){
				changecardflag[i] = 1;
			}
		}
	}
	
	public int flagset0(){
		return changecardflag[0];
	}
	
	public int flagset1(){
		return changecardflag[1];
	}
	
	public int flagset2(){
		return changecardflag[2];
	}
	
	public int flagset3(){
		return changecardflag[3];
	}
	
	public int flagset4(){
		return changecardflag[4];
	}
}