import java.io.Serializable;
import java.util.LinkedList;

public class Sala implements Serializable{
    private String designacao;
    private int numFilas;
    private int numLugaresFila;
    private String ecra;
    private Acessibilidade acessibilidade;
    private String tipo;
    private Estado estado;

    private Lugar[][] lugares;


    private static final long serialVersionUID = 2L;

    public Sala(String designacao, int numFilas, int numLugaresFila, String ecra, Acessibilidade acessibilidade, String tipo, Estado estado) {
        this.designacao = designacao;
        this.numFilas = numFilas;
        this.numLugaresFila = numLugaresFila;
        this.ecra = ecra;
        this.acessibilidade = acessibilidade;
        this.tipo = tipo;
        this.estado = estado;
        this.lugares = new Lugar[numFilas][numLugaresFila];

        if(acessibilidade.equals(Acessibilidade.SIM))
        {
            inicializarLugares(true);
        }
        else
        {
            inicializarLugares(false);
        }

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

    public boolean temAcessibilidade(){
        if (getAcessibilidade().equals(Acessibilidade.SIM))
        {
            return true;
        }
        return false;
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

    public double getLotacao() {
        return (double) (numFilas * numLugaresFila);
    }

    public Lugar[][] getLugares() {
        return lugares;
    }

    public int getNumLugaresOcupados() {
        int count = 0;
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numLugaresFila; j++) {
                if (lugares[i][j].getEstado() == EstadoLugar.OCUPADO) {
                    count++;
                }
            }
        }
        return count;
    }

    private void inicializarLugares(boolean acessibilidade) {
        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numLugaresFila; j++) {
                // Simples exemplo: cria todos livres e não acessíveis
                lugares[i][j] = new Lugar(false, EstadoLugar.LIVRE);
            }
        }
    }
}
