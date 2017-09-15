package SillyPhrase;

/**
 * Negates a logical sentence and returns a boolean.
 * @author mickey
 *
 */
public class Negation extends LogicalSentence{
	
	LogicalSentence l1;

	public Negation(LogicalSentence l1){
		this.l1 = l1;
	}
	
	public Boolean evaluate(TruthAssignment t){
		return !(l1.evaluate(t));
	}
}
