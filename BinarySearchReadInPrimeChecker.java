import java.util.ArrayList;

public class BinarySearchReadInPrimeChecker extends ReadInPrimes implements PrimeChecker {
	
	ArrayList<Integer> primes = readInMillionArrayListPrimes();

	public boolean checkNumber(int number) {
		
		int lower = 0;
		int upper = primes.size() - 1;
		
		int middle;
		
		while(lower <= upper) {
			
			// Work out middle of lower and upper
			middle = (lower + upper)/2;
			//System.out.println("Upper: " + upper + " Lower: " + lower + " middle: " + middle + " middle prime: " + primes.get(middle));
			
			// if middle prime is smaller than our number then set lower bound to middle + 1
			if(primes.get(middle) < number){
				lower = middle + 1;
			// if middle prime is bigger than our number then set upper bound to middle - 1
			} else if (primes.get(middle) > number) {
				upper = middle - 1;
			// if middle prime isn't bigger or smaller than our number then it is our number so
			// our number IsindexAction prime
			} else {
				return true;
			}
		}
		
		return false;
	}
	
	

}
