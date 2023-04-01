package br.edu.iff.jogoforca.imagem;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.imagem.LetraImagemFactory;
import br.edu.iff.jogoforca.ElementoGraficoFactory;
import br.edu.iff.jogoforca.dominio.boneco.Boneco;
import br.edu.iff.jogoforca.dominio.boneco.imagem.BonecoImagemFactory;

public class ElementoGraficoImagemFactory implements ElementoGraficoFactory{
    private static ElementoGraficoImagemFactory soleInstance = null;
    
    private BonecoImagemFactory bonecoImageFactory;
    private LetraImagemFactory letraImagemFactory;

    public static ElementoGraficoImagemFactory getSoleInstance() {
        return soleInstance == null ? soleInstance = new ElementoGraficoImagemFactory() : soleInstance;      
    }

    private ElementoGraficoImagemFactory() {
        this.bonecoImageFactory = BonecoImagemFactory.getSoleInstance();
        this.letraImagemFactory = LetraImagemFactory.getSoleInstance();
    }

    @Override
    public Boneco getBoneco() {
        return this.bonecoImageFactory.getBoneco();
    }

    @Override
    public Letra getLetra(char codigo) {
        return this.letraImagemFactory.getLetra(codigo);
    }

    @Override
    public Letra getLetraEncoberta() {
        return letraImagemFactory.getLetraEncoberta();
    }

    @Override
    public Letra criar(char charAt) {
        return this.letraImagemFactory.criar(charAt);
    }
    
    
    
    
}
