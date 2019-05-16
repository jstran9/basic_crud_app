package tran.example.basicwebapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CalculatorTest {

    @Mock // mocking our calculator object.
    private Calculator calculator;

    @Before // executes before each test.
    public void setup() {
        /**
         * triggers mockito's injection mechanism to inject the mock into our "calculator" field/data member.
         * we need to also make make sure we mock the "abs" method.
         */
        MockitoAnnotations.initMocks(this);
//        calculator = new Calculator(); // initMocks is essentially doing this line for us!
    }

    @Test
    public void testAbs() {
        when(calculator.abs(-20)).thenReturn(20); // mocks a working calculator.
        assertEquals(20, calculator.abs(-20)); // assuming that "when" mock is done properly then we would return the expected 20.
//        assertEquals(1, calculator.abs(20));
//        assertEquals(4, calculator.abs(-4));
    }
}
