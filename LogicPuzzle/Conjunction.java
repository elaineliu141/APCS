package SillyPhrase;

public class Conjunction extends LogicalSentence{

	LogicalSentence left;
	LogicalSentence right;
	public Conjunction(LogicalSentence left, LogicalSentence right){
		//super(logic);
		this.left = right;
		this.right = right;
	}
	
	public Boolean evaluate(TruthAssignment t){
		if(left.evaluate(t)==true && right.evaluate(t)==true){
			return true;
		}
		
		else{
			return false;
		}
		
	}
}

