package SillyPhrase;

import java.util.ArrayList;

public class TruthAssignment {
	ArrayList<PropositionConstant> propConstant;
	ArrayList<Boolean> booleanVal;
	public TruthAssignment(){
		propConstant = new ArrayList<PropositionConstant>();
		 booleanVal = new ArrayList<Boolean>();
	}

	public void put(PropositionConstant b, boolean c) {
		propConstant.add(b);
		booleanVal.add(c);
	}
	
	public ArrayList<PropositionConstant> getPropConstant(){
		return propConstant;
	}
	
	public ArrayList<Boolean> getBooleanVal(){
		return booleanVal;
	}
}
