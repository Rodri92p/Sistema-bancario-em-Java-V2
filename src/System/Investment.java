package System;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Investimento")
public class Investment {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "cliente_id")
private Client cliente;

@Enumerated(EnumType.STRING)
@Column(nullable = false)
private TipoInvestimento tipo;		

@Column(nullable = false)
private BigDecimal valorInvestido;

@Column(nullable = false)
private BigDecimal valorRendido;

@Column(nullable = false)
private BigDecimal valorTotal;

@Column(nullable = false)
private Boolean ativo;
	
@Column(nullable = false)
private LocalDate dataInvestimento;

public BigDecimal calcularValorAtual() {

    LocalDate hoje = LocalDate.now();

    long dias = ChronoUnit.DAYS.between(dataInvestimento, hoje);

    BigDecimal taxa;

    if (tipo == TipoInvestimento.BRASIL) 
         { taxa = new BigDecimal("0.12"); } 
    else 
         { taxa = new BigDecimal("0.03"); }

    BigDecimal taxaDiaria = taxa.divide(new BigDecimal("365"),20,RoundingMode.HALF_UP);

    BigDecimal fator = BigDecimal.ONE.add(taxaDiaria);

    BigDecimal valorTotalAtual = valorTotal.multiply(BigDecimal.valueOf(Math.pow(fator.doubleValue(), dias)));

    return valorTotalAtual.setScale(2, RoundingMode.HALF_UP);
}


public BigDecimal calcularValorizacao() {

    BigDecimal valorAtual = calcularValorAtual();

    return valorAtual.subtract(valorInvestido).setScale(2, RoundingMode.HALF_UP);
}
   

public Investment() {
    this.ativo = true;
    this.dataInvestimento = LocalDate.now();
    this.valorInvestido = BigDecimal.ZERO;
}

public Investment(Boolean ativo, LocalDate data, BigDecimal valor, TipoInvestimento investimento) {
	this.ativo = ativo;
	this.dataInvestimento = data;
	this.valorInvestido = valor;
	this.tipo = investimento;
}


public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public Client getCliente() {
	return cliente;
}

public void setCliente(Client cliente) {
	this.cliente = cliente;
}

public TipoInvestimento getTipo() {
	return tipo;
}

public void setTipo(TipoInvestimento tipo) {
	this.tipo = tipo;
}

public BigDecimal getValorInvestido() {
	return valorInvestido;
}

public Boolean getAtivo() {
	return ativo;
}

public void setAtivo(Boolean ativo) {
	this.ativo = ativo;
}

public void setValorInvestido(BigDecimal valorInvestido) {
	this.valorInvestido = valorInvestido;
}

public LocalDate getDataInvestimento() {
	return dataInvestimento;
}

public void setDataInvestimento(LocalDate dataInvestimento) {
	this.dataInvestimento = dataInvestimento;
}

public BigDecimal getValorRendido() {
	return valorRendido;
}

public void setValorRendido(BigDecimal valorRendido) {
	this.valorRendido = valorRendido;
}


public BigDecimal getValorTotal() {
	return valorTotal;
}


public void setValorTotal(BigDecimal valorTotal) {
	this.valorTotal = valorTotal;
}
   
    
}
