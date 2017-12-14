import java.util.ArrayList;


public class Results {

	private String methodName;
	private ArrayList<Long> times;
	private ArrayList<Boolean> isPrime;
	
	public Results(String methodName, ArrayList<Long> times, ArrayList<Boolean> isPrime) {
		this.methodName = methodName;
		this.times = times;
		this.isPrime = isPrime;
	}
	
	public String getMethodName() {
		return methodName;
	}
	public ArrayList<Long> getTimes() {
		return times;
	}
	public ArrayList<Boolean> getIsPrime() {
		return isPrime;
	}
	
}
