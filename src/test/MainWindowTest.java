import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class MainWindowTest {

    @Test
    void testConvertWithValidCurrencies() {
        ArrayList<Currency> currencies = ("Currency 1", "Currency 2");
        double result = MainWindow.convert("Currency 1", "Currency 2", currencies, 100.0);
        assertEquals(93.0, result); // Vérifier en fonction des valeurs d'échange actuelles
    }
}
