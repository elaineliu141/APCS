import java.util.ArrayList;


/**
 * Implementation of functions useful for Djistra's algorithm.
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
	 * @return
	 */
	int ret17() {
		  ArrayList<Integer> retval = new ArrayList<Integer>();
		  retval.add(17);
		  int val = 17;
		  return val;
	}
	
	/**
	 * Returns the first element of the ArrayList.
	 * @param al
	 * @return
	 */
	Integer zerothElement(ArrayList<Integer> al){
		  return al.get(0);
		}

	/**
	 * Returns true if the integer input is contained in the second array.
	 * @param a
	 * @param ar
	 * @return
	 */
	boolean contains(int a, int[] ar){
		  for(int e:ar){
		    if(e==a){
		      return true;
		    }
		  }
		  return false;
		}

	/**
	 * Returns true if the array contains any other number in the second array.
	 * @param a
	 * @param b
	 * @return boolean
	 */
	boolean anyContains(int[] a, int[] b){
		  for(int e: a){
		    for(int x: b){
		      if(e == x){
		        return true;
		      }
		    }
		  }
		  return false;
		}

	/**
	 * Returns the union of two arrays.
	 * @param a
	 * @param b
	 * @return array union of two arrays
	 */
	void union(int[] a, int[] b){
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
	 * @param a
	 * @param b
	 * @return array intersection of two arrays
	 */
	void intersect(int[] a, int[] b){
		  int length = 0;
		  for (int i : a) { 
		    for (int k : b) {
		      if (i == k) {
		        length++;
		      }
		    }
		  }
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
	 * @param a
	 * @param b
	 * @param c
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
	 * Adds an edge to the existing ArrayList of ArrayLists of Object.
	 * @param graph
	 * @param a
	 * @param b
	 * @param c
	 * @return graph arraylist of arraylists of objects
	 */
	ArrayList<ArrayList<Object>> addEdge(ArrayList<ArrayList<Object>> graph, int a, int b, int c){
		  ArrayList<Object> array = new ArrayList<Object>();
		  array.add(a);
		  array.add(b);
		  array.add(c);
		  graph.add(array);
		  return graph;
		}

	/**
	 * Returns an arraylist of integers with the "start" integer set to 0 and the rest set to the max int number.
	 * @param n
	 * @param start
	 * @return array arraylist with set numbers
	 */
	ArrayList<Integer> setInfinity(int n, int start){
		  ArrayList<Integer> array = new ArrayList<Integer>();
		  for(int i = 0; i< n; i++){
		    array.add(1);
		  }
		  for(int i = 0; i < array.size(); i++){
		    array.set(i,2147483647);
		  }
		  array.set(start,0);
		  return array;
		  
		}



}
