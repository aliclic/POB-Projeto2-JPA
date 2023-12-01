/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Aluno;

public class DAOAluno extends DAO<Aluno>{

	public Aluno read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Aluno> q = manager.createQuery("select a from Aluno a where a.nome=:n", Aluno.class);
			q.setParameter("n", nome);
			return q.getSingleResult();

		}catch(NoResultException e){
			return null;
		}
	}

	//  //pode-se sobrescrever o metodo readAll da classe DAO para ordenar o resultado 
	public List<Aluno> readAll(){
		TypedQuery<Aluno> q = manager.createQuery("select a from Aluno a LEFT JOIN FETCH a.telefones order by a.nota", Aluno.class);
		return  q.getResultList();
	}

}

