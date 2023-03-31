public abstract class ObjetoDominioImpl implements ObjetoDominio {
    private long id;

    public ObjetoDominioImpl(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
