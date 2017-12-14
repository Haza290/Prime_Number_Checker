import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ReadInPrimes has only 1 static member, which is used to read in the first million prime numbers 
 * @author Harry
 *
 */
public class ReadInPrimes {

	/**
	 * Reads in first million primes from txt file and turns data as an array
	 * @return An Array of Integers of the first million primes
	 */
	public static ArrayList<Integer> readInMillionArrayListPrimes() {
		
		System.out.println("starting reading in data");

		//TODO change this to a local address maybe?
		File file = new File("D:\\Users\\Harry\\Desktop\\Frist_Million_Primes.txt");

		ArrayList<Integer> primesArray = new ArrayList<>();
		try (Scanner scanner = new Scanner(file);) {
			while (scanner.hasNextInt()) {
				primesArray.add(scanner.nextInt());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("all data read");
		
		return primesArray;
	}

}
