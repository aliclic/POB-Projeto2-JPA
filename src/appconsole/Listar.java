package appconsole;


import modelo.Bairro;
import modelo.Endereco;
import modelo.Pessoa;
import regras_negocio.Fachada;

public class Listar {

	public Listar() {
		try {
			Fachada.inicializar();
			System.out.println("\n---listagem de bairros:");
			for(Bairro b: Fachada.listarBairros())
				System.out.println(b);

			System.out.println("\n---listagem de endereços:");
			for(Endereco e: Fachada.listarEnderecos())
				System.out.println(e);
			
			System.out.println("\n---listagem de pessoas:");
			for(Pessoa p: Fachada.listarPessoas())
				System.out.println(p);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Listar();
	}
}
