package br.edu.iff.jogoforca.imagem;

import br.edu.iff.jogoforca.ElementoGraficoFactory;
import br.edu.iff.jogoforca.dominio.boneco.imagem.BonecoImagemFactory;

public class ElementoGraficoImagemFactory implements ElementoGraficoFactory{
    private static ElementoGraficoImagemFactory soleInstance = null;
    
    private BonecoImagemFactory bonecoImageFactory;

    public static ElementoGraficoImagemFactory getSoleInstance() {
        if (soleInstance == null){
            soleInstance = new ElementoGraficoImagemFactory();
        }
        return soleInstance;
    }

    private ElementoGraficoImagemFactory() {
    }
    
    
    
    
}
