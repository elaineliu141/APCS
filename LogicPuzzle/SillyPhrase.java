package SillyPhrase;

public class SillyPhrase {
	String combinator;
	boolean basic;
	String SillyConstant;
	SillyPhrase lhv;
	SillyPhrase rhv;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropositionConstant a = new PropositionConstant("a");
		PropositionConstant b = new PropositionConstant("b");
		LogicalSentence l1 = new LogicalSentence(a);
		LogicalSentence l2 = new LogicalSentence(b);
		LogicalSentence l3 = new Negation(l1);
		LogicalSentence l4 = new Negation(l3);
		LogicalSentence l5 =  new Conjunction(l3, new Negation(l4));

		TruthAssignment ta1 = new TruthAssignment();
		ta1.put(b,true);
		ta1.put(a, false); 
		//System.out.println(l5.evaluate(ta1));
		System.out.println(legal("a&"));
		System.out.println(findMatch("a(((b)))", 2));

		String[] pc = {"p"};
		truthTable(pc);
		LogicalSentence l6 = new ExOr(l1,l2);

	}
	
	private static void truthTable(String[] pc) {
		// TODO Auto-generated method stub
		
	}

	private static int findMatch(String string, int i) {
		// TODO Auto-generated method stub
		String findParens = string.substring(i);
		String middle = findParens.substring(findParens.indexOf(")")-1, findParens.indexOf(")"));
		return findParens.indexOf(middle);
	}
	
	private static boolean legal(String s) {
		
		//public static boolean legalSentence(String s){
		/**
		 * Checks to see if the input sentence is legal
		 * 
		*/
			
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
	
	public static SillyPhrase xor(SillyPhrase a, SillyPhrase b){
		SillyPhrase retval= new SillyPhrase();
		retval.basic = false;
		retval.combinator="^";
		retval.lhv = a;
		retval.rhv = b;
		return retval;
		
	}

}
