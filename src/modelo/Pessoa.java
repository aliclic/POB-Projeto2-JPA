package modelo;

import daojpa.LowerToUpperConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Convert(converter=LowerToUpperConverter.class)
	private String nome;
	
	@OneToOne
	@JoinColumn(name = "endereco_id")
    private Endereco endereco;
    
    private int grauAmizade;
    
    private String dtNascimento;

    public Pessoa() {}
    public Pessoa(String nome, Endereco endereco, int grauAmizade, String dtNascimento) {
        this.nome = nome;
        this.endereco = endereco;
        this.grauAmizade = grauAmizade;
        this.dtNascimento = dtNascimento;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getGrauAmizade() {
        return grauAmizade;
    }

    public void setGrauAmizade(int grauAmizade) {
        this.grauAmizade = grauAmizade;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    @Override
    public String toString() {
        return "Pessoa: " + nome + "\n" +
                "Endereco: " + endereco + "\n" +
                "Grau de Amizade: " + grauAmizade + "\n" +
                "Data de Nascimento: " + dtNascimento;
    }
}