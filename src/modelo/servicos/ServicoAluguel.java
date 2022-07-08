package modelo.servicos;

import modelo.entidades.AluguelCarro;
import modelo.entidades.Fatura;

public class ServicoAluguel {
	
	private Double precoPorDia;
	private Double precoPorHora;
	
	private TaxaServico taxaServico;

	public ServicoAluguel(Double precoPorDia, Double precoPorHora, TaxaServico taxaServico) {
		super();
		this.precoPorDia = precoPorDia;
		this.precoPorHora = precoPorHora;
		this.taxaServico = taxaServico;
	}
	
	public void processoFatura(AluguelCarro aluguelCarro) {
		long t1 = aluguelCarro.getInicio().getTime();
		long t2 = aluguelCarro.getFim().getTime();
		double horas =(double)(t2 - t1) / 1000 / 60 / 60;
		
		double pagamentoBase;
		if(horas <= 12.0) {
			pagamentoBase = Math.ceil(horas) * precoPorHora;
		}
		else {
			pagamentoBase = Math.ceil(horas / 24) * precoPorDia;
		}
			
		double taxa = taxaServico.taxa(pagamentoBase);
		
		aluguelCarro.setFatura(new Fatura(pagamentoBase, taxa));
	}
}
