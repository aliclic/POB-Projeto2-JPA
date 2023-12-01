/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
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

	//  sobrescrever o metodo readAll da classe DAO 
	public List<Pessoa> readAll(){
		TypedQuery<Pessoa> q = manager.createQuery("select p from Pessoa p LEFT JOIN FETCH p.telefones order by p.id", Pessoa.class);
		return  q.getResultList();
	}

	//--------------------------------------------
	//  consultas
	//--------------------------------------------
	public  List<Pessoa> readAll(String caracteres) {
		caracteres = caracteres.toUpperCase();

		TypedQuery<Pessoa> q = manager.createQuery
				("select p from Pessoa p LEFT JOIN FETCH p.telefones where p.nome like :x  order by p.nome",Pessoa.class);
		q.setParameter("x", "%"+caracteres+"%");
		return q.getResultList();
	}


	public  List<Pessoa>  readByNTelefones(int n) {
		TypedQuery<Pessoa> q = manager.createQuery
				("select p from Pessoa p JOIN FETCH p.telefones where SIZE(p.telefones) = :x",Pessoa.class);
		q.setParameter("x", n);
		return q.getResultList(); 
	}

	public List<Pessoa> readByMes(String mes) {
		TypedQuery<Pessoa> q = manager.createQuery
				("select p from Pessoa p JOIN FETCH p.telefones where substring(p.dtnascimento,4,2) = :m",Pessoa.class);
		q.setParameter("m", mes);
		return q.getResultList(); 

	}
		
	public  boolean  temTelefoneCelular(String nome) {
		try{
			nome = nome.toUpperCase();

			Query q = manager.createQuery
					("select count(t) from Pessoa p join p.telefones t where p.nome = :x and t.numero like :y");
			q.setParameter("x", nome);
			q.setParameter("y", "9%"); //inicia com 9
			long cont = (Long) q.getSingleResult();
			return cont>0;	
		}catch(NoResultException e){
			return false;
		}
	}

	public  boolean  temTelefoneFixo(String nome) {
		try{
			nome = nome.toUpperCase();

			Query q = manager.createQuery
					("select count(t) from Pessoa p join p.telefones t where p.nome = :x and t.numero like :y");
			q.setParameter("x", nome);
			q.setParameter("y", "3%"); //inicia com 3

			long cont = (Long) q.getSingleResult();
			return cont>0;	
		}catch(NoResultException e){
			return false;
		}
	}


}

