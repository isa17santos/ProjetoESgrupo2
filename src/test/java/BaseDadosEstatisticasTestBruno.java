import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BaseDadosEstatisticasTestBruno {
    @Test
    void testVendasPorDiaTotal() {
        BaseDados bd = BaseDados.getInstance();
        Map<String, Integer> vendas = bd.vendasPorDiaTotal();
        assertNotNull(vendas);
        assertEquals(7, vendas.size(), "Should return 7 days");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate today = LocalDate.now();
        int i = 0;
        for (String key : vendas.keySet()) {
            LocalDate expectedDate = today.minusDays(6 - i);
            assertEquals(expectedDate.format(formatter), key, "Date key should match expected");
            assertTrue(vendas.get(key) >= 0, "Sales count should be non-negative");
            i++;
        }
    }

    @Test
    void testVendasPorMesTotal() {
        BaseDados bd = BaseDados.getInstance();
        Map<String, Integer> vendas = bd.vendasPorMesTotal();
        assertNotNull(vendas);
        assertEquals(12, vendas.size(), "Should return 12 months");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
        LocalDate current = LocalDate.now().withDayOfMonth(1).minusMonths(11);
        int i = 0;
        for (String key : vendas.keySet()) {
            assertEquals(current.format(formatter), key, "Month key should match expected");
            assertTrue(vendas.get(key) >= 0, "Sales count should be non-negative");
            current = current.plusMonths(1);
            i++;
        }
    }

    @Test
    void testVendasPorAnoTotal() {
        BaseDados bd = BaseDados.getInstance();
        Map<String, Integer> vendas = bd.vendasPorAnoTotal();
        assertNotNull(vendas);
        assertEquals(5, vendas.size(), "Should return 5 years");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        int startYear = LocalDate.now().getYear() - 4;
        int i = 0;
        for (String key : vendas.keySet()) {
            assertEquals(String.valueOf(startYear + i), key, "Year key should match expected");
            assertTrue(vendas.get(key) >= 0, "Sales count should be non-negative");
            i++;
        }
    }

    @Test
    void testProdutosMaisVendidos() {
        BaseDados bd = BaseDados.getInstance();
        Map<String, Integer> produtos = bd.produtosMaisVendidos();
        assertNotNull(produtos);
        assertTrue(produtos.size() <= 10, "Should return at most 10 products");
        for (Map.Entry<String, Integer> entry : produtos.entrySet()) {
            assertNotNull(entry.getKey());
            assertTrue(entry.getValue() >= 0, "Product sales should be non-negative");
        }
    }
}