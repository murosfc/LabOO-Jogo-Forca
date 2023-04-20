package br.edu.iff.jogoforca.dominio.boneco.imagem;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;

public class BonecoImagemFactory implements BonecoFactory{
    private static BonecoImagemFactory soleInstance = null;

    private BonecoImagemFactory() {
    }

    public static BonecoImagemFactory getSoleInstance() {        
        return soleInstance == null ? soleInstance = new BonecoImagemFactory() :soleInstance;
    }

    @Override
    public Boneco getBoneco() {
        return BonecoImagem.getSoleInstance();
    }
            
}
