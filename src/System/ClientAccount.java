package System;

public class ClientAccount {

private String nome;
private String cpf;
private String senha;
private String pix;
private double saldo;


//Construtor Padrão

public ClientAccount(String nome, String cpf, String senha, double saldo, String pix) {
  this.cpf = cpf;
  this.nome = nome;
  this.senha = senha;
  this.saldo = saldo;
  this.pix = pix;
}

//Getters && Setters

public String getPix() {
	return pix;
}

public String getSenha() {
	return senha;
}

public double getSaldo() {
	return saldo;
}

public String getNome() {
	return nome;
}

public String getCpf() {
	return cpf;
}

public void setPix(String pix) {
	this.pix = pix;
}

//classes

public void depositar(double valor) {
	
  if (valor<=0) {
  return;	
  }
  
  saldo += valor;
}

public void sacar(double valor) {
	
  if (valor<=0) {
  return;
  }

  if (valor>saldo) {
  return;
  }
  
  saldo -= valor;
}

public void enviar(double valor) {
	  saldo -= valor;
}

public void receber(double valor) {
	  saldo += valor;
}

}