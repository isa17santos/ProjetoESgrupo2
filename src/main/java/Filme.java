import java.util.LinkedList;
import java.io.Serializable;

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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setIdiomas(Idioma idiomas) {
        this.idiomas = idiomas;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public void setGeneros(LinkedList<Genero> generos) {
        this.generos = generos;
    }

    public void setTipos(String tipos) {
        this.tipos = tipos;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setPrecoCompra(Float precoCompra) {
        this.precoCompra = precoCompra;
    }

    public int getPrecoBilhete() {
        return 0;
    }
}
