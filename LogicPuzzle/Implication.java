package SillyPhrase;

public class Implication extends LogicalSentence{
	LogicalSentence left, right;
	public void Implication() {
		// TODO Auto-generated constructor stub
		
	}
	
	public Implication(LogicalSentence l1, LogicalSentence l2) {
		// TODO Auto-generated constructor stub
		this.left=left;
		this.right = right;
	}
	
	public Boolean evaluate(TruthAssignment t){
		
		return left.evaluate(t) != right.evaluate(t);
	}
}
