import java.text.Normalizer;

public class NormalizarTexto {
    public static String normalizar(String texto) {
        if (texto == null) return "";
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", ""); // remove acentos
        return texto.trim().toLowerCase();
    }
}
