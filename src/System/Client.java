package System;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Clientes")
public class Client {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column(nullable = false)
private double saldo;

@Column(unique = true, nullable = false, length = 11)
private String cpf;

@Column(unique = true, nullable = false)
private String email;

@Column(nullable = false)
private String nome;

@Column(nullable = false)
private String sobrenome;

@Column(nullable = false)
private String senha;

@Column(unique = true, nullable = true)
private String pix_cpf;

@Column(unique = true, nullable = true)
private String pix_email;

@Column(unique = true, nullable = true)
private String pix_celular;

@Column(unique = true, nullable = true)
private String pix_aleatorio;

@Column(nullable = true)
private Integer favorito1;

@Column(nullable = true)
private Integer favorito2;

@Column(nullable = true)
private Integer favorito3;

@Column(nullable = true)
private Integer favorito4;

@Column(nullable = true)
private Integer favorito5;

@Column(nullable = true)
private Integer favorito6;

@Column(nullable = true)
private Integer favorito7;

@Column(nullable = true)
private Integer favorito1tipo;

@Column(nullable = true)
private Integer favorito2tipo;

@Column(nullable = true)
private Integer favorito3tipo;

@Column(nullable = true)
private Integer favorito4tipo;

@Column(nullable = true)
private Integer favorito5tipo;

@Column(nullable = true)
private Integer favorito6tipo;

@Column(nullable = true)
private Integer favorito7tipo;


public void setFavorito7tipo(Integer favorito7tipo) {
	this.favorito7tipo = favorito7tipo;
}

@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
private List<Investment> investimentos;

@OneToMany(mappedBy = "cliente")
private List<Movement> movimentacoes;

public Client() {
	
}

public Client(String cpf, String nome, String sobrenome, String senha, String email) {
	  this.cpf = cpf;
	  this.nome = nome;
	  this.sobrenome = sobrenome;
	  this.senha = senha;
	  this.email = email;
}

public Client(String cpf, String nome, String email, String sobrenome, String senha, String pix_cpf, 
		      String pix_celular, String pix_email, String pix_aleatorio, double saldo)
{	
  this.cpf = cpf;
  this.nome = nome;
  this.sobrenome = sobrenome;
  this.senha = senha;
  this.email = email;
  this.pix_cpf = pix_cpf;
  this.pix_email = pix_email;
  this.pix_aleatorio = pix_aleatorio;
  this.pix_celular = pix_celular;
  this.saldo = saldo;
}

public boolean removerFavorito(int idFavorito, int tipoPix) {

    Integer[] favoritos = {
        favorito1,
        favorito2,
        favorito3,
        favorito4,
        favorito5,
        favorito6,
        favorito7
    };
    
    Integer[] tipos = {
        favorito1tipo,
        favorito2tipo,
        favorito3tipo,
        favorito4tipo,
        favorito5tipo,
        favorito6tipo,
        favorito7tipo
    };

    int indice = -1;

    for(int i = 0; i < favoritos.length; i++) {
    	if(favoritos[i] != null &&
    	    favoritos[i].equals(idFavorito) &&
    	    tipos[i] != null &&
    	    tipos[i].equals(tipoPix)) {
    		
    		indice = i;
        break;
      }
    }

    if(indice == -1) {
        return false;
    }

    for(int i = indice; i < favoritos.length - 1; i++) {
        favoritos[i] = favoritos[i + 1];
        tipos[i] = tipos[i + 1];
    }

    favoritos[6] = null;
    tipos[6] = null;

    favorito1 = favoritos[0];
    favorito2 = favoritos[1];
    favorito3 = favoritos[2];
    favorito4 = favoritos[3];
    favorito5 = favoritos[4];
    favorito6 = favoritos[5];
    favorito7 = favoritos[6];
    
    favorito1tipo = tipos[0];
    favorito2tipo = tipos[1];
    favorito3tipo = tipos[2];
    favorito4tipo = tipos[3];
    favorito5tipo = tipos[4];
    favorito6tipo = tipos[5];
    favorito7tipo = tipos[6];

    return true;
}

public boolean adicionarFavorito(int idFavorito, int tipoPix) {

    Integer[] favoritos = {
        favorito1,
        favorito2,
        favorito3,
        favorito4,
        favorito5,
        favorito6,
        favorito7
    };
    
    Integer[] tipos = {
        favorito1tipo,
        favorito2tipo,
        favorito3tipo,
        favorito4tipo,
        favorito5tipo,
        favorito6tipo,
        favorito7tipo
    };

    for(int i = 0; i < favoritos.length; i++) {

        if(favoritos[i] != null &&
           favoritos[i].equals(idFavorito) &&
           tipos[i] != null &&
           tipos[i].equals(tipoPix)) {

            return false; 
        }
    }

    for(int i = 0; i < favoritos.length; i++) {
    	
        if(favoritos[i] == null) {

            favoritos[i] = idFavorito;
            tipos[i] = tipoPix;
            
            favorito1 = favoritos[0];
            favorito2 = favoritos[1];
            favorito3 = favoritos[2];
            favorito4 = favoritos[3];
            favorito5 = favoritos[4];
            favorito6 = favoritos[5];
            favorito7 = favoritos[6];

            favorito1tipo = tipos[0];
            favorito2tipo = tipos[1];
            favorito3tipo = tipos[2];
            favorito4tipo = tipos[3];
            favorito5tipo = tipos[4];
            favorito6tipo = tipos[5];
            favorito7tipo = tipos[6];
            
            return true;
        }
    }

    return false;
}

public String getChavePixPrincipal(Integer tipo) {

switch(tipo) {

   case 1:
    	String cpf_pix = pix_cpf.substring(0, 3) + "." + pix_cpf.substring(3, 6) + "." + pix_cpf.substring(6, 9) + "-" + pix_cpf.substring(9, 11);
        return cpf_pix;
        
   case 2:
        return pix_email;
 
   case 3:     
    	String telefone_pix = "(" + pix_celular.substring(0, 2) + ")" + pix_celular.substring(2, 7) + "-" + pix_celular.substring(7, 11);
        return telefone_pix;
        
   case 4:
        return pix_aleatorio;
        
   default:  
        return "";
}

}

public String getChavePixTransferencia(Integer tipo) {
	
	switch(tipo) {
	
	case 1:
		return pix_cpf;
		
	case 2:
		return pix_email;
		
	case 3:     
        return pix_celular;
		
	case 4:
		return pix_aleatorio;
		
	default:  
		return "";
	}
	
}

public int getId() {
	return id;
}

public double getSaldo() {
	return saldo;
}

public void setSaldo(double saldo) {
	this.saldo = saldo;
}

public void setId(int id) {
	this.id = id;
}

public String getCpf() {
	return cpf;
}

public void setCpf(String cpf) {
	this.cpf = cpf;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getSenha() {
	return senha;
}

public void setSenha(String senha) {
	this.senha = senha;
}

public String getSobrenome() {
	return sobrenome;
}

public void setSobrenome(String sobrenome) {
	this.sobrenome = sobrenome;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPix_cpf() {
	return pix_cpf;
}

public void setPix_cpf(String pix_cpf) {
	this.pix_cpf = pix_cpf;
}

public String getPix_email() {
	return pix_email;
}

public void setPix_email(String pix_email) {
	this.pix_email = pix_email;
}

public String getPix_celular() {
	return pix_celular;
}

public void setPix_celular(String pix_celular) {
	this.pix_celular = pix_celular;
}

public String getPix_aleatorio() {
	return pix_aleatorio;
}

public void setPix_aleatorio(String pix_aleatorio) {
	this.pix_aleatorio = pix_aleatorio;
}

public List<Movement> getMovimentacoes() {
	return movimentacoes;
}

public void setMovimentacoes(List<Movement> movimentacoes) {
	this.movimentacoes = movimentacoes;
}

public Integer getFavorito1() {
	return favorito1;
}

public void setFavorito1(Integer favorito1) {
	this.favorito1 = favorito1;
}

public Integer getFavorito2() {
	return favorito2;
}

public void setFavorito2(Integer favorito2) {
	this.favorito2 = favorito2;
}

public Integer getFavorito3() {
	return favorito3;
}

public void setFavorito3(Integer favorito3) {
	this.favorito3 = favorito3;
}

public Integer getFavorito4() {
	return favorito4;
}

public void setFavorito4(Integer favorito4) {
	this.favorito4 = favorito4;
}

public Integer getFavorito5() {
	return favorito5;
}

public void setFavorito5(Integer favorito5) {
	this.favorito5 = favorito5;
}

public Integer getFavorito6() {
	return favorito6;
}

public void setFavorito6(Integer favorito6) {
	this.favorito6 = favorito6;
}

public Integer getFavorito7() {
	return favorito7;
}

public void setFavorito7(Integer favorito7) {
	this.favorito7 = favorito7;
}

public Integer getFavorito1tipo() {
	return favorito1tipo;
}

public void setFavorito1tipo(Integer favorito1tipo) {
	this.favorito1tipo = favorito1tipo;
}

public Integer getFavorito2tipo() {
	return favorito2tipo;
}

public void setFavorito2tipo(Integer favorito2tipo) {
	this.favorito2tipo = favorito2tipo;
}

public Integer getFavorito3tipo() {
	return favorito3tipo;
}

public void setFavorito3tipo(Integer favorito3tipo) {
	this.favorito3tipo = favorito3tipo;
}

public Integer getFavorito4tipo() {
	return favorito4tipo;
}

public void setFavorito4tipo(Integer favorito4tipo) {
	this.favorito4tipo = favorito4tipo;
}

public Integer getFavorito5tipo() {
	return favorito5tipo;
}

public void setFavorito5tipo(Integer favorito5tipo) {
	this.favorito5tipo = favorito5tipo;
}

public Integer getFavorito6tipo() {
	return favorito6tipo;
}

public void setFavorito6tipo(Integer favorito6tipo) {
	this.favorito6tipo = favorito6tipo;
}

public Integer getFavorito7tipo() {
	return favorito7tipo;
}

}
