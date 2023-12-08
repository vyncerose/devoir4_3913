package currencyConverter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CurrencyTest {

    // BOITE NOIRE
    @ParameterizedTest
    @ValueSource(doubles = {-1, 0, 1, 500000, 999999, 1000000, 1000001})
    public void testBoundaryValuesCurrency(double amount) {
        double exchangeRate = 1.5;
        boolean isAmountValid = amount >= 0 && amount <= 1000000;
        double converted = Currency.convert(amount,exchangeRate);

        if (isAmountValid) {
            assertTrue(converted == amount * exchangeRate, "Conversion failed for valid amount: " + amount);
        } else {
            assertEquals(0, converted, 0.0, "Conversion should fail for invalid amount: " + amount);
        }
    }
    // Tests ici

    // BOITE BLANCHE
    @Test
    void testConvert(){
        Double result = Currency.convert(100.0, 0.5);
        assertEquals(50.0, result);
    }
}
