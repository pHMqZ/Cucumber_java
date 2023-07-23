package com.pms.entities;

public class Filme {

	private int estoque;
	private int aluguel;
	
	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public void setAluguel(Integer aluguel) {
		this.aluguel = aluguel;
		
	}

	public int getAluguel() {
		return aluguel;
	}

	public int getEstoque() {
		return estoque;
	}

}
