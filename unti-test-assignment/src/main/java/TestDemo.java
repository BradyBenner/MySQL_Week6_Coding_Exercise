//Brady Benner
//12/18/2021
//Ref: https://github.com/DrOldGuy/cohort_915_mysql-week-6.git

import java.util.Random;
import com.google.common.annotations.VisibleForTesting;
//This is the class that is under test for the assignment.  
//It has a method to add 2 ints and another method to return the square of a
//randomly attained int
public class TestDemo {
	
	//This method checks if two parameters a,b are negative, either are negative 
	//it throws an exception
	//if both are not negative, the method returns a+b
	//this method is the main method in the first test of the homework
	public int addPositive(int a, int b) {
		
		    if (a < 0 || b < 0) {
		      throw new IllegalArgumentException("Each parameter must be positive");
		    } else {

		    return a + b;
		    }
		
	}
	
	//this method gets a random int a from getRandomInt  and then returns the a*a
	//this is the second method to be tested
	public int randomNumberSquared() {
		
		int a = getRandomInt();
		return a*a;
	}
//this method gets a random int and returns it.  This method is used to show how
//mocking methods works since we cannot predict what the actual int returned will be
//thus, in order to test addPositive, we must override that method getting the int from this method
	@VisibleForTesting
	int getRandomInt() {
		Random random = new Random();
		return random.nextInt(10) + 1;

	}
	
	

}
