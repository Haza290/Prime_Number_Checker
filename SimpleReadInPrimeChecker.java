import java.util.ArrayList;

public class SimpleReadInPrimeChecker extends ReadInPrimes implements PrimeChecker {
	
	ArrayList<Integer> primes;
	
	public SimpleReadInPrimeChecker() {
		primes = readInMillionArrayListPrimes();
	}

	public boolean checkNumber(int number) {
		for (int prime : primes) {
			if (prime == number) {
				return true;
			} else if (prime > number) {
				return false;
			}		
		}
		return false;
	}

}
