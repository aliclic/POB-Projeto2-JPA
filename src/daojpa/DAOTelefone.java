/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Telefone;

public class DAOTelefone  extends DAO<Telefone>{
	
	public Telefone read (Object chave){
		try{
			String numero = (String) chave;
			TypedQuery<Telefone> q = manager.createQuery("select t from Telefone t where t.numero = :n ",Telefone.class);
			q.setParameter("n", numero);

			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	//  sobrescrever o metodo readAll da classe DAO 
	public List<Telefone> readAll(){
		TypedQuery<Telefone> q = manager.createQuery("select t from Telefone t LEFT JOIN FETCH t.pessoa order by t.id", Telefone.class);
		return  q.getResultList();
	}

	
	//--------------------------------------------
	//  consultas
	//--------------------------------------------

	public List<Telefone> readAll (String digitos){		
		TypedQuery<Telefone> q = manager.createQuery
				("select t from Telefone t LEFT JOIN FETCH t.pessoa  where t.numero like :x order by t.numero",Telefone.class);
		q.setParameter("x", "%"+digitos+"%");
		return q.getResultList();
	}
}
