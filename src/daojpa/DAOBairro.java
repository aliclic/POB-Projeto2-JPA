package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Bairro;

public class DAOBairro extends DAO<Bairro>{

	public Bairro read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Bairro> q = manager.createQuery("select b from Bairro b where b.nome = :n", Bairro.class);
			q.setParameter("n", nome);
			return q.getSingleResult();

		}catch(NoResultException e){
			return null;
		}
	}

	public List<Bairro> readAll(){
		TypedQuery<Bairro> q = manager.createQuery("select b from Bairro b LEFT JOIN FETCH b.enderecos order by b.nome", Bairro.class);
		return  q.getResultList();
	}
	
	// consultas
	
	//  todos os bairros que têm mais de uma entrada (n) na lista de endereços
	public List<Bairro> getBairroWithMostAddresses(int n) {
        TypedQuery<Bairro> query = manager.createQuery(
            "select b from Bairro b whire SIZE(b.enderecos) > :n", Bairro.class);
        query.setParameter("n", n);
        return query.getResultList();
    }

}

