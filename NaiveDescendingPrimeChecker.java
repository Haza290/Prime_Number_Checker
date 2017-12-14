/**
 * Checks if number is prime by checking if it is divisible by any number lower than it start from 
 * the number/2 and descending to 2
 * @author Harry
 *
 */
public class NaiveDescendingPrimeChecker implements PrimeChecker {
		
	public boolean checkNumber(int number) {
				
		int i = number/2;
		while(i > 1) {
			// if the remainder is 0 then number isn't prime
			if(number % i == 0) {
				return false;
			}
			i--;
		}
		// if we get here then number is prime
		return true;
	}

}
