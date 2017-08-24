/**
 * A set of 4 methods that all return integers.
 * 
 * @author mickey
 *
 */
public class Intro {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(coins(27));
	}
	
	//returns 17
	public int ret(){
		/**
		 * Returns 17
		 */
		return 17;
	}
	
	public int logic(boolean a, boolean b, boolean c){
		/**
		 * Returns true or false based on 3 booleans.
		 * @param: boolean a, boolean b, boolean c
		 */
		if(a&&b&&c){
			return 1;
		}
		else{
			return 0;
		}
		
	}
	
	public static int stars(int n){
		/**
		 * Returns a format of stars based on the input integer n.
		 * @param: int n
		 */
		for(int i=1; i<=n; i++){
			for(int k=1; k<=i; k++){
				System.out.print("*");
			}
			System.out.println("");
			}
		return 0;
	}
	
	public static String coins(int n){
		/**
		 * Returns an amount in 5 cents and 2 cents based on the input.
		 * @param: int n
		 * 
		 */  
		//His friend told him that he wanted to exchange for 5 cent and 2 cent coins.
		//Jim wants to exchange as few coins as possible, so print the minimum number of 5c and 2c coins Jim needs to use.  
		//If the exchange isn’t possible, return -1.  The method stub is included on the following page.

		int fiveCents;
		int twoCents;
		int centsRemainder=n%5;
		
		if(n%5 == 0){
			return "You can exchange " + n/5 + " 5-cents.";
		}
		else if(n%2==0){
			return "You can exchange " + n/2 + " 2-cents.";
		}
		
		else if(n<5 && n==2){
			return "You can exchange " + 1 + "2-cents.";
		}
		
		else if(n>5){
			fiveCents=n/5;
			centsRemainder=n%5;
			
			if(centsRemainder%2==0){
				twoCents=centsRemainder/2;
				return "You can exchange " + fiveCents + " 5-cents and " + twoCents + " 2-cents.";
			}
			else{
				return "Sorry! You can not exchange cents.";
			}
		}
		
		return "Sorry! You can not exchange cents.";
		
	}

}
