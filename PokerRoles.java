import java.util.*;

public class PokerRoles{
	int[] card = new int[5];
	int[] cardnum = new int[5];//カードの数
	int i, num;
	int straight;//ストレートの確認
	int flash;//フラッシュの確認
	int countcard, cauntpair;
	int strength;//役の強さ
	
	public int judgment(int card0, int card1, int card2, int card3, int card4){
		strength = 0;
		card[0] = card0; //カードの代入をする
		card[1] = card1;
		card[2] = card2;
		card[3] = card3;
		card[4] = card4;
		//カードを小さい順に並べる
		Arrays.sort(card);
		
		for(i = 0; i < 5; i++){//カードの数を取得する
			cardnum[i] = card[i] % 13;
			if(cardnum[i] == 0){
				cardnum[i] = 13;
			}
		}
		//カードを小さい順に並べる
		Arrays.sort(cardnum);
		
		//ワンペア strength = 1
		countcard = 0;
		cauntpair = 0;
		num = 0;
		for(i = 1; i < 5; i++){
			if(cardnum[0] == cardnum[i]){
				if(i != 4 && cardnum[0] == cardnum[i + 1]){
					if(countcard !=2){
						countcard = 1;//スリーカード
					}
					
					if(i != 3 && cardnum[0] == cardnum[i + 2]){
						countcard = 2;//フォーカード
					}
				}
				
				if(num != cardnum[0] && num != 0){
					cauntpair = 2;
				}
				else if(num == 0){
					num = cardnum[0];
					cauntpair = 1;
				}
				
				strength = 1;
			}
		}
		
		for(i = 2; i < 5; i++){
			if(cardnum[1] == cardnum[i]){
				if(i != 4 && cardnum[1] == cardnum[i + 1]){
					if(countcard !=2){
						countcard = 1;//スリーカード
					}
					
					if(i != 3 && cardnum[1] == cardnum[i + 2]){
						countcard = 2;//フォーカード
					}
				}
				
				if(num != cardnum[1] && num != 0){
					cauntpair = 2;
				}
				else if(num == 0){
					num = cardnum[1];
					cauntpair = 1;
				}
				strength = 1;
			}
		}
		
		for(i = 3; i < 5; i++){
			if(cardnum[2] == cardnum[i]){
				if(i != 4 && cardnum[2] == cardnum[i + 1]){
					if(countcard !=2){
						countcard = 1;//スリーカード
					}
				}
				
				if(num != cardnum[2] && num != 0){
					cauntpair = 2;
				}
				else if(num == 0){
					num = cardnum[2];
					cauntpair = 1;
				}
				strength = 1;
			}
		}
		
		if(cardnum[3] == cardnum[4]){
			if(num != cardnum[3] && num != 0){
					cauntpair = 2;
			}
			else if(num == 0){
				num = cardnum[3];
				cauntpair = 1;
			}
			strength = 1;
		}
		
		//ツーペア strength = 2
		if(cauntpair == 2){
			strength = 2;
		}
		
		//スリーカード strength = 3
		if(countcard == 1){
			strength = 3;
		}
		
		//ストレート strength = 4
		straight = 0;
		if(cardnum[0] == cardnum[1] - 1 && cardnum[0] == cardnum[2] - 2 &&
		   cardnum[0] == cardnum[3] - 3 && cardnum[0] == cardnum[4] - 4){
		   	straight =1;
			strength = 4;
		}
		
		if(cardnum[0] == 1 && cardnum[1] == 10 &&
		   cardnum[2] == 11 && cardnum[3] == 12 && cardnum[4] == 13){
		   	straight =2;
			strength = 4;
		}
		
		//フラッシュ strength = 5
		flash = 0;
		for(i = 0; i< 5; i++){//スペード
			if(0 < card[i] && card[i] < 14){
				flash++;
				if(flash == 5){
					strength = 5;
				}
			}
		}
		
		flash = 0;
		for(i = 0; i< 5; i++){//クラブ
			if(13 < card[i] && card[i] < 27){
				flash++;
				if(flash == 5){
					strength = 5;
				}
			}
		}
		
		flash = 0;
		for(i = 0; i< 5; i++){//ダイヤ
			if(26 < card[i] && card[i] < 40){
				flash++;
				if(flash == 5){
					strength = 5;
				}
			}
		}
		
		flash = 0;
		for(i = 0; i< 5; i++){//ハート
			if(39 < card[i] && card[i] < 53){
				flash++;
				if(flash == 5){
					strength = 5;
				}
			}
		}
		
		//フルハウス strength = 6
		if(cauntpair == 2 && strength == 3){
			strength = 6;
		}
		
		//フォーカード strength = 7
		if(countcard == 2){
			strength = 7;
		}
		
		//ストレートフラッシュ strength = 8
		if(0 < straight && strength == 5){
			strength = 8;
		}
		
		//ロイヤルストレートフラッシュ strength = 9
		if(strength == 8 && straight == 2){
			strength = 9;
		}
		
		return strength;
	}
}