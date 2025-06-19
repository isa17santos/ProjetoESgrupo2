import java.io.Serializable;

public class Bilhete implements Serializable {
    private Sessao sessao; // aqui vai buscar o filme, sala, dia, horario e preço
    private String lugar;
    private boolean vendido;

    private static final long serialVersionUID = 1L;

    public Bilhete(Sessao sessao, String lugar) {
        this.sessao = sessao;
        this.lugar = lugar;
        this.vendido = false; //sempre que o bilhete e criado isto e false. So passa a true quando a compra e finalizada
    }

    public Sessao getSessao() {
        return sessao;
    }

    public String getLugar() {
        return lugar;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }
}
