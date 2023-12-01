

package appconsole;

import daodb4o.Util;
import modelo.Bairro;
import modelo.Endereco;
import modelo.Pessoa;
import modelo.Usuario;
import regras_negocio.Fachada;

public class Listar {

	public Listar() {
		try {
			Fachada.inicializar();
			System.out.println("\n---listagem de bair:");
			for(Bairro b: Fachada.listarBairros())
				System.out.println(b);

			System.out.println("\n---listagem de end:");
			for(Endereco e: Fachada.listarEnderecos())
				System.out.println(e);

			System.out.println("\n---listagem de pes:");
			for(Pessoa p: Fachada.listarPessoas())
				System.out.println(p);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Util.desconectar();
		System.out.println("\nfim do programa !");
	}

	public static void main(String[] args) {
		new Listar();
	}
}
