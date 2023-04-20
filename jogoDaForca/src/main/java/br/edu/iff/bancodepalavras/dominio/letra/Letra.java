package br.edu.iff.bancodepalavras.dominio.letra;

public abstract class Letra {

    private char codigo;

    protected Letra(char codigo) {
        this.codigo = codigo;
    }

    public char getCodigo() {
        return codigo;
    }

    public abstract void exibir(Object contexto);

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Letra)) {
            return false;
        }
        Letra outra = (Letra) o;
        return this.codigo == outra.codigo && this.getClass().equals(outra.getClass());
    }

    @Override
    public int hashCode() {
        return this.codigo + this.getClass().hashCode();

    }

    @Override
    public String toString() {
        return "Letra: " + codigo;
    }
}
