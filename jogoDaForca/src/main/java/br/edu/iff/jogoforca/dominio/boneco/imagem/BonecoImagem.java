package br.edu.iff.jogoforca.dominio.boneco.imagem;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.tela.Painel;

public class BonecoImagem implements Boneco{
    private static BonecoImagem soleInstance = null;

    public static BonecoImagem getSoleInstance() {   
        return soleInstance == null? soleInstance = new BonecoImagem() : soleInstance;
    }
    
    private BonecoImagem (){}

    @Override
    public void exibir(Object contexto, int partes) { 
    	Painel painel = (Painel) contexto;
    	painel.exibirBoneco(partes);
    }     
    
}
