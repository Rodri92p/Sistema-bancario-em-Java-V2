package System;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Transações")
public class Movement {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@Column( nullable = false)
private String tipo;

@Column(nullable = false)
private String direcao;

@Column(nullable = false)
private LocalDateTime data;

@Column(nullable = false)
private BigDecimal valor;

@ManyToOne
@JoinColumn(name = "remetente_id")
private Client remetente;

@ManyToOne
@JoinColumn(name = "cliente_id", nullable = false)
private Client cliente;

public Movement() {
		
}

public Movement(String tipo, String movimento, Client cliente, Client remetente, BigDecimal valor) {
  this.tipo = tipo;
  this.direcao = movimento;
  this.remetente = remetente;
  this.valor = valor;
  this.cliente = cliente;
  this.data = LocalDateTime.now();
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getTipo() {
	return tipo;
}

public void setTipo(String tipo) {
	this.tipo = tipo;
}

public String getDirecao() {
	return direcao;
}

public void setDirecao(String movimento) {
	this.direcao = movimento;
}

public BigDecimal getValor() {
	return valor;
}

public LocalDateTime getData() {
	return data;
}

public void setData(LocalDateTime data) {
	this.data = data;
}

public Client getCliente() {
	return cliente;
}

public void setCliente(Client cliente) {
	this.cliente = cliente;
}

public void setValor(BigDecimal valor) {
	this.valor = valor;
}

public Client getRemetente() {
	return remetente;
}

public void setRemetente(Client remetente) {
	this.remetente = remetente;
}
}
