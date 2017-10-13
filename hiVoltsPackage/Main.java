package hiVoltsPackage;

public class Main
{
	public static void main(String[] args)
	{
		//Standard number of fences and mhos.
		int fences = 20;
		int mhos = 12;
		
		//Creates an object and inputs number of mhos and fences.
		Frame hivolts = new Frame();
		hivolts.setUp(mhos, fences);
		hivolts.setVisible(true);
	}
}