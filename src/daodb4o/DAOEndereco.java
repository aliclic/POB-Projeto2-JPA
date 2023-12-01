/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Endereco;

public class DAOEndereco extends DAO<Endereco>{

	public Endereco read (Object chave){
		int id = (int) chave;
		Query q = manager.query();
		q.constrain(Endereco.class);
		q.descend("id").constrain(id);
		List<Endereco> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	//criar "id" sequencial
	public void create(Endereco obj){
		int novoid = super.gerarId();  	//gerar novo id da classe
		obj.setId(novoid);				//atualizar id do objeto antes de grava-lo no banco
		manager.store( obj );
	}

}
