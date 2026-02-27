package healthcalc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        @ParameterizedTest(name = "Cálculo IBW para valores estándar para hombre altura: {0} cm")
        @ValueSource(doubles = {165, 170, 180, 190, 201})
        void testIbwHombreValido(double height) throws InvalidHealthDataException {
            double expectedIbw = (height - 100) - ((height - 150) / 4.0);

            double result = healthCalc.ibw(height);

            assertEquals(expectedIbw, result, 0.01);
        }

        @Test
        @ParameterizedTest(name = "Cálculo IBW para valores estándar para mujeres altura: {0} cm")
        @ValueSource(doubles = {145, 155, 168, 180, 200})
        void testIbwMujerValido(double height) throws InvalidHealthDataException {
            double expectedIbw = (height - 100) - ((height - 150) / 2.5);

            double result = healthCalc.ibw(height);

            assertEquals(expectedIbw, result, 0.01);
        }

        @Test
        @DisplayName("Lanzar error cuando altura negativa")
        void testIbwAlturaNegativa() {
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.ibw(-100.0));
        }

        @Test
        @DisplayName("Lanzar error cuando altura cero")
        void testIbwAlturaCero() {
            assertThrows(InvalidHealthDataException.class, () -> healthCalc.ibw(0.0));
        }   
    }
}
