public class NormalizarEnum {
    public static String normalizarEnum(String texto) {
        texto = texto.trim().toUpperCase()
                .replace("Ç", "C")
                .replace("Ã", "A")
                .replace("Á", "A")
                .replace("Â", "A")
                .replace("É", "E")
                .replace("Í", "I")
                .replace("Ó", "O")
                .replace("Ô", "O")
                .replace("Õ", "O")
                .replace("Ú", "U")
                .replace(" ", "_");

        return texto;
    }

}
