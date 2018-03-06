import java.util.ArrayList;


/**
 * Implementation of functions useful for Dijkstra's algorithm.
 * @author mickey
 *
 */
public class setImplementation{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Test Objects
		setImplementation obj = new setImplementation();
		int[] array = {3,1,4,1,5,17};

		ArrayList<Integer> start = new ArrayList<Integer>(3);
		ArrayList<ArrayList<Integer>> val= new ArrayList<ArrayList<Integer>>();
		start.add(3);

		int[] secArray = {2,4,5,1};


		//Returns true if 17 is included in the first array.
		System.out.println(obj.contains(obj.ret17(), array));


		//Returns true if any number in array corresponds with second array
		System.out.println(obj.anyContains(array, secArray));

		//Returns the union and intersection of the arrays.
		obj.union(array,secArray);
		System.out.println();
		obj.intersect(array,secArray);
		System.out.println();

		//Adds the integers to an arraylist
		obj.listify(3, 4, 1);
		System.out.println();


		//Implements Set Infinity
		System.out.println(obj.setInfinity(obj.zerothElement(start), 2));

	}

	/**
	 * Returns 17.
	 * @return integer 17
	 */
	int ret17() {
		ArrayList<Integer> retval = new ArrayList<Integer>();
		retval.add(17);
		int val = 17;
		//return 17
		return val;
	}

	/**
	 * Returns the first element of the ArrayList.
	 * @param al ArrayList 
	 * @return the zeroth element of the arraylist
	 */
	Integer zerothElement(ArrayList<Integer> al){
		return al.get(0);
	}

	/**
	 * Returns true if the integer input is contained in the second array.
	 * @param a intersected integer
	 * @param ar array
	 * @return boolean (T || F)
	 */
	boolean contains(int a, int[] ar){
		for(int e:ar){
			if(e==a){
				//if contained
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if the array contains any other number in the second array.
	 * @param a array 
	 * @param b array
	 * @return boolean (T || F)
	 */
	boolean anyContains(int[] a, int[] b){
		for(int e: a){
			for(int x: b){
				if(e == x){
					//if contained
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Returns the union of two arrays.
	 * @param a array 
	 * @param b array
	 * @return array union of two arrays
	 */
	void union(int[] a, int[] b){
		//Adding every element to the initialized array
		int[] arry= new int[a.length+b.length];
		for(int i = 0; i< a.length; i++){
			arry[i] = a[i];

		}

		for(int i = 0; i< b.length; i++){
			arry[i+a.length] = b[i];

		}
		for(int e: arry){
			System.out.print(e + " ");
		}
	}

	/**
	 * Returns the intersection of two arrays.
	 * @param a array
	 * @param b array
	 * @return array intersection of two arrays
	 */
	void intersect(int[] a, int[] b){
		//determining the length of the intersection
		int length = 0;
		for (int i : a) { 
			for (int k : b) {
				if (i == k) {
					length++;
				}
			}
		}

		//placing values into initialized array
		int[] newArr = new int[length]; 
		int counter = 0;
		for (int i : a) { 
			for (int k : b) {
				if (i == k) { 
					newArr[counter] = i;
					counter++;
				}
			}
		} 
		for(int e: newArr){
			System.out.print(e + " ");
		}
	}

	/**
	 * Adds certain elements to an ArrayList.
	 * @param a integer
	 * @param b integer
	 * @param c integer
	 * @return arrayList with elements added in
	 */
	void listify(int a, int b, int c){
		ArrayList<Object> retval = new ArrayList<Object>();
		retval.add(a);
		retval.add(b);
		retval.add(c);

		for(Object e: retval){
			System.out.print(e + " ");
		}
	}

	/**
	 * Adds an edge to the existing ArrayList of ArrayLists of Objects.
	 * @param graph ArrayList of ArrayLists of Object
	 * @param a integer
	 * @param b integer
	 * @param c integer
	 * @return graph ArrayList of ArrayLists of objects
	 */
	ArrayList<ArrayList<Object>> addEdge(ArrayList<ArrayList<Object>> graph, int a, int b, int c){
		ArrayList<Object> array = new ArrayList<Object>();
		//adds elements
		array.add(a);
		array.add(b);
		array.add(c);
		graph.add(array);
		return graph;
	}

	/**
	 * Returns an ArrayList of integers with the "start" integer set to 0 and the rest set to the max int number.
	 * @param n length of array
	 * @param start start integer
	 * @return array ArrayList with set numbers
	 */
	ArrayList<Integer> setInfinity(int n, int start){
		ArrayList<Integer> array = new ArrayList<Integer>();
		for(int i = 0; i< n; i++){
			array.add(1);
		}

		//Sets the rest of the elements to the maximum integer value
		for(int i = 0; i < array.size(); i++){
			array.set(i,2147483647);
		}
		array.set(start,0);
		return array;

	}
	/**
	 * Collects the first two nodes of each ArrayList and organizes them together.
	 * @param edgelist input ArrayLists of ArrayLists of Ints
	 * @return ArrayList of collected nodes
	 */
	ArrayList<Integer> collectNodes(ArrayList<ArrayList<Integer>> edgelist){
		ArrayList<Integer> retval = new ArrayList<Integer>();
		for(ArrayList<Integer> arry: edgelist){
			retval.add(arry.get(0));
			retval.add(arry.get(1));
		}

		//Removing repeated elements
		for(int a = 1; a< retval.size()-1;a++){
			if(retval.get(a) == retval.get(a +1)){
				retval.remove(a);
			}
		}

		for(int k = 1; k<retval.size()-1; k++){
			if(retval.get(k) == retval.get(retval.size()-1)){
				retval.remove(retval.size()-1);
			}
		}

		return retval;

	}
	/**
	 * Return the String at the index of the smallest integer for which the corresponding boolean is false.
	 * @param visited boolean array
	 * @param distance integer array
	 * @param nodeName string array
	 * @return the element of the satisfied condition
	 */
	String nearesUnvisitedNode(boolean[] visited, int[] distance, String[] nodeName){
		int index= 0;
		for(int i: distance){
			if(visited[i-1] == false){
				index = i;
				break;
			}
		}

		//Checks if the boolean array has only false elements
		for(boolean i : visited){
			if(i == false){
				return nodeName[index-1];
			}

		}
		return "";

	}


}
