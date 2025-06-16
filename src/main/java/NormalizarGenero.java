import java.text.Normalizer;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class NormalizarGenero {
    public static String formatarGeneros(LinkedList<Genero> generos) {
        return generos.stream()
                .map(NormalizarGenero::formatarGenero)
                .collect(Collectors.joining(", "));
    }

    private static String formatarGenero(Genero genero) {
        // Começa com o nome do enum: FICCAO_CIENTIFICA
        String texto = genero.name()
                .replace("_", " ")                       // FICCAO CIENTIFICA
                .toLowerCase();                         // ficcao cientifica

        // Capitaliza a primeira letra de cada palavra
        texto = capitalizarPalavras(texto);

        // Substitui por versões acentuadas se necessário (personalizado)
        texto = texto.replace("Animacao", "Animação")
                .replace("Acao", "Ação")
                .replace("Comedia", "Comédia")
                .replace("Ficcao", "Ficção")
                .replace("Cientifica", "Científica");

        return texto;
    }

    private static String capitalizarPalavras(String texto) {
        String[] palavras = texto.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String palavra : palavras) {
            if (palavra.length() > 0) {
                sb.append(Character.toUpperCase(palavra.charAt(0)))
                        .append(palavra.substring(1))
                        .append(" ");
            }
        }
        return sb.toString().trim();
    }
}
