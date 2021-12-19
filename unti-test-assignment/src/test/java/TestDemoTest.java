//Brady Benner
//12/18/2021
//ref: https://github.com/DrOldGuy/cohort_915_mysql-week-6.git

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//This class contains tests for methods in the TestDemo class
class TestDemoTest {
	private TestDemo testDemo;
	//This creates a new testDemo object before each test
	@BeforeEach
	void setUp() throws Exception {
		 testDemo = new TestDemo();
	}

	//This method tests the addPositive method using a list of parameters
	//if either of the two ints is negative, we expect an exception
	//if both ints passed via parameters are not negative, we expect the sum of them back
	//the expectException parameter is true if we expect an exception and false if we do not 
	// due to at least one int being negative
	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, Boolean expectException) {
		if(!expectException) {
			  assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
			} else {
				assertThatThrownBy(() -> 
			    testDemo.addPositive(a, b))
			        .isInstanceOf(IllegalArgumentException.class);

			}

	}
	
	//this method creates the argument stream to test assertThatTwoPositiveNumbersAreAddedCorrectly
	static Stream<Arguments> argumentsForAddPositive() {
	    
	    return Stream.of(
	        arguments(2, 4, 6, false),
	        arguments(-2, 4, 0, true),
	        arguments(2, -4, 0, true),
	        arguments(0, 0, 0, false),
	        arguments(1,1,2,false)
	    );
	}
	
	//this method tests the randomNumberSquared method in the main class
	//it must mock getRandomInt because we cannot predict what number will be returned to
	//the randomNumberSquared method and therefore we cannot test if that method returns 
	//the exact correct value
	//
	//This method creates a TestDemo spy object to test the class
	//then it sets up the redirection around the getRandomInt mocked class
	//then using the fake random int, it gets the squared value  and tests that from expected
	//This test method runs doReturn several times with different mocked fake values.
	//parameterized arguments as used above are better than the below using several doReturns
	// (for several reasons) but I wanted to see several values without changing the assignment
	@Test
	void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
		doReturn(0).when(mockDemo).getRandomInt();
		int zeroSquared = mockDemo.randomNumberSquared();
		assertThat(zeroSquared).isEqualTo(0);
		doReturn(-6).when(mockDemo).getRandomInt();
		int negSquared = mockDemo.randomNumberSquared();
		assertThat(negSquared).isEqualTo(36);
	}

}
