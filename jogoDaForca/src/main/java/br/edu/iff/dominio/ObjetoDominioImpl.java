package br.edu.iff.dominio;

public abstract class ObjetoDominioImpl implements ObjetoDominio {
    private Long id;

    public ObjetoDominioImpl(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }    
}
