package br.edu.iff.dominio;

public abstract class ObjetoDominioImpl implements ObjetoDominio {
    public Long id;

    public ObjetoDominioImpl(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }    
}
