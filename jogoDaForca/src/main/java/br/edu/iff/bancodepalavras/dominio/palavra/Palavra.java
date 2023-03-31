public class Palavra extends ObjetoDominioImpl {

    private LetraFactory letraFactory;
    private Letra[] letras;
    private Tema tema;

    public Palavra(long id, String palavra, Tema tema) {
        super(id);
        this.letraFactory = null;
        this.letras = new Letra[palavra.length()];
        this.tema = tema;

        for (int i = 0; i < palavra.length(); i++) {
            this.letras[i] = letraFactory.criar(palavra.charAt(i));
        }
    }

    public static Palavra criar(long id, String palavra, Tema tema, LetraFactory letraFactory) {
        Palavra p = new Palavra(id, palavra, tema);
        p.setLetraFactory(letraFactory);
        return p;
    }

    public static Palavra reconstituir(long id, String palavra, Tema tema, LetraFactory letraFactory) {
        Palavra p = new Palavra(id, palavra, tema);
        p.setLetraFactory(letraFactory);
        return p;
    }

    public void setLetraFactory(LetraFactory factory) {
        this.letraFactory = factory;
        for (int i = 0; i < letras.length; i++) {
            this.letras[i].setLetraFactory(factory);
        }
    }

    public LetraFactory getLetraFactory() {
        return this.letraFactory;
    }

    public Letra[] getLetras() {
        return this.letras;
    }

    public Letra getLetra(int posicao) {
        return this.letras[posicao];
    }

    public void exibir(Object contexto) {
        // Implementação do método exibir
    }

    public void exibir(Object contexto, boolean[] posicoes) {
        // Implementação do método exibir com posicoes
    }

    public int[] tentar(char codigo) {
        // Implementação do método tentar
    }

    public Tema getTema() {
        return this.tema;
    }

    public boolean comparar(String palavra) {
        // Implementação do método comparar
    }

    public int getTamanho() {
        return this.letras.length;
    }

    @Override
    public String toString() {
        // Implementação do método toString
    }
}
