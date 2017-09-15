package SillyPhrase;

import java.util.ArrayList;

public class LogicalSentence {
	PropositionConstant constant;
	public LogicalSentence() {
		
	}

	public LogicalSentence(PropositionConstant a){
		
	}
	

	public Boolean evaluate(TruthAssignment ta1) {
		// TODO Auto-generated method stub
		ArrayList<PropositionConstant> propConstant = ta1.getPropConstant();
        ArrayList<Boolean> booleanVal = ta1.getBooleanVal();
        
        for (int i = 0; i < booleanVal.size(); i++)    {
            if (propConstant.get(i).equals(constant))    {
                return booleanVal.get(i);
            }
        }

        return false;
	}
}
