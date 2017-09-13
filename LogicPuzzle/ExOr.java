package SillyPhrase;

public class ExOr extends LogicalSentence{
	LogicalSentence left, right;
	public void ExOr() {
		// TODO Auto-generated constructor stub
		
	}
	
	public ExOr(LogicalSentence l1, LogicalSentence l2) {
		// TODO Auto-generated constructor stub
		this.left=left;
		this.right = right;
	}
	
	public Boolean evaluate(TruthAssignment t){
		
		return left.evaluate(t) != right.evaluate(t);
	}
}
