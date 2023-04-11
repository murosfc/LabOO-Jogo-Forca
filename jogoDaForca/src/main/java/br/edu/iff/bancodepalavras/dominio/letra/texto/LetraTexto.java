package br.edu.iff.bancodepalavras.dominio.letra.texto;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import java.util.Objects;

public class LetraTexto extends Letra {  

    public LetraTexto(char codigo) {
        super(codigo);
    }

    @Override
    public void exibir(Object contexto) {
        System.out.println(this.getCodigo());
    }
    
    public void exibirOculto(Object contexto) {
        System.out.println("_");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LetraTexto)) return false;
        LetraTexto letraTexto = (LetraTexto) o;
        return letraTexto.getCodigo() == this.getCodigo();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getCodigo());
    }

    @Override
    public String toString() {
        return String.valueOf(this.getCodigo());
    }
}
