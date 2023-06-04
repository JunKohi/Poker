import java.util.*;

public class DeterminedCard{
	int[] card = new int[10];//カードの種類を格納する
	Random rand = new Random();
	int cardlist = 0;//使用するカードの範囲
	int index1 = 0;//カードリスト１で使う
	int index2 = 0;//カードリスト２で使う
	int i = 0;
	
	//カードリスト1
	List<Integer> cardlist1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
								   40, 41, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52); 
	
	//カードリスト２
	List<Integer> cardlist2 = Arrays.asList(1, 10, 11, 12, 13, 14, 23, 24, 25, 26,
								   27, 36, 37, 38, 39, 40, 49, 50, 51, 52); 
	
	public void cardreset(){
		for(i = 0; i < 10; i++){
			card[i] = 0;
		}
	}
	
	public void cardlistset(int num){
		cardlist = num;
	}
	
	public int setcard1(){
		do{
			if(cardlist == 1){//
				index1 = rand.nextInt(cardlist1.size());
				card[0] = cardlist1.get(index1); 
			}
			else if(cardlist == 2){
				index2 = rand.nextInt(cardlist2.size());
				card[0] = cardlist2.get(index2); 
				
			}
			else{
				card[0] = rand.nextInt(52) + 1;
			}
		}while(card[0] == card[1] || card[0] == card[2] || card[0] == card[3] ||
		card[0] == card[4] || card[0] == card[5] || card[0] == card[6] ||
		card[0] == card[7] || card[0] == card[8] || card[0] == card[9]);
		System.out.println("プレイヤーの１枚目のカード："+card[0]);
		return card[0];
	}
	
	public int setcard2(){
		do{
			if(cardlist == 1){
				index1 = rand.nextInt(cardlist1.size());
				card[1] = cardlist1.get(index1); 
			}
			else if(cardlist == 2){
				index2 = rand.nextInt(cardlist2.size());
				card[1] = cardlist2.get(index2); 
			}
			else{
				card[1] = rand.nextInt(52) + 1;
			}
		}while(card[1] == card[0] || card[1] == card[2] || card[1] == card[3] ||
		card[1] == card[4] || card[1] == card[5] || card[1] == card[6] ||
		card[1] == card[7] || card[1] == card[8] || card[1] == card[9]);
		System.out.println("プレイヤーの2枚目のカード："+card[1]);
		return card[1];
	}
	
	public int setcard3(){
		do{
			if(cardlist == 1){
				index1 = rand.nextInt(cardlist1.size());
				card[2] = cardlist1.get(index1); 
			}
			else if(cardlist == 2){
				index2 = rand.nextInt(cardlist2.size());
				card[2] = cardlist2.get(index2); 
			}
			else{
				card[2] = rand.nextInt(52) + 1;
			}
		}while(card[2] == card[1] || card[2] == card[0] || card[2] == card[3] ||
		card[2] == card[4] || card[2] == card[5] || card[2] == card[6] ||
		card[2] == card[7] || card[2] == card[8] || card[2] == card[9]);
		System.out.println("プレイヤーの3枚目のカード："+card[2]);
		return card[2];
	}
	
	public int setcard4(){
		do{
			if(cardlist == 1){
				index1 = rand.nextInt(cardlist1.size());
				card[3] = cardlist1.get(index1); 
			}
			else if(cardlist == 2){
				index2 = rand.nextInt(cardlist2.size());
				card[3] = cardlist2.get(index2); 
			}
			else{
				card[3] = rand.nextInt(52) + 1;
			}
		}while(card[3] == card[1] || card[3] == card[2] || card[3] == card[0] ||
		card[3] == card[4] || card[3] == card[5] || card[3] == card[6] ||
		card[3] == card[7] || card[3] == card[8] || card[3] == card[9]);
		System.out.println("プレイヤーの4枚目のカード："+card[3]);
		return card[3];
	}
	
	public int setcard5(){
		do{
			if(cardlist == 1){
				index1 = rand.nextInt(cardlist1.size());
				card[4] = cardlist1.get(index1); 
			}
			else if(cardlist == 2){
				index2 = rand.nextInt(cardlist2.size());
				card[4] = cardlist2.get(index2); 
			}
			else{
				card[4] = rand.nextInt(52) + 1;
			}
		}while(card[4] == card[1] || card[4] == card[2] || card[4] == card[3] ||
		card[4] == card[0] || card[4] == card[5] || card[4] == card[6] ||
		card[4] == card[7] || card[4] == card[8] || card[4] == card[9]);
		System.out.println("プレイヤーの5枚目のカード："+card[4]);
		return card[4];
	}
	
	public int setcard6(){
		do{
			if(cardlist == 1){
				index1 = rand.nextInt(cardlist1.size());
				card[5] = cardlist1.get(index1); 
			}
			else if(cardlist == 2){
				index2 = rand.nextInt(cardlist2.size());
				card[5] = cardlist2.get(index2); 
			}
			else{
				card[5] = rand.nextInt(52) + 1;
			}
		}while(card[5] == card[1] || card[5] == card[2] || card[5] == card[3] ||
		card[5] == card[4] || card[5] == card[0] || card[5] == card[6] ||
		card[5] == card[7] || card[5] == card[8] || card[5] == card[9]);
		System.out.println("コンピュータの1枚目のカード："+card[5]);
		return card[5];
	}
	
	public int setcard7(){
		do{
			if(cardlist == 1){
				index1 = rand.nextInt(cardlist1.size());
				card[6] = cardlist1.get(index1); 
			}
			else if(cardlist == 2){
				index2 = rand.nextInt(cardlist2.size());
				card[6] = cardlist2.get(index2); 
			}
			else{
				card[6] = rand.nextInt(52) + 1;
			}
		}while(card[6] == card[1] || card[6] == card[2] || card[6] == card[3] ||
		card[6] == card[4] || card[6] == card[5] || card[6] == card[0] ||
		card[6] == card[7] || card[6] == card[8] || card[6] == card[9]);
		System.out.println("コンピュータの2枚目のカード："+card[6]);
		return card[6];
	}
	
	public int setcard8(){
		do{
			if(cardlist == 1){
				index1 = rand.nextInt(cardlist1.size());
				card[7] = cardlist1.get(index1); 
			}
			else if(cardlist == 2){
				index2 = rand.nextInt(cardlist2.size());
				card[7] = cardlist2.get(index2); 
			}
			else{
				card[7] = rand.nextInt(52) + 1;
			}
		}while(card[7] == card[1] || card[7] == card[2] || card[7] == card[3] ||
		card[7] == card[4] || card[7] == card[5] || card[7] == card[6] ||
		card[7] == card[0] || card[7] == card[8] || card[7] == card[9]);
		System.out.println("コンピュータの3枚目のカード："+card[7]);
		return card[7];
	}
	
	public int setcard9(){
		do{
			if(cardlist == 1){
				index1 = rand.nextInt(cardlist1.size());
				card[8] = cardlist1.get(index1); 
			}
			else if(cardlist == 2){
				index2 = rand.nextInt(cardlist2.size());
				card[8] = cardlist2.get(index2); 
			}
			else{
				card[8] = rand.nextInt(52) + 1;
			};
		}while(card[8] == card[1] || card[8] == card[2] || card[8] == card[3] ||
		card[8] == card[4] || card[8] == card[5] || card[8] == card[6] ||
		card[8] == card[7] || card[8] == card[0] || card[8] == card[9]);
		System.out.println("コンピュータの4枚目のカード："+card[8]);
		return card[8];
	}
	
	public int setcard10(){
		do{
			if(cardlist == 1){
				index1 = rand.nextInt(cardlist1.size());
				card[9] = cardlist1.get(index1); 
			}
			else if(cardlist == 2){
				index2 = rand.nextInt(cardlist2.size());
				card[9] = cardlist2.get(index2); 
			}
			else{
				card[9] = rand.nextInt(52) + 1;
			}
		}while(card[9] == card[1] || card[9] == card[2] || card[9] == card[3] ||
		card[9] == card[4] || card[9] == card[5] || card[9] == card[6] ||
		card[9] == card[7] || card[9] == card[8] || card[9] == card[0]);
		System.out.println("コンピュータの5枚目のカード："+card[9]);
		return card[9];
	}
}
