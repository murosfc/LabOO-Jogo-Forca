package br.edu.iff.factory;

import br.edu.iff.repository.Repository;

public abstract class EntityFactory {
    protected Repository repository;
    
    public EntityFactory(Repository repository) {
        this.repository = repository;
    }
    
    protected Repository getRepository() {
        return this.repository;
    }
    
    protected Long getProximoId() {
        return this.repository.getProximoId();
    }
}
