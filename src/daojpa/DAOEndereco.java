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

	//  consultas
	
	// lista de endereços cujo bairro tem o nome especificado
	public List<Endereco> getEnderecosByBairro(String nomeBairro) {
        TypedQuery<Endereco> q = manager.createQuery(
            "select e from Endereco e where e.bairro.nome = :nomeBairro", Endereco.class);
        q.setParameter("nomeBairro", nomeBairro);
        return q.getResultList();
    }
	
}
