package br.edu.iff.jogoforca.dominio.boneco.imagem;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;

public class BonecoImagemFactory implements BonecoFactory{
    private static BonecoImagemFactory soleInstance = null;

    private BonecoImagemFactory() {
    }

    public static BonecoImagemFactory getSoleInstance() {
        if (soleInstance == null)
            soleInstance = new BonecoImagemFactory();
        return soleInstance;
    }

    @Override
    public Boneco getBoneco() {
        return BonecoImagem.getSoleInstance();
    }
            
}
