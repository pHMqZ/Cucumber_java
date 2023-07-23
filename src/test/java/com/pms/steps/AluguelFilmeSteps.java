package com.pms.steps;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.pms.entities.Filme;
import com.pms.entities.NotaAluguel;
import com.pms.entities.TipoAluguel;
import com.pms.services.AluguelService;
import com.pms.utils.DateUtils;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

public class AluguelFilmeSteps {

	private Filme filme;
	private AluguelService aluguel = new AluguelService();
	private NotaAluguel nota;
	private String error;
	private TipoAluguel tipoAluguel;

	@Dado("^um filme com estoque de (\\d+) unidades$")
	public void umFilmeComEstoqueDeUnidades(Integer int1) {
		filme = new Filme();
		filme.setEstoque(int1);
	}

	@Dado("^que o preço seja R\\$ (\\d+)$")
	public void queOPreçoSejaR$(Integer int1) {
		filme.setAluguel(int1);
	}

	@Dado("um filme")
	public void umFilme(DataTable table) {
	    Map<String, String> map = table.asMap(String.class, String.class);
	    filme = new Filme();
		filme.setEstoque(Integer.parseInt (map.get("estoque")));
		filme.setAluguel(Integer.parseInt (map.get("preco")));
		String tipo = map.get("tipo");
		tipoAluguel = tipo.equals("semanal")? TipoAluguel.SEMANAL: tipo.equals("extendido")? tipoAluguel.EXTENDIDO: TipoAluguel.COMUM;
	}

	@Quando("alugar")
	public void alugar() {
		try {
			nota = aluguel.alugar(filme, tipoAluguel);
		} catch (RuntimeException e) {
			error = e.getMessage();
		}
	}

	@Então("^o preço do aluguel será R\\$ (\\d+)$")
	public void oPreçoDoAluguelSeráR$(int int1) {
		assertEquals(int1, nota.getPreco());
	}


	@Então("^o estoque do filme será (\\d+) unidade$")
	public void oEstoqueDoFilmeSeráUnidade(int int1) {
		assertEquals(int1, filme.getEstoque());
	}

	@Então("não será possível por falta de estoque")
	public void nãoSeráPossívelPorFaltaDeEstoque() {
		assertEquals("Filme sem estoque", error);
	}
	
	@Dado("^o tipo do aluguel seja (.*)$")
	public void oTipoDoAluguelSejaExtendido(String tipo) {
	    tipoAluguel = tipo.equals("semanal")? TipoAluguel.SEMANAL: tipo.equals("extendido")? tipoAluguel.EXTENDIDO: TipoAluguel.COMUM;
	}

	@Então("^a data de entrega será em (\\d+) dias?$")
	public void aDataDeEntregaSeráEmDias(Integer int1) {
		Date dataEsperada = DateUtils.obterDataDiferencaDias(int1);
		Date dataReal = nota.getDataEntrega();
		
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		assertEquals(format.format(dataEsperada), format.format(dataReal));
	}
	
	@Então("^a pontuação será de (\\d+) pontos$")
	public void aPontuaçãoSeráDePontos(int int1) {
	   assertEquals(int1, nota.getPontuacao());
	}

}







