package br.edu.iff.jogoforca.dominio.boneco.texto;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;

public class BonecoTexto implements Boneco{

    private static BonecoTexto soleInstance = null;

    public static BonecoTexto getSoleInstance() {
        return soleInstance == null ? soleInstance = new BonecoTexto() : soleInstance;       
    }

    private BonecoTexto() {
    }

    @Override
    public void exibir(Object context, int partes) {
        String partesTexto[] = {partes + " - ", "cabeça", "olho esquerdo", "olho direito",  "nariz", "boca", "tronco", "braço esquerdo", "braço direito", "perna esquerda", "perna direita"};
        String saida = partesTexto[0];
        for (int i = 1 ; i <= partes; i++){
            saida += partesTexto[i];
            if(i != partes)
               saida += ", ";
        }
        System.out.println(saida);
    }    
}
