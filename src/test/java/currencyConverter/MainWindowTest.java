package currencyConverter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class MainWindowTest {

    // BOITE NOIRE

    // Tests ici

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
