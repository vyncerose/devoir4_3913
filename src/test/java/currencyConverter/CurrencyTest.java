package currencyConverter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CurrencyTest {

    // BOITE NOIRE

    // Tests ici

    // BOITE BLANCHE
    @Test
    void testConvert(){
        Double result = Currency.convert(100.0, 0.5);
        assertEquals(50.0, result);
    }
}
