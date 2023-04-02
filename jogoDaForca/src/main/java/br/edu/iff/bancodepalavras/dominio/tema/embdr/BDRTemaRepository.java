package br.edu.iff.bancodepalavras.dominio.tema.embdr;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;
import java.util.List;

public class BDRTemaRepository implements TemaRepository {
    private static BDRTemaRepository soleInstance;
    
    private BDRTemaRepository() {
        // construtor privado para impedir a criação de instâncias fora da classe
    }
    
    public static synchronized BDRTemaRepository getSoleInstance() {
        return soleInstance == null ? soleInstance = new BDRTemaRepository() : soleInstance;       
    }
    
    @Override
    public Tema getPorId(Long id) {
        return null; // implementação da busca por nome em um banco de dados relacional
    }
    
    public List<Tema> getPorNome(String nome) {
        return null; // implementação da busca por nome em um banco de dados relacional
    }
    
    public List<Tema> getTodos() {
        return null;  //implementação da busca de todos os temas em um banco de dados relacional
    }
    
    public void inserir(Tema tema) throws RepositoryException {
        // implementação da inserção de um tema em um banco de dados relacional
    }
    
    public void atualizar(Tema tema) throws RepositoryException {
        // implementação da atualização de um tema em um banco de dados relacional
    }
    
    public void remover(Tema tema) throws RepositoryException {
        // implementação da remoção de um tema em um banco de dados relacional
    }

    @Override
    public Long getProximoId() {
        return 0L;
    }

   
}
