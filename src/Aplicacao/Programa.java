package Aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import modelo.entidades.AluguelCarro;
import modelo.entidades.Veiculo;
import modelo.servicos.BrasilTaxaServico;
import modelo.servicos.ServicoAluguel;

public class Programa {
	
	public static void main(String[] args)throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Entre com a data do aluguel");
		System.out.print("Modelo do carro: ");
		String modeloCarro = sc.nextLine();
		System.out.print("Retirada (dd/MM/aaaa hh:ss): ");
		Date inicio = sdf.parse(sc.nextLine());
		System.out.print("Retorno (dd/MM/aaaa hh:ss): ");
		Date fim = sdf.parse(sc.nextLine());
		
		AluguelCarro ac = new AluguelCarro(inicio, fim, new Veiculo(modeloCarro));
		
		System.out.print("Entre com o preço por hora: ");
		double precoPorHora = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		double precoPorDia = sc.nextDouble();
		
		ServicoAluguel servicoAluguel = new ServicoAluguel(precoPorDia, precoPorHora, new BrasilTaxaServico());
		
		servicoAluguel.processoFatura(ac);
		
		System.out.println("FATURA: ");
		System.out.println("Pagamento base: " + String.format("%.2f", ac.getFatura().getPagamentoBase()));
		System.out.println("Taxa: " + String.format("%.2f", ac.getFatura().getTaxa()));
		System.out.println("Pagamento total: " + String.format("%.2f", ac.getFatura().getPagamentoTotal()));
		
		
		sc.close();
	}

}
