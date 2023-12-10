package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Bairro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    private String nome;
	
    @OneToMany(mappedBy = "bairro", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Endereco> enderecos = new ArrayList<>();
    
    public Bairro() {}
    public Bairro(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nomeBairro) {
        this.nome = nomeBairro;
    }
    
    public void adicionarEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }
    
    public void removerEndereco(int id) {
        enderecos.remove(id);
    }
    
    public List<Endereco> getEnderecos() {
    	return enderecos;
    }
    
    @Override
    public String toString() {
        return "Bairro: " + nome + ", " + "enderecos: " + enderecos;
    }
}