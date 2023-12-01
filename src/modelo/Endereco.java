package modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    private String rua;
    
    private int numero;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Bairro bairro;

    public Endereco() {}
    public Endereco(String rua, int numero, Bairro bairro) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Bairro getBairro() {
        return bairro;
    }

    public void setBairro(Bairro bairro) {
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return "Endereço: " + rua + ", " + numero + ", " + bairro;
    }
}