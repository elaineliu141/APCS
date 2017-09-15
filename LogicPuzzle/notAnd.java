package SillyPhrase;

/**
 * Returns the nand of two logical sentences and returns a boolean.
 * @author mickey
 *
 */
public class notAnd extends LogicalSentence{

	LogicalSentence left;
	LogicalSentence right;
	public notAnd(LogicalSentence left, LogicalSentence right){
		
		this.left = left;
		this.right = right;
	}
	
	public Boolean evaluate(TruthAssignment t){
		return !(left.evaluate(t)&& right.evaluate(t));
		
	}
}

