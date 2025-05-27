public class Sala {
    private String designacao;
    private int numFilas;
    private int numLugaresFila;
    private String ecra;
    private Acessibilidade acessibilidade;
    private String tipo;
    private Estado estado;

    public Sala(String designacao, int numFilas, int numLugaresFila, String ecra, Acessibilidade acessibilidade, String tipo, Estado estado) {
        this.designacao = designacao;
        this.numFilas = numFilas;
        this.numLugaresFila = numLugaresFila;
        this.ecra = ecra;
        this.acessibilidade = acessibilidade;
        this.tipo = tipo;
        this.estado = estado;
    }

    public String getDesignacao() {
        return designacao;
    }

    public int getNumFilas() {
        return numFilas;
    }

    public int getNumLugaresFila() {
        return numLugaresFila;
    }

    public String getEcra() {
        return ecra;
    }

    public Acessibilidade getAcessibilidade() {
        return acessibilidade;
    }

    public String getTipo() {
        return tipo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setDesignacao(String designacao) {
        this.designacao = designacao;
    }

    public void setNumFilas(int numFilas) {
        this.numFilas = numFilas;
    }

    public void setNumLugaresFila(int numLugaresFila) {
        this.numLugaresFila = numLugaresFila;
    }

    public void setEcra(String ecra) {
        this.ecra = ecra;
    }

    public void setAcessibilidade(Acessibilidade acessibilidade) {
        this.acessibilidade = acessibilidade;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
