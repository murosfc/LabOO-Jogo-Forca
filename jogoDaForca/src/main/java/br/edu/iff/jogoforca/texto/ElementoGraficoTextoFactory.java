package br.edu.iff.jogoforca.texto;

import br.edu.iff.jogoforca.ElementoGraficoFactory;

public class ElementoGraficoTextoFactory implements ElementoGraficoFactory{
    private static ElementoGraficoTextoFactory soleInstance = null;

    private ElementoGraficoTextoFactory() {
    }

    public static ElementoGraficoTextoFactory getSoleInstance() {
        if (soleInstance == null)
            soleInstance = new ElementoGraficoTextoFactory();
        return soleInstance;
    }
    
    
}
