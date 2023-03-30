package br.edu.iff.jogoforca.embdr;

public class BDRRepositoryFactory {
    private static BDRRepositoryFactory soleInstance = null;

    private BDRRepositoryFactory() {
    }

    public static BDRRepositoryFactory getSoleInstance() {
        if (soleInstance == null)
            soleInstance = new BDRRepositoryFactory();
        return soleInstance;
    }
    
    
}
