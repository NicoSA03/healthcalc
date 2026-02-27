package healthcalc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import healthcalc.exceptions.InvalidHealthDataException;

/**
 * Tests for the HealthCalc interface.
 * 
 * Use the AAA pattern (Arrange, Act, Assert) for the tests.
 * 
 * @author NicoSA
 */
@DisplayName("Tests para la métrica de peso ideal IBW.")
public class IBWTest {

    private HealthCalc healthCalc;

    @BeforeEach
    void setUp() {
        healthCalc = new HealthCalcImpl();
    }
    
    @Nested
    @DisplayName("Métrica del IBW")
    class IBWMetricTests {

        @Test
        @DisplayName("Cálculo de IBW con valores estándar válidos para hombres")
        void testIbwValido() throws InvalidHealthDataException {
            double height = 175;
            double expectedIbw = (175 - 100) - ((175 - 150) / 4.0);

            double result = healthCalc.ibw(height);

            assertEquals(expectedIbw, result, 0.01);
        }

        @Test
        @DisplayName("Cálculo de IBW con valores estándar válidos para mujeres")
        void testIbwMujerValido() throws InvalidHealthDataException {
            double height = 165;
            double expectedIbw = (165 - 100) - ((165 - 150) / 2.5);

            double result = healthCalc.ibw(height);

            assertEquals(expectedIbw, result, 0.01);
           
        }
    }
}
