public abstract class LetraFactoryImpl implements LetraFactory {

    // Método template que define o fluxo de criação de uma letra
    protected final Letra getLetra(char codigo) {
        Letra letra = null;
        if (codigo == '_') {
            letra = getLetraEncoberta();
        } else {
            letra = criarLetra(codigo);
        }
        return letra;
    }
    
    // Método abstrato que deve ser implementado pelas classes filhas
    protected abstract Letra criarLetra(char codigo);

    // Método que retorna uma instância de LetraEncoberta
    public final Letra getLetraEncoberta() {
        return LetraEncoberta.getInstance();
    }

    // Método que retorna uma instância de Letra
    public final Letra getLetra(char codigo) {
        return getLetra(codigo);
    }

}
