package br.edu.iff.bancodepalavras.dominio.letra;

public abstract class LetraFactoryImpl implements LetraFactory {
    private final static int POOL_SIZE = 27;
    private Letra[] pool;
    private Letra encoberta;
    
    protected LetraFactoryImpl(){ 
        this.pool = new Letra[POOL_SIZE];
        this.encoberta = null;
    } 
    
    @Override
    public final Letra getLetra(char codigo){
    	codigo = Character.toLowerCase(codigo);
    	int i = codigo - 'a';
    	Letra letra = this.pool[i];
	    if (letra == null) {
	      letra = this.criarLetra(codigo);
	      this.pool[i] = letra;
	    }
	    return letra;
    }

    @Override
    public final Letra getLetraEncoberta() {
    	if (encoberta == null) {
    		char code = '#';
    		this.encoberta = criarLetra(code);
	    }
	    return this.encoberta;
    }

    protected abstract Letra criarLetra(char codigo);
}
