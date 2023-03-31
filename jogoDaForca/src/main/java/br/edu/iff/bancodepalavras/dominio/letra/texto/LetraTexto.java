public class LetraTexto implements Letra {
    private char codigo;

    public LetraTexto(char codigo) {
        this.codigo = codigo;
    }

    public char getCodigo() {
        return codigo;
    }

    @Override
    public void exibir(Object contexto) {
        System.out.print(codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LetraTexto)) return false;
        LetraTexto letraTexto = (LetraTexto) o;
        return codigo == letraTexto.codigo;
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
