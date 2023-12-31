package regras_negocio;

import java.util.List;

import daojpa.DAO;
import daojpa.DAOBairro;
import daojpa.DAOEndereco;
import daojpa.DAOPessoa;
import modelo.Bairro;
import modelo.Endereco;
import modelo.Pessoa;

public class Fachada {
	private Fachada() {}

	private static DAOEndereco daoendereco = new DAOEndereco();   
	private static DAOBairro daobairro = new DAOBairro(); 
	private static DAOPessoa daopessoa = new DAOPessoa(); 

	public static void inicializar() {
		DAO.open();
	}
	public static void finalizar() {
		DAO.close();
	}


	public static Bairro cadastrarBairro(String nomeBairro) throws Exception {
		DAO.begin();
		Bairro bairro = daobairro.read(nomeBairro);
		if (bairro!=null)
			throw new Exception("bairro ja cadastrado: " + nomeBairro);
		
		bairro  = new Bairro(nomeBairro);
		daobairro.create(bairro);
		DAO.commit();
		return bairro;
	}

	public static void excluirBairro(String nomeBairro) throws Exception {
		DAO.begin();
	    Bairro bairro = daobairro.read(nomeBairro);

	    if (bairro == null) {
	        throw new Exception("Bairro não encontrado: " + nomeBairro);
	    }

	    // Remove os endereços associados ao bairro
	    for (Endereco endereco : bairro.getEnderecos()) {
	        // Remove o endereço da pessoa, se aplicável
	        for (Pessoa pessoa : daopessoa.listarPorEndereco(endereco.getId())) {
	            pessoa.setEndereco(null);
	            daopessoa.update(pessoa);
	        }

	        // Remove o endereço
	        daoendereco.delete(endereco);
	    }

	    // Remove o bairro
	    daobairro.delete(bairro);

	    DAO.commit();
	}

	public static Endereco cadastrarEndereco(String rua, int numero, String nomeBairro) throws Exception {
		DAO.begin();
		Bairro bairro = daobairro.read(nomeBairro);
		if (bairro == null)
			throw new Exception("Bairro não existe: " + nomeBairro);

		Endereco endereco = new Endereco(rua, numero, bairro);

		endereco.setBairro(bairro);
		bairro.adicionarEndereco(endereco);
		daoendereco.update(endereco);
		DAO.commit();
		return endereco;
	}

	public static void excluirEndereco(int enderecoId) throws Exception {
		DAO.begin();
		Endereco endereco = daoendereco.read(enderecoId);
		if (endereco == null) {
			throw new Exception("Endereço não encontrado com ID: " + enderecoId);
		}

		String nomeBairro = endereco.getBairro().getNome();
		Bairro bairro = daobairro.read(nomeBairro);
		if (bairro == null) {
			throw new Exception("Bairro não encontrado para o endereço com ID: " + enderecoId);
		}

		bairro.removerEndereco(enderecoId);
		daobairro.update(bairro);

		for (Pessoa pessoa : daopessoa.listarPorEndereco(enderecoId)) {
	        pessoa.setEndereco(null);
	        daopessoa.update(pessoa);
	    }

	    daoendereco.delete(endereco);
	    DAO.commit();
	}

	public static Pessoa cadastrarPessoa(String nome, int idEndereco, int grauAmizade, String DtNascimento) throws Exception {
		DAO.begin();
		Pessoa pessoa = daopessoa.read(nome);
		if (pessoa != null)
			throw new Exception("Pessoa já existe: " + nome);
		
		Endereco endereco = daoendereco.read(idEndereco);
		if (endereco == null) {
			throw new Exception("Endereço não encontrado com ID: " + idEndereco);
		}
		
		if(grauAmizade <  1 || grauAmizade > 5)
			throw new Exception("Grau de amizade deve ser um valor entre 0 e 5");

		pessoa = new Pessoa(nome, endereco, grauAmizade, DtNascimento);
		daopessoa.create(pessoa);
		DAO.commit();
		return pessoa;
	}

	public static void excluirPessoa(String nome) throws Exception {
		DAO.begin();
		Pessoa pessoa = daopessoa.read(nome);
		if (pessoa == null) {
			throw new Exception("Pessoa não encontrada: " + nome);
		}

		daopessoa.delete(pessoa);
		DAO.commit();
	}

	public static List<Bairro>  listarBairros() {
		DAO.begin();
		List<Bairro> resultados =  daobairro.readAll();
		DAO.commit();
		return resultados;
	} 

	public static List<Endereco>  listarEnderecos() {
		DAO.begin();
		List<Endereco> resultados =  daoendereco.readAll();
		DAO.commit();
		return resultados;
	}

	public static List<Pessoa> listarPessoas() {
		DAO.begin();
		List<Pessoa> resultados =  daopessoa.readAll();
		DAO.commit();
		return resultados;
	}


	public static void trocarEndereco(String nomePessoa, int idEndereco) throws Exception {
		DAO.begin();
		Pessoa pessoa = daopessoa.read(nomePessoa);
		if (pessoa == null)
			throw new Exception("Pessoa não encontrada: " + nomePessoa);
		
		Endereco endereco = daoendereco.read(idEndereco);
		if (endereco == null)
			throw new Exception("Endereco não encontrado com ID: "+ idEndereco);

		pessoa.setEndereco(endereco);
		daopessoa.update(pessoa);
		DAO.commit();
	}
	

	public static List<Bairro> getBairroWithMostAddresses(int n) {
		DAO.begin();
		List<Bairro> resultados = daobairro.getBairroWithMostAddresses(n);
		DAO.commit();
		return resultados;
	}

	public static List<Pessoa> consultaPessoasPorGrauAmizade (int grauAmizade) {
		DAO.begin();
		List<Pessoa> resultados = daopessoa.consultaPessoasPorGrauAmizade(grauAmizade);
		DAO.commit();
		return resultados;
	}

	public static List<Pessoa> getPessoasByBairro(String nomeBairro) throws Exception{
		DAO.begin();
		if(localizarBairro(nomeBairro) == null) {;
			throw new Exception("Bairro não existe!");
		}
		List<Pessoa> resultados = daopessoa.getPessoasByBairro(nomeBairro);
		DAO.commit();
		return resultados;
	}


	public static Bairro localizarBairro(String nomeBairro) {
		return daobairro.read(nomeBairro);
	}
	public static Endereco localizarEndereco(int id) {
		return daoendereco.read(id);
	}
	public static Pessoa localizarPessoa(String nome) {
		return daopessoa.read(nome);
	}

	
}
