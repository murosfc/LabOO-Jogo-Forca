public abstract class EntityFactory {
    protected Repository repository;
    
    public EntityFactory(Repository repository) {
        this.repository = repository;
    }
    
    protected Repository getRepository() {
        return this.repository;
    }
    
    protected long getProximoId() {
        return this.repository.getProximoId();
    }
}
