package SillyPhrase;

import java.util.ArrayList;

public class TruthAssignment {
	ArrayList<PropositionConstant> propConstant = new ArrayList<PropositionConstant>();
	ArrayList<Boolean> booleanVal = new ArrayList<Boolean>();
	public TruthAssignment(){
		
	}

	public void put(PropositionConstant b, boolean c) {
		propConstant.add(b);
		booleanVal.add(c);
	}
	
	public boolean get(PropositionConstant a){
		return booleanVal.get(0);
	}
}
