package modelo;

import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Bairro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String nomeBairro;
	
    @OneToMany(mappedBy="bairro", 
    		cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private ArrayList<Endereco> enderecos;
    
    public Bairro() {}
    public Bairro(String nomeBairro){
        this.nomeBairro = nomeBairro;
        this.enderecos = new ArrayList<>();
    }

    public String getNome() {
        return nomeBairro;
    }

    public void setNome(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }
    
    public void adicionarEndereco(Endereco endereco) {
    	this.enderecos.add(endereco);
    }
    
    public void removerEndereco(int id) {
    	enderecos.remove(id);
    }
    
    public ArrayList<Endereco> getEnderecos() {
    	return enderecos;
    }
    
    @Override
    public String toString() {
        return "Bairro: " + nomeBairro + ", " + "enderecos: " + enderecos;
        }
}