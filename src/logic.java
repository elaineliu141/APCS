/**
 * Returns a boolean (true or false) when the parameter called is a legal sentence or not.
 * Returns true if the sentence is "p" or "q"
 * Returns true when sentence contains "&&" "||" with corresponding left and right sides
 * Removes "~" if contained in the string
 * Only inputs are p,q,and r
 * 
 * @author mickey
 * @param s string inputed from the main
 * @return boolean based on the sentence
 */
public class logic {

	public static void main(String[] args) {
		/**
		 * Main method of input
		 */
		// TODO Auto-generated method stub
		//ONLY inputs are lowercase p and q and r
		System.out.println(legalSentence("p|q=>"));
	} 
	
	public static boolean legalSentence(String s){
		/**
		 * Checks to see if the input sentence is legal.
		 */
		
		
		//p or q simple sentences
		if(s.equals("p") || s.equals("q")){
			return true;
		}
		
		else if(s.equals("")){
			return true;
		}	
		
		//checks if ends with operators
		else if (s.substring(s.length()).equals("&") || s.substring(s.length()).equals("|")|| s.substring(s.length()).equals("~")) {
			return false;
		}
		//biconditional
		else if (s.length() >= 3 && s.substring(s.length()-3).equals("<=>")) {
			return false;
					
		}
		//implies
		else if (s.length() >= 2 && s.substring(s.length()-2).equals("=>")) {
			return false;
		}
		
		
		//checks if it begins with any Logical Operator besides "~"
		else if (s.substring(0,1).equals("&") || s.substring(0,1).equals("|")) {
			return false;
		}
		else if (s.length() >= 2) {
				if (s.substring(0,2).equals("=>")) {
					return false;
				}
			}
		else if (s.length() >= 3) {
				if (s.substring(0,3).equals("<=>")) {
					return false;
				}
		}
		/*for (int i = 0; i < s.length()-1; i++)
		{
			if (s.substring(i,i+1).equals("&") || s.substring(i,i+1).equals("|") || s.substring(i,i+1).equals("~")
					|| s.substring(i,i+2).equals("=>") || s.substring(i,i+3).equals("<=>")) {
				if (s.substring(i+1,i+2).equals("&") || s.substring(i+1,i+2).equals("|")) {
					return false;
				}
			}
		}/*
		
		for (int i = 0; i < s.length()-4; i++)
		{
			if (s.substring(i,i+2).equals("=>")) {
				if (s.substring(i+2,i+3).equals("&") || s.substring(i+2,i+3).equals("|")
						|| s.substring(i+2,i+4).equals("=>") || s.substring(i+2,i+5).equals("<=>")) {
					return false;
				}
			}
		}
		for (int i = 0; i < s.length()-5; i++)
		{
			if (s.substring(i,i+3).equals("<=>")) {
				if (s.substring(i+3,i+4).equals("&") || s.substring(i+3,i+4).equals("|")
						|| s.substring(i+3,i+5).equals("=>") || s.substring(i+3,i+6).equals("<=>")) {
					return false;
				}
			}
		}*/
		
		
		int leftHandIndex;
		
		for(int i = 0; i < s.length(); i++){
			if(s.contains("&")){
				leftHandIndex= s.indexOf("&");
				if(s.substring(0, leftHandIndex).equals("p")){
					int rightHandIndex= s.indexOf("&");
					if(s.substring(rightHandIndex+1, s.length()).equals("q") || s.substring(rightHandIndex+1, s.length()).equals("r")){
						return true;
					}
					
					else if(s.substring(rightHandIndex+1, s.length()).contains("~")){
						return true;
					}
					
					else if(s.substring(rightHandIndex+1, s.length()).contains("|")){
						return true;
					}
					else if(s.substring(rightHandIndex+1, s.length()).contains("<=>")){
						return true;
					}
					else if(s.substring(rightHandIndex+1, s.length()).contains("=>")){
						return true;
					}
					
					
					else{
						return false;
					}
				}
				else{
					return false;
				}
					
			}
			
			else if(s.contains("|")){
				leftHandIndex= s.indexOf("|");
				if(s.substring(0, leftHandIndex).equals("p")){
					int rightHandIndex= s.indexOf("|");
					if(s.substring(rightHandIndex+1, s.length()).equals("q") || s.substring(rightHandIndex+1, s.length()).equals("r")){
						return true;
					}
					else if(s.substring(rightHandIndex+1, s.length()).contains("~")){
						return true;
					}
					
					else if(s.substring(rightHandIndex+1, s.length()).contains("&")){
						return true;
					}
					else if(s.substring(rightHandIndex+1, s.length()).contains("<=>")){
						return true;
					}
					else if(s.substring(rightHandIndex+1, s.length()).contains("=>")){
						return true;
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
					
			
			}

			else if(s.contains("~")){
				leftHandIndex= s.indexOf("~");
				if(s.substring(0, leftHandIndex).equals("p")){
					int rightHandIndex= s.indexOf("~");
					if(s.substring(rightHandIndex+1, s.length()).equals("q") || s.substring(rightHandIndex+1, s.length()).equals("r")){
						return false;
					}
					else if(s.substring(rightHandIndex+1, s.length()).contains("&")){
						return true;
					}
					
					else if(s.substring(rightHandIndex+1, s.length()).contains("|")){
						return true;
					}
					else if(s.substring(rightHandIndex+1, s.length()).contains("<=>")){
						return true;
					}
					else if(s.substring(rightHandIndex+1, s.length()).contains("=>")){
						return true;
					}
					
					else{
						return true;
					}
				}
				else{
					return false;
				}
					
			
			}
			else if(s.contains("<=>")){
				leftHandIndex= s.indexOf("~");
				if(s.substring(0, leftHandIndex).equals("p")){
					int rightHandIndex= s.indexOf("<=>");
					if(s.substring(rightHandIndex+1, s.length()).equals("q") || s.substring(rightHandIndex+1, s.length()).equals("r")){
						return true;
					}
					else if(s.substring(rightHandIndex+1, s.length()).contains("~")){
						return true;
					}
					
					else if(s.substring(rightHandIndex+1, s.length()).contains("|")){
						return true;
					}
					else if(s.substring(rightHandIndex+1, s.length()).contains("&")){
						return true;
					}
					else if(s.substring(rightHandIndex+1, s.length()).contains("=>")){
						return true;
					}
					
					else{
						return false;
					}
				}
				else{
					return false;
				}
				
				
			}
			
			else if(s.contains("=>")){
				leftHandIndex= s.indexOf("=>");
				if(s.substring(0, leftHandIndex).equals("p")){
					int rightHandIndex= s.indexOf("=>");
					if(s.substring(rightHandIndex+1, s.length()).equals("q") || s.substring(rightHandIndex+1, s.length()).equals("r")){
						return true;
					}
					else if(s.substring(rightHandIndex+1, s.length()).contains("~")){
						return true;
					}
					
					else if(s.substring(rightHandIndex+1, s.length()).contains("|")){
						return true;
					}
					else if(s.substring(rightHandIndex+1, s.length()).contains("<=>")){
						return true;
					}
					else if(s.substring(rightHandIndex+1, s.length()).contains("&")){
						return true;
					}
					else{
						return false;
					}
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
			
			
		}
		
			
		return false;
		
	}

}













/*if(s.contains("~")){
if(s.substring(0, 1).equals("~")){
	return true;
}
else{
	return false;
}
}*/


/*else if(s.contains("&")){
int leftHandIndex= s.indexOf("&");
if(s.substring(0, leftHandIndex).equals("p")){
	int rightHandIndex= s.indexOf("&");
	if(s.substring(rightHandIndex+1, s.length()).equals("q") || s.substring(rightHandIndex+1, s.length()).equals("r")){
		return true;
	}
	else{
		return false;
	}
		
}
	
else{
	return false;
}
	
}

else if(s.contains("|")){
int leftHandIndex= s.indexOf("|");
if(s.substring(0, leftHandIndex).equals("p")){
	int rightHandIndex= s.indexOf("|");
	if(s.substring(rightHandIndex+1, s.length()).equals("q") || s.substring(rightHandIndex+1, s.length()).equals("r")){
		return true;
	}
	else{
		return false;
	}
		
}
else{
	return false;
}
}

else if(s.contains("=>")){
int leftHandIndex= s.indexOf("=>");
if(s.substring(0, leftHandIndex).equals("p")){
	int rightHandIndex= s.indexOf("=>");
	if(s.substring(rightHandIndex+2, s.length()).equals("q") || s.substring(rightHandIndex+1, s.length()).equals("r")){
		return true;
	}
	else{
		return false;
	}
		
}
else{
	return false;
}
}


else{
return false;
}*/