package appconsole;

import regras_negocio.Fachada;

public class Cadastrar {

	public Cadastrar() {
		try {
			Fachada.inicializar();
			System.out.println("cadastrando bairro...");
			Fachada.cadastrarBairro("Centro");
			Fachada.cadastrarBairro("Mangabeira");
			Fachada.cadastrarBairro("JoseAmerico");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("cadastrando endereco...");
			Fachada.cadastrarEndereco("Exemplo1", 111, "Centro");
			Fachada.cadastrarEndereco("Exemplo2", 1112, "Centro");
			Fachada.cadastrarEndereco("Exemplo3", 122, "Mangabeira");
			Fachada.cadastrarEndereco("Exemplo4", 3312, "JoseAmerico");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("cadastrando pessoa...");
			Fachada.cadastrarPessoa("Gustavo", 1, 3, "03102002");
			Fachada.cadastrarPessoa("Gustavo2", 2, 2, "03102002");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}


	public static void main(String[] args) {
		new Cadastrar();
	}
}