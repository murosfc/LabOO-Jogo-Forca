package br.edu.iff.jogoforca.dominio.boneco.texto;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;

public class BonecoTexto implements Boneco {

    private static BonecoTexto soleInstance = null;

    public static BonecoTexto getSoleInstance() {
        if (soleInstance == null)
            soleInstance = new BonecoTexto();
        return soleInstance;
    }

    private BonecoTexto() {
    }

    @Override
    public void exibir(Object context, int partes) {
        switch (partes) {

            case 1:
                System.out.println("1 - cabeça");

                break;

            case 2:
                System.out.println("2 - cabeça, olho esquerdo");

                break;

            case 3:
                System.out.println("3 - cabeça, olho esquerdo, olho direito\n");

                break;

            case 4:
                System.out.println("4 - cabeça, olho esquerdo, olho direito, nariz");

                break;
            case 5:
                System.out.println("5 - cabeça, olho esquerdo, olho direito, nariz, boca");

                break;
            case 6:
                System.out.println("6 - cabeça, olho esquerdo, olho direito, nariz, boca, tronco");

                break;
            case 7:
                System.out.println("7 - cabeça, olho esquerdo, olho direito, nariz, boca, tronco, \n" +
                        "braço esquerdo");

                break;
            case 8:
                System.out.println("8 - cabeça, olho esquerdo, olho direito, nariz, boca, tronco, \n" +
                        "braço esquerdo, braço direito");

                break;
            case 9:
                System.out.println("9 - cabeça, olho esquerdo, olho direito, nariz, boca, tronco, \n" +
                        "braço esquerdo, braço direito, perna esquerda");

                break;
            case 10:
                System.out.println("10 - cabeça, olho esquerdo, olho direito, nariz, boca, tronco, \n" +
                        "braço esquerdo, braço direito, perna esquerda, perna direita\n ");

                break;
            default:

        }
    }
}
