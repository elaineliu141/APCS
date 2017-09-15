package SillyPhrase;

public class Implication extends LogicalSentence{
	LogicalSentence left, right;
	public Implication() {
		// TODO Auto-generated constructor stub
		
	}
	
	public Implication(LogicalSentence left, LogicalSentence right) {
		// TODO Auto-generated constructor stub
		this.left=left;
		this.right = right;
	}
	
	public Boolean evaluate(TruthAssignment t){
		
		return left.evaluate(t) != right.evaluate(t);
	}
}
