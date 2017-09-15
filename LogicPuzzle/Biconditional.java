package SillyPhrase;

/**
 * Returns the biconditional of two logical sentences.
 * @author mickey
 *
 */
public class Biconditional extends LogicalSentence{
	LogicalSentence left;
	LogicalSentence right;
	public Biconditional(LogicalSentence left, LogicalSentence right){
		
		this.left = left;
		this.right = right;
	}
	
	public Boolean evaluate(TruthAssignment t){
		return (left.evaluate(t)&& right.evaluate(t));
		
	}
}
