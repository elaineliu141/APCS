package SillyPhrase;

/**
 * Returns the conjunction of two logical sentences and returns a boolean.
 * @author mickey
 *
 */
public class Conjunction extends LogicalSentence{

	LogicalSentence left;
	LogicalSentence right;
	public Conjunction(LogicalSentence left, LogicalSentence right){
		
		this.left = left;
		this.right = right;
	}
	
	public Boolean evaluate(TruthAssignment t){
		return (left.evaluate(t)&& right.evaluate(t));
		
	}
}

