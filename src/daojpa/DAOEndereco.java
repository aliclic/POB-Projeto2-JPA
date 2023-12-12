package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Endereco;

public class DAOEndereco  extends DAO<Endereco>{
	
	public Endereco read (Object chave){
		try{
			Integer id = (Integer) chave;
			TypedQuery<Endereco> q = manager.createQuery("select e from Endereco e where e.id = :n ", Endereco.class);
			q.setParameter("n", id);

			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	public List<Endereco> readAll(){
		TypedQuery<Endereco> q = manager.createQuery("select e from Endereco e order by e.id", Endereco.class);
		return  q.getResultList();
	}

	public List<Endereco> listarPorBairro(String nomeBairro) {
		TypedQuery<Endereco> query = manager.createQuery(
				"select e from Endereco e where e.bairro.nome = :nomeBairro", Endereco.class);
		query.setParameter("nomeBairro", nomeBairro);
		return query.getResultList();
	}



}
