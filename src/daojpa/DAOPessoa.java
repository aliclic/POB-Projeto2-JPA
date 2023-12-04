package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Pessoa;

public class DAOPessoa extends DAO<Pessoa>{


	public Pessoa read (Object chave){
		try{
			String nome = (String) chave;
			nome = nome.toUpperCase();
			TypedQuery<Pessoa> q = manager.createQuery("select p from Pessoa p where p.nome=:n",Pessoa.class);
			q.setParameter("n", nome);
			return q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}

	public List<Pessoa> readAll(){
		TypedQuery<Pessoa> q = manager.createQuery("select p from Pessoa order by p.id", Pessoa.class);
		return  q.getResultList();
	}

	//  consultas
	
	// lista de pessoa que possuem um determinado grau de amizade
	public List<Pessoa> consultaPessoasPorGrauAmizade(int grauAmizade) {
		TypedQuery<Pessoa> q = manager.createQuery
				("select p from Pessoa p where p.grauAmizade = :x", Pessoa.class);
		q.setParameter("x", grauAmizade);
		return q.getResultList();
	}

	// lista de pessoas que possuem um determinado bairro atribuido
	public List<Pessoa> getPessoasByBairro(String nomeBairro) {
		TypedQuery<Pessoa> q = manager.createQuery
				("select p from Pessoa p where p.endereco.bairro.nome = :x", Pessoa.class);
		q.setParameter("x", nomeBairro);
		return q.getResultList();
	}


}

