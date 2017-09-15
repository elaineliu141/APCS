package SillyPhrase;

public class Disjunction extends LogicalSentence{


	LogicalSentence l1, r1;
	public Disjunction() {
		// TODO Auto-generated constructor stub
		
	}
	
	public Disjunction(LogicalSentence l1, LogicalSentence r1) {
		// TODO Auto-generated constructor stub
		this.l1=l1;
		this.r1 = r1;
	}
	
	public Boolean evaluate(TruthAssignment t){
		
		if(l1.evaluate(t)==false && r1.evaluate(t)==false){
			return false;
		}
		else{
			return true;
		}
	}
}
