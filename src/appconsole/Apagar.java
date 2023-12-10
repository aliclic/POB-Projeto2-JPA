package appconsole;

import regras_negocio.Fachada;

public class Apagar {
    public Apagar() {
		try {
			Fachada.inicializar();
			Fachada.excluirPessoa("Gustavo");
			System.out.println("pessoa excluída");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Fachada.finalizar();
		System.out.println("\nfim do programa !");
	}
	public static void main(String[] args) {
		new Apagar();
	}
}
