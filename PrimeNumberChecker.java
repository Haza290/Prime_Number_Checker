import java.util.ArrayList;
import java.io.*;
import org.apache.poi.xssf.usermodel.*;

public class PrimeNumberChecker {

	static int LOWER_BOUND = 2; 		// Smallest number checked if prime (inclusive)
	static int UPPER_BOUND = 15000000; 	// Largest number checked if prime (exclusive)
	
	String XLSX_FILE_PATH = "D:\\Users\\Harry\\Desktop\\test.xlsx";

	public static void main(String[] args) throws Exception {
		new PrimeNumberChecker();
	}

	public PrimeNumberChecker() throws Exception {
		
		// Creates an array of PrimeChecker and adds the different classes to it
		ArrayList<PrimeChecker> primeCheckerClasses = new ArrayList<PrimeChecker>();
		
		primeCheckerClasses.add(new BinarySearchReadInPrimeChecker());
		primeCheckerClasses.add(new NaiveAscendingPrimeChecker());
		primeCheckerClasses.add(new SimpleReadInPrimeChecker());

		// Generate results and upload them to google sheet
		ArrayList<Results> results = runPrimeCheckers(primeCheckerClasses);
		printData(results);
		
	}

	/**
	 * Takes all the prime checker classes, run them from numbers LOWER_BOUND to UPPER_BOUND, recording 
	 * time taken and if that number was prime
	 * 
	 * @param primeCheckerClasses An Array of different prime checker classes
	 * @return An array of results of time taken and if a number was prime from each prime checker classes
	 */
	private ArrayList<Results> runPrimeCheckers(ArrayList<PrimeChecker> primeCheckerClasses) {

		ArrayList<Results> results = new ArrayList<Results>();

		// for each prime checking method check all numbers from 2 to UPPER_BOUND and record there times
		// and if they found the number to be prime
		for (int i = 0; i < primeCheckerClasses.size(); i++) {
			
			// Stores current prime checker class
			PrimeChecker currentPrimeCheckerClass = primeCheckerClasses.get(i);
			
			System.out.println("starting: " + currentPrimeCheckerClass.getClass().getSimpleName());

			// Creates arrays to store time taken to check if number is prime
			// and if they are primes
			ArrayList<Long> times = new ArrayList<Long>();
			ArrayList<Boolean> isPrime = new ArrayList<Boolean>();
			
			// Long totalRunningTime = System.currentTimeMillis();
			Long tenSecondTimmer = System.currentTimeMillis();

			for (int number = LOWER_BOUND; number < UPPER_BOUND; number++) {
								
				Boolean isNumberPrime;
				Long startTime = System.currentTimeMillis(); 					// Starts the timer
				isNumberPrime = currentPrimeCheckerClass.checkNumber(number); 	// Runs the prime checker
				Long endTime = System.currentTimeMillis(); 						// Ends the timer
				Long primeCheckerTime = endTime - startTime;

				// Adds time taken to check prime to times and if it is boolean or not to isPrime
				times.add(primeCheckerTime);
				isPrime.add(isNumberPrime);
				
				if(System.currentTimeMillis() - tenSecondTimmer > 10000L){
					System.out.println("Running: " + currentPrimeCheckerClass.getClass().getSimpleName() 
							+ " on: " + number + " of: " + UPPER_BOUND);
					tenSecondTimmer = System.currentTimeMillis();
				}
				
			}
			// Adds all results from this current Prime Checker Class to a Results class and adds that 
			// to an array
			results.add(new Results(currentPrimeCheckerClass.getClass().getSimpleName(), times, isPrime));
		}

		return results;
	}

	private void updateSpreadSheet(ArrayList<Results> results) throws Exception {
		// Open file, if we can't find file create a new one
		File file = new File(XLSX_FILE_PATH);
		
		XSSFWorkbook workbook;
		// If file exists open it otherwise create a new file
		if(file.isFile() && file.exists()) {
	         System.out.println("file exsits");
	         FileInputStream fIP = new FileInputStream(file);
	         workbook = new XSSFWorkbook(fIP);
		} else {
			System.out.println("file doesn't exsits");
			workbook = new XSSFWorkbook(); 
	        workbook.createSheet();
		}
		
		// Creates the number axis between the 2 bounds
		for (int i = LOWER_BOUND; i < UPPER_BOUND; i++) {
			
			if (workbook.getSheetAt(0).getRow(i) == null) {
				workbook.getSheetAt(0).createRow(i);
			}
			workbook.getSheetAt(0).getRow(i).createCell(0).setCellValue(i);
		}
		
        FileOutputStream outFile =new FileOutputStream(new File("C:\\Anuj\\Data\\Data.xlsx"));
        workbook.write(outFile);
        outFile.close();
	}
	
	/**
	 * Prints summary of results, used for debugging
	 * @param results
	 */
	private void printData(ArrayList<Results> results) {
		
		for (int i = 0; i < results.size(); i++) {
			Long totalTime = 0L;
			int totalNumPrimes = 0;
			
			ArrayList<Long> times = results.get(i).getTimes();
			ArrayList<Boolean> primes = results.get(i).getIsPrime();
			
			for (Long time : times) {
				totalTime += time;
			}
			for (boolean isPrime : primes) {
				if(isPrime) {
					totalNumPrimes ++;
				}
			}
		}
	}
}
