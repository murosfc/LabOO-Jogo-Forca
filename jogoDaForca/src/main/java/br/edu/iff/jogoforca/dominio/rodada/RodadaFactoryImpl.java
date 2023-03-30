package br.edu.iff.jogoforca.dominio.rodada;

public abstract class RodadaFactoryImpl implements RodadaFactory {

    private RodadaRepository rodadaRepository;

    protected RodadaRepository getRodadaRepository() {
        return rodadaRepository/*.get()*/;
    }

}
