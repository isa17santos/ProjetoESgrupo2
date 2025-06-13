import java.io.Serializable;

public class Lugar implements Serializable{

    private boolean acessivel; // true = cadeira de rodas
    private EstadoLugar estado;

    public Lugar(boolean acessivel, EstadoLugar estado) {
        this.acessivel = acessivel;
        this.estado = estado;
    }

    public boolean isAcessivel() {
        return acessivel;
    }

    public EstadoLugar getEstado() {
        return estado;
    }

    public void setEstado(EstadoLugar estado) {
        this.estado = estado;
    }
}
