package SillyPhrase;

public class Disjunction extends LogicalSentence{


	LogicalSentence left, right;
	public void Disjunction() {
		// TODO Auto-generated constructor stub
		
	}
	
	public Disjunction(LogicalSentence l1, LogicalSentence l2) {
		// TODO Auto-generated constructor stub
		this.left=left;
		this.right = right;
	}
	
	public Boolean evaluate(TruthAssignment t){
		
		if(left.evaluate(t)==false &&right.evaluate(t)==false){
			return false;
		}
		else{
			return true;
		}
	}
}
