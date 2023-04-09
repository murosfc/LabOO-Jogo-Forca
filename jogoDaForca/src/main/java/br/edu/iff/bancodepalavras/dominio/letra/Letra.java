package br.edu.iff.bancodepalavras.dominio.letra;

import java.util.Objects;

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
        if (o == this) return true;
        if (!(o instanceof Letra)) return false;
        Letra letra = (Letra) o;
        return codigo == letra.codigo && this.getClass().equals(letra.getClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return String.valueOf(codigo);
    }
}
