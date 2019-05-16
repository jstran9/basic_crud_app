package tran.example.basicwebapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CarTest {

    @Mock
    private Engine engine;

    @InjectMocks // this will inject the above mock (engine) into our car object.
    private Car car;

    @Before
    public void setup() {
        // before each test we will inject the engine into the car.
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWarning() {
        when(engine.getRpm()).thenReturn(6000);
        car.accelerate();

        assertEquals("Slow Down!", car.getWarningMessage());
    }
}
