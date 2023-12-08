package currencyConverter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class MainWindowTest {

    // BOITE NOIRE
    ArrayList<Currency> BNcurrencies = Currency.init();

    private static final double TEST_AMOUNT = 100.0;

    @Test
    public void testConvertUSDtoCAD() {
        double converted = MainWindow.convert("US Dollar", "Canadian Dollar",BNcurrencies, TEST_AMOUNT);
        assertTrue(converted > 0, "Conversion failed from US Dollar to Canadian Dollar");
    }

    @Test
    public void testConvertUSDtoGBP() {
        double converted = MainWindow.convert("US Dollar", "British Pound",BNcurrencies, TEST_AMOUNT);
        assertTrue(converted > 0, "Conversion failed from US Dollar to British Pound");
    }

    @Test
    public void testConvertUSDtoEUR() {
        double converted = MainWindow.convert("US Dollar", "Euro",BNcurrencies, TEST_AMOUNT);
        assertTrue(converted > 0, "Conversion failed from US Dollar to Euro");
    }

    @Test
    public void testConvertUSDtoCHF() {
        double converted = MainWindow.convert("US Dollar", "Swiss Franc",BNcurrencies, TEST_AMOUNT);
        assertTrue(converted > 0, "Conversion failed from US Dollar to Swiss Franc");
    }

    @Test
    public void testConvertUSDtoAUD() {
        double converted = MainWindow.convert("US Dollar", "Australian Dollar",BNcurrencies, TEST_AMOUNT);
        assertTrue(converted > 0, "Conversion failed from US Dollar to Australian Dollar");
    }

    @Test
    public void testInvalidCurrency() {
        String validCurrency = "US Dollar";
        String invalidCurrency = "Japanese Yen";

        double amount = 100;
        double convertedToInvalid = MainWindow.convert(validCurrency, invalidCurrency, BNcurrencies, amount);
        assertEquals(0, convertedToInvalid, 0.0, "Conversion should fail to " + invalidCurrency);

        double convertedFromInvalid = MainWindow.convert(invalidCurrency, validCurrency,BNcurrencies, amount);
        assertEquals(0, convertedFromInvalid, 0.0, "Conversion should fail from " + invalidCurrency);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, 0, 1, 500000, 999999, 1000000, 1000001})
    public void testBoundaryValues(double amount) {
        String validCurrency1 = "US Dollar";
        String validCurrency2 = "Euro";

        boolean isAmountValid = amount >= 0 && amount <= 1000000;
        double converted = MainWindow.convert(validCurrency1, validCurrency2, BNcurrencies, amount);

        if (isAmountValid) {
            assertTrue(converted >= 0, "Conversion failed for valid amount: " + amount);
        } else {
            assertEquals(0, converted, 0.0, "Conversion should fail for invalid amount: " + amount);
        }
    }

    // BOITE BLANCHE
    @Test
    void testConvertWithValidCurrencies() {
        ArrayList<Currency> currencies = Currency.init(); // currencies.size() > 0
        double result = MainWindow.convert("US Dollar", "Euro", currencies, 100.0); // currency1 et currency2 appartiennent à currencies
                                                                                                               // shortNameCurrency2 n'est pas nul
        assertEquals(93.0, result); 
    }

    @Test
    void testConvertWithInvalidCurrencies(){
        ArrayList<Currency> currencies = Currency.init();
        double result;
        // currency1 invalide
        result = MainWindow.convert("US Dollar", "Currency 2", currencies, 100.0);
        assertEquals(0.0, result);
        // currency2 invalide
        result = MainWindow.convert("Currency 1", "US Dollar", currencies, 100.0);
        assertEquals(0.0, result);
        // currency1 et currency2 invalides
        result = MainWindow.convert("Currency 1", "Currency 2", currencies, 100.0);
        assertEquals(0.0, result);
    }

    @Test
    void testConvertNullShortNameCurrency2(){
        ArrayList<Currency> currencies = Currency.init();
        double result;
        Currency currency2 = currencies.get(1); // Euro
        currency2.getShortName();
        // shortNameCurrency2 valide
        result = MainWindow.convert("US Dollar", "Euro", currencies, 100.0);
        assertEquals(93.0, result);
        // shortNameCurrency2 invalide, mais tout le reste est valide
        currency2.setShortName(null);
        result = MainWindow.convert("US Dollar", "Euro", currencies, 100.0);
        assertEquals(0.0, result);
    }

    @Test
    void testConvertEmptyCurrencies(){
        ArrayList<Currency> currencies = Currency.init();
        currencies.clear();
        double result = MainWindow.convert("US Dollar", "Euro", currencies, 100.0);
        assertEquals(0.0, result);
    }
}
