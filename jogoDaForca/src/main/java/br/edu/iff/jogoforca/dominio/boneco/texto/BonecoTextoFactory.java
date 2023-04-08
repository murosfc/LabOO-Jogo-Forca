package br.edu.iff.jogoforca.dominio.boneco.texto;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;


public class BonecoTextoFactory implements BonecoFactory{
    private static BonecoTextoFactory soleInstance = null;

    public BonecoTextoFactory() {
    }

    public static BonecoTextoFactory getSoleInstance() {
        return soleInstance == null ? soleInstance = new BonecoTextoFactory() : soleInstance;        
    }     

    @Override
    public Boneco getBoneco() {
    	return BonecoTexto.getSoleInstance();   
    }
    
}
