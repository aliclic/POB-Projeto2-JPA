package regras_negocio;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import java.util.List;

import daodb4o.DAO;
import daodb4o.DAOBairro;
import daodb4o.DAOEndereco;
import daodb4o.DAOPessoa;
import daodb4o.DAOUsuario;
import modelo.Bairro;
import modelo.Endereco;
import modelo.Pessoa;
import modelo.Usuario;

public class Fachada {
	private Fachada() {}

	private static DAOEndereco daoendereco = new DAOEndereco();  
	private static DAOBairro daobairro = new DAOBairro(); 
	private static DAOPessoa daopessoa = new DAOPessoa(); 
	private static DAOUsuario daousuario = new DAOUsuario(); 
	public static Usuario logado;	//contem o objeto Usuario logado em TelaLogin.java

	public static void inicializar(){
		DAO.open();
	}
	public static void finalizar(){
		DAO.close();
	}


	public static Bairro cadastrarBairro(String nomeBairro) throws Exception{
		DAO.begin();
		Bairro bairro = daobairro.read(nomeBairro);
		if (bairro!=null)
			throw new Exception("bairro ja cadastrado:" + " " + nomeBairro);
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

		for (Endereco endereco : bairro.getEnderecos()) {
			daoendereco.delete(endereco);
		}

		daobairro.delete(bairro);
		DAO.commit();
	}

	public static Endereco cadastrarEndereco(String rua, int numero, String nomeBairro) throws Exception {
		DAO.begin();
		Bairro bar = daobairro.read(nomeBairro);
		if (bar==null)
			throw new Exception("Bairro não existe:" + " " + nomeBairro);

		Endereco end = new Endereco(rua, numero, nomeBairro);


		bar.adicionarEndereco(end);
		daobairro.update(bar);
		daoendereco.create(end);
		DAO.commit();
		return end;
	}

	public static void excluirEndereco(int enderecoId) throws Exception {
		DAO.begin();
		Endereco endereco = daoendereco.read(enderecoId);

		if (endereco == null) {
			throw new Exception("Endereço não encontrado com ID: " + enderecoId);
		}

		String nomeBairro = endereco.getBairro();
		Bairro bairro = daobairro.read(nomeBairro);

		if (bairro == null) {
			throw new Exception("Bairro não encontrado para o endereço com ID: " + enderecoId);
		}

		bairro.removerEndereco(enderecoId);
		daoendereco.delete(endereco);
		daobairro.update(bairro);
		DAO.commit();
	}

	public static Pessoa cadastrarPessoa(String nome, String rua, int numero, String nomeBairro, int grauAmizade, String DtNascimento) throws Exception {
		DAO.begin();
		Pessoa pessoa = daopessoa.read(nome);
		if(pessoa!=null)
			throw new Exception("Pessoa já existe: " + nome);

		String endereco = rua + " " + numero + " " + nomeBairro;

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

	public static List<Bairro>  listarBairros(){
		DAO.begin();
		List<Bairro> resultados =  daobairro.readAll();
		DAO.commit();
		return resultados;
	} 

	public static List<Endereco>  listarEnderecos(){
		DAO.begin();
		List<Endereco> resultados =  daoendereco.readAll();
		DAO.commit();
		return resultados;
	}

	public static List<Pessoa> listarPessoas(){
		DAO.begin();
		List<Pessoa> resultados =  daopessoa.readAll();
		DAO.commit();
		return resultados;
	}

	public static List<Usuario>  listarUsuarios(){
		DAO.begin();
		List<Usuario> resultados =  daousuario.readAll();
		DAO.commit();
		return resultados;
	}


	public static void trocarEndereco(String nome, int id) throws Exception{
		DAO.begin();
		Endereco endereco = daoendereco.read(id);
		Pessoa pessoa = daopessoa.read(nome);
		if(endereco==null)
			throw new Exception("Endereco não encontrado");

		pessoa.setEndereco(endereco.getRua() + " " + endereco.getNumero() + " " + endereco.getBairro());
		daopessoa.update(pessoa);
		DAO.commit();
	}

	public static List<Bairro> getBairroWithMostAddresses(int n){
		DAO.begin();
		List<Bairro> resultados =  daobairro.getBairroWithMostAddresses(n);
		DAO.commit();
		return resultados;
	}

	public static List<Pessoa> consultaPessoasPorGrauAmizade (int grauAmizade){
		DAO.begin();
		List<Pessoa> resultados =  daopessoa.consultaPessoasPorGrauAmizade(grauAmizade);
		DAO.commit();
		return resultados;
	}

	public static List<Pessoa> getPessoasByBairro(String nomeBairro){
		DAO.begin();
		List<Pessoa> resultados =  daopessoa.getPessoasByBairro(nomeBairro);
		DAO.commit();
		return resultados;
	}


	public static Bairro localizarBairro(String nomeBairro){
		return daobairro.read(nomeBairro);
	}
	public static Endereco localizarEndereco(int id){
		return daoendereco.read(id);
	}
	public static Pessoa localizarPessoa(String nome){
		return daopessoa.read(nome);
	}

	
	//------------------Usuario------------------------------------
	public static Usuario cadastrarUsuario(String nome, String senha) throws Exception{
		DAO.begin();
		Usuario usu = daousuario.read(nome);
		if (usu!=null)
			throw new Exception("Usuario ja cadastrado:" + nome);
		usu = new Usuario(nome, senha);

		daousuario.create(usu);
		DAO.commit();
		return usu;
	}
	public static Usuario localizarUsuario(String nome, String senha) {
		Usuario usu = daousuario.read(nome);
		if (usu==null)
			return null;
		if (! usu.getSenha().equals(senha))
			return null;
		return usu;
	}
}
