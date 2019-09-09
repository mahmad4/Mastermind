/*
Muhammad Ahmad
mahmad4
mahmad4@u.rochester.edu
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P1 {
	 
	public static List<String> array(int positions, String[] colors){
		int num_comb = (int)Math.pow(colors.length, positions);
		List<String> combos = new ArrayList<String>();
		String temp;
		String orig;
		int xx = -1;
		for (int i = 0; i < positions; i++){
			int period = (int)Math.pow(colors.length, positions - i -1);
			for (int j = 0; j < num_comb; j++){
				if (j%period == 0){
					xx++;
					if (xx >= colors.length){
						xx = 0;
					}
				}
				temp = colors[xx];
				if (period == (int)Math.pow(colors.length, positions-1)){
					combos.add(temp);
				}
				else{
					orig = combos.get(j);
					orig += temp;
					combos.set(j, orig);
				}
		}
		}
		return combos;
	}
	
	public static String nextmove(List<String> combos){
		String next_move = combos.get(0);
		return next_move;
	}	
	
	public static List<String> update(int black, int white, List<String> combos, String next_move){
		
		ArrayList<Character> move = new ArrayList<Character>();
		for(int l = combos.size()-1; l >= 0; l--){
			for (char c : next_move.toCharArray()) {
				  move.add(c);
			}
			String xx = combos.get(l);
			int temp_black = 0;
			int temp_white = 0;
			ArrayList<Character> check = new ArrayList<Character>();
			for (char c : xx.toCharArray()) {
			  check.add(c);
			}
			// no of blacks
			for (int k = check.size()-1; k >= 0; k--){
				if (move.get(k) == check.get(k)){
					temp_black++;
					check.remove(k);
					move.remove(k);
				}
			}
			
			// no of whites
			for (int i = move.size()-1; i >= 0; i--){
				for (int j = check.size()-1; j >= 0; j--){
					try{
					if (move.get(i) == check.get(j)){
							temp_white++;
							check.remove(j);
							move.remove(i);
					}}
					catch (Exception e){
					}
					
				}
			}
			if ((temp_black != black) || (temp_white != white)){
				combos.remove(l);
			}
			move.clear();
		}
		return combos;
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to Mastermind!");
		System.out.println();
		Scanner scan = new Scanner(System.in);
		System.out.println("Press 1 to play, 0 to quit");
		int game = 0;
		game = scan.nextInt();
		while (game == 1){
		
		System.out.println("Enter the number of positions :");
		int positions = scan.nextInt();
		System.out.println("Enter the number of colors you want to enter : ");
		int n_c = scan.nextInt();
		String[] colors = new String[n_c];
		System.out.println("Enter the colors(letters) separated by a space : ");
		for (int i = 0; i < n_c; i++){
			String cc = scan.next();
			colors[i] = cc;
		}
		List<String> combos = array(positions, colors);
		System.out.println(combos);
		int black = 0;
		int white;
		
		while (black != positions){
			String next_move = nextmove(combos);
			System.out.println("Next move : " + next_move);
			System.out.print("Right color and right position : ");
			black = scan.nextInt();
			System.out.print("Right color and wrong position : ");
			System.out.println();
			white = scan.nextInt();
			if (black != positions){
				combos = update(black, white, combos, next_move);
				System.out.println(combos);
				System.out.println();
			}	
		}
		System.out.println("I GOT IT !");
		
		System.out.println("Press 1 to play, 0 to quit");
		game = scan.nextInt();
		}
		System.out.println("GOOD BYE!");
		scan.close();	// closing the scanner
	}
}