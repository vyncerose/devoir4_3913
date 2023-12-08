package currencyConverter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class MainWindowTest {

    @Test
    void testConvertWithValidCurrencies() {
        ArrayList<Currency> currencies = Currency.init();
        Currency currency1 = new Currency("Currency 1", "C1");
        Currency currency2 = new Currency("Currency 2", "C2");
        currencies.add(currency1);
        currencies.add(currency2);
        currency1.defaultValues();
        currency2.defaultValues();
        double result = MainWindow.convert("US Dollar", "Euro", currencies, 100.0);
        assertEquals(93.0, result); // Vérifier en fonction des valeurs d'échange actuelles
    }
}
