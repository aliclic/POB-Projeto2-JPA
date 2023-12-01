package appconsole;

import modelo.Bairro;
import modelo.Endereco;
import modelo.Pessoa;
import regras_negocio.Fachada;

public class Consultar {
	public Consultar() {
		try {
			Fachada.inicializar();

			System.out.println("consultas... \n");
			System.out.println("\nBairro com mais de 1 endereco");
			for(Bairro a : Fachada.getBairroWithMostAddresses(1))
				System.out.println(a);


			System.out.println("\nPessoas  que  moram no bairro Centro");
			for(Pessoa b : Fachada.getPessoasByBairro("Centro"))
				System.out.println(b);


			System.out.println("\nPessoas com grau de amizade 2");
			for(Pessoa c : Fachada.consultaPessoasPorGrauAmizade(2))
				System.out.println(c);


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Consultar();
	}
}

