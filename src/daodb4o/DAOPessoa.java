/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daodb4o;

import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Bairro;
import modelo.Pessoa;

public class DAOPessoa extends DAO<Pessoa>{

	public Pessoa read (Object chave) {
		String nomePessoa = (String) chave;
		Query q = manager.query();
		q.constrain(Pessoa.class);
		q.descend("nome").constrain(nomePessoa);
		List<Pessoa> resultados =  q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}

	public List<Pessoa> consultaPessoasPorGrauAmizade(int grauAmizade) {
		Query query = manager.query();
		query.constrain(Pessoa.class);
		query.descend("grauAmizade").constrain(grauAmizade);
		return query.execute();
	}

	public List<Pessoa> getPessoasByBairro(String nomeBairro) {
		Query query = manager.query();
		query.constrain(Pessoa.class);
		query.descend("endereco").descend("bairro").descend("nomeBairro").constrain(nomeBairro);
		return query.execute();
	}

}

