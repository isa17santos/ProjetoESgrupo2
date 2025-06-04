import java.io.Serializable;

public class Sessao implements Serializable{
    private Filme filme;
    private Sala sala;
    private Estado estado;
    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minuto;
    private int bilhetesVendidos;
    private static final long serialVersionUID = 1L;

    public Sessao(Filme filme, Sala sala, Estado estado, int dia, int mes, int ano, int hora, int minuto) {
        this.filme = filme;
        this.sala = sala;
        this.estado = estado;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
        this.minuto = minuto;
        this.bilhetesVendidos = 0; // Inicializa o número de bilhetes vendidos como 0
    }

    public Filme getFilme() {
        return filme;
    }

    public Sala getSala() {
        return sala;
    }

    public Estado getEstado() {
        return estado;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public int getBilhetesVendidos() {
        return bilhetesVendidos;
    }

    public void venderBilhete() {
        this.bilhetesVendidos++;
    }
}
