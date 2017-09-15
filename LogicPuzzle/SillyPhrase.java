package SillyPhrase;
/**
 * Through a series of methods and objects, a sill phrase is passed into the program and a truth table is made 
 * based on the assigned boolean values.
 * 
 * @author mickey
 *
 */
public class SillyPhrase {
	String combinator;
	boolean basic;
	String SillyConstant;
	SillyPhrase lhv;
	SillyPhrase rhv;
	
	
	/**
	 * The main method.
	 * Constructs all objects and creates a truth table.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropositionConstant a = new PropositionConstant("a");
		PropositionConstant b = new PropositionConstant("b");
		LogicalSentence l1 = new LogicalSentence(a);
		LogicalSentence l2 = new LogicalSentence(b);
		LogicalSentence l3 = new Negation(l1);
		LogicalSentence l4 = new Negation(l3);
		LogicalSentence l5 = new Conjunction(l3, new Negation(l4));
		LogicalSentence l6 = new ExOr(l1,l2);

		TruthAssignment ta1 = new TruthAssignment();
		ta1.put(b,true);
		ta1.put(a, false); 
		
		//EVALUATEEEEEEEEEE
		System.out.println(l5.evaluate(ta1));
		System.out.println(legal("a&"));
		System.out.println(findMatch("a(b(a)", 0));

		String[] pc = {"p","|","q"};
		int variableCount= (pc.length/2)+1;
		truthTable(pc,variableCount);
	}

	/**
	 * Makes a truth table based on an array of strings.
	 * @param pc
	 */
	private static void truthTable(String[] pc, int z) {
		// TODO Auto-generated method stub
		System.out.println(pc[0]+pc[1]+pc[2]+ "\t" + pc[0]+pc[2]);
		for (int i = 0 ; i != (1<<z) ; i++) {
		    String s = Integer.toBinaryString(i);
		    while (s.length() != z) {
		        s = '0'+s;
		    }
		    
		    if(pc[1].equals("&")){
		    	if(s.equals("11")){
		    		System.out.print("T");
		    	}else{
		    		System.out.print("F");
		    	}
		    }
		    
		    else if(pc[1].equals("|")){
		    	if(s.equals("00")){
		    		System.out.print("F"+"\t");
		    	}else{
		    		System.out.print("T"+"\t");
		    	}
		    }
		    
		    System.out.println(s);
		}

	}

	/**
	 * Finds the substring within the innermost parentheses starting from the integer input (i).
	 * Returns the rightmost character within the substring.
	 * 
	 * @param string
	 * @param i
	 * @return string
	 */
	private static int findMatch(String string, int i) {
		// TODO Auto-generated method stub
		String inner = string.substring(i, string.length());
		int lastParens = inner.indexOf(")");
		int rightMost = string.indexOf(inner.substring(lastParens, lastParens+1));
		return rightMost-1;
	}
	
	/**
	 * Checks to see if the input, or string, is a legal sentence or not.
	 * 
	 * @param s
	 * @return boolean
	 */
	private static boolean legal(String s) {		
		//p or q simple sentences
		if(s.equals("")){
			return true;
		}	
					
		//checks if ends with operators
		else if (s.substring(s.length()-1).equals("&") || s.substring(s.length()-1).equals("|")|| s.substring(s.length()-1).equals("~")) {
			return false;
		}

		//biconditional
		else if (s.length() >= 3 && s.substring(s.length()-3).equals("<=>")) {
			return false;
								
		}
		//implies
		else if (s.length() >= 2 && s.substring(s.length()-2).equals("=>")) {
			return false;
		}
					
					
		//checks if it begins with any Logical Operator besides "~"
		else if (s.substring(0,1).equals("&") || s.substring(0,1).equals("|")) {
			return false;
		}
		else if (s.length() >= 2) {
			if (s.substring(0,2).equals("=>")) {
				return false;
			}
		}
		else if (s.length() >= 3) {
			if (s.substring(0,3).equals("<=>")) {
				return false;
				}
		}
		return true;
					
	}
	
	
	/**
	 * Returns retval.
	 * 
	 * @param a
	 * @param b
	 * @return value
	 */
	public static SillyPhrase xor(SillyPhrase a, SillyPhrase b){
		SillyPhrase retval= new SillyPhrase();
		retval.basic = false;
		retval.combinator="^";
		retval.lhv = a;
		retval.rhv = b;
		return retval;
		
	}

}
