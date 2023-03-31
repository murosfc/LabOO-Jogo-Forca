package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.repository.RepositoryException;

public interface JogadorRepository{

    public Jogador getPorId(long id);

    public Jogador getPorNome(String nome);

    public Jogador inserir(Jogador jogador) throws RepositoryException;

    public Jogador atualizar(Jogador jogador) throws RepositoryException;

    public Jogador remover(Jogador jogador) throws RepositoryException;

}
