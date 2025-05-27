public class Sessao {
    private Filme filme;
    private Sala sala;
    private Estado estado;
    private int dia;
    private int mes;
    private int ano;
    private int hora;
    private int minuto;

    public Sessao(Filme filme, Sala sala, Estado estado, int dia, int mes, int ano, int hora, int minuto) {
        this.filme = filme;
        this.sala = sala;
        this.estado = estado;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.hora = hora;
        this.minuto = minuto;
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
}
