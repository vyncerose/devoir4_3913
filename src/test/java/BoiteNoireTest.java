import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import currencyConverter.Currency;
import currencyConverter.MainWindow;

import java.util.ArrayList;

public class BoiteNoireTest {

    ArrayList<Currency> currencies = Currency.init();

    private static final double TEST_AMOUNT = 100.0;

    @Test
    public void testConvertUSDtoCAD() {
        double converted = MainWindow.convert("US Dollar", "Canadian Dollar",currencies, TEST_AMOUNT);
        assertTrue(converted > 0, "Conversion failed from US Dollar to Canadian Dollar");
    }

    @Test
    public void testConvertUSDtoGBP() {
        double converted = MainWindow.convert("US Dollar", "British Pound",currencies, TEST_AMOUNT);
        assertTrue(converted > 0, "Conversion failed from US Dollar to British Pound");
    }

    @Test
    public void testConvertUSDtoEUR() {
        double converted = MainWindow.convert("US Dollar", "Euro",currencies, TEST_AMOUNT);
        assertTrue(converted > 0, "Conversion failed from US Dollar to Euro");
    }

    @Test
    public void testConvertUSDtoCHF() {
        double converted = MainWindow.convert("US Dollar", "Swiss Franc",currencies, TEST_AMOUNT);
        assertTrue(converted > 0, "Conversion failed from US Dollar to Swiss Franc");
    }

    @Test
    public void testConvertUSDtoAUD() {
        double converted = MainWindow.convert("US Dollar", "Australian Dollar",currencies, TEST_AMOUNT);
        assertTrue(converted > 0, "Conversion failed from US Dollar to Australian Dollar");
    }

    @Test
    public void testInvalidCurrency() {
        String validCurrency = "US Dollar";
        String invalidCurrency = "Japanese Yen";

        double amount = 100;
        double convertedToInvalid = MainWindow.convert(validCurrency, invalidCurrency, currencies, amount);
        assertEquals(0, convertedToInvalid, 0.0, "Conversion should fail to " + invalidCurrency);

        double convertedFromInvalid = MainWindow.convert(invalidCurrency, validCurrency,currencies, amount);
        assertEquals(0, convertedFromInvalid, 0.0, "Conversion should fail from " + invalidCurrency);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, 0, 1, 500000, 999999, 1000000, 1000001})
    public void testBoundaryValues(double amount) {
        String validCurrency1 = "US Dollar";
        String validCurrency2 = "Euro";

        boolean isAmountValid = amount >= 0 && amount <= 1000000;
        double converted = MainWindow.convert(validCurrency1, validCurrency2, currencies, amount);

        if (isAmountValid) {
            assertTrue(converted >= 0, "Conversion failed for valid amount: " + amount);
        } else {
            assertEquals(0, converted, 0.0, "Conversion should fail for invalid amount: " + amount);
        }
    }

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
}
