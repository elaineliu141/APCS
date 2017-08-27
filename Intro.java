/**
 * A set of 4 methods.
 * 
 * @author mickey
 *
 */
public class Intro {

	public int ret(){
		/**
		 * Returns 17
		 */
		return 17;
	}
	
	public boolean logic(boolean a, boolean b, boolean c){
		/**
		 * Returns true or false based on 3 booleans.
		 * @param: a, b, c
		 */
		if(a&&b&&c){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public String stars(int n){
		/**
		 * Returns a format of stars with n rows and n stars per row (based on the input integer n).
		 * @param: n
		 */
		for(int i=1; i<=n; i++){
			for(int k=1; k<=i; k++){
				System.out.print("*");
			}
			System.out.println("");
			}
		return (String)null;
	}
	
	public int coins(int n){
		/**
		 * Returns an amount that can be exchanged in 5 cents and 2 cents based on the input.
		 * @param: n
		 * 
		 */  
		//His friend told him that he wanted to exchange for 5 cent and 2 cent coins.
		//Jim wants to exchange as few coins as possible, so print the minimum number of 5c and 2c coins Jim needs to use.  
		//If the exchange isn’t possible, return -1.  The method stub is included on the following page.

		int fiveCents;
		int twoCents;
		int fiveCount;
		
		
		//loops through multiples of 5 within number
		for(int multiple = n/5; multiple >=1 ;multiple--){
			if((n-(5*multiple))%2==0){
				twoCents = (n-(5*multiple))/2;
				fiveCount= multiple;
				return multiple + twoCents;
			}
		}
		
		if( n%2==0){
			twoCents = n/2;
			return twoCents;
		}
		return -1;
		
	}

}
