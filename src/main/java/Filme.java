import java.util.LinkedList;
import java.io.Serializable;
import java.util.Objects;

public class Filme implements Serializable{
    private String nome;
    private int duracao;
    private String foto; //guardar o nome da foto
    private Idioma idiomas;
    private String idade; // nao da para ser enum, por isso, guarda-se numa string
    private LinkedList<Genero> generos;
    private String tipos;
    private Estado estado;
    private float precoCompra;
    private boolean comSessao;
    private static final long serialVersionUID = 1L;

    public Filme(String nome, int duracao, String foto, Idioma idiomas, String idade, LinkedList<Genero> generos, String tipos, Estado estado, Float precoCompra) {
        this.nome = nome;
        this.duracao = duracao;
        this.foto = foto;
        this.idiomas = idiomas;
        this.idade = idade;
        this.generos = generos;
        this.tipos = tipos;
        this.estado = estado;
        this.precoCompra = precoCompra;
        this.comSessao = false;
    }

    public String getNome() {
        return nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public String getFoto() {
        return foto;
    }

    public Idioma getIdiomas() {
        return idiomas;
    }

    public String getIdade() {
        return idade;
    }

    public LinkedList<Genero> getGeneros() {
        return generos;
    }

    public String getTipos() {
        return tipos;
    }

    public Estado getEstado() {
        return estado;
    }

    public Float getPrecoCompra() {
        return precoCompra;
    }

    public boolean isComSessao() {
        return comSessao;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }


    public void setComSessao(boolean comSessao) {
        this.comSessao = comSessao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Filme filme = (Filme) o;

        return nome.equals(filme.nome)
                && duracao == filme.duracao
                && idiomas == filme.idiomas
                && idade.equals(filme.idade)
                && generos.equals(filme.generos)
                && tipos.equals(filme.tipos)
                && estado == filme.estado
                && Float.compare(precoCompra, filme.precoCompra) == 0
                && foto.equals(filme.foto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, duracao, idiomas, idade, generos, tipos, estado, precoCompra, foto);
    }
}
