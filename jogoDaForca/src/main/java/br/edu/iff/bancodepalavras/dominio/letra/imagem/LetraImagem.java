package br.edu.iff.bancodepalavras.dominio.letra.imagem;

import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.tela.Painel;

public class LetraImagem extends Letra {

	public LetraImagem(char codigo) {
		super(codigo);
	}

	@Override
	public void exibir(Object contexto) {
		boolean chamadoProletrasErradas = false;
		Painel painel = (Painel) contexto;
		if (painel != null) {
			StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
			for (int i = 0; i <= 5; i++) { // varre as últimas cinco chamadas na pilha
				if (stackTrace[i].getMethodName().equals("exibirLetrasErradas"))
					chamadoProletrasErradas = true;
			}
			if (chamadoProletrasErradas) {
				painel.exibirLetrasErradas(this.getCodigo());
			} else {
				char espaco = ' ';
				if (espaco != this.getCodigo()) {
					painel.exibirPalavras(this.getCodigo());
				}
			}
		}
	}
}
