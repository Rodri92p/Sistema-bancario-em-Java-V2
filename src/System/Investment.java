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
private Boolean ativo;
	
@Column(nullable = false)
private LocalDate dataInvestimento;

public BigDecimal calcularValorAtual() {

    long dias = ChronoUnit.DAYS.between(dataInvestimento, LocalDate.now());

    double taxa = (tipo == TipoInvestimento.BRASIL) ? 0.12 : 0.03;

    double valor = valorInvestido.doubleValue()
            * Math.pow(1 + taxa / 365, dias);

    return BigDecimal.valueOf(valor);
}

public BigDecimal calcularValorizacao() {

    BigDecimal valorAtual = calcularValorAtual();

    BigDecimal lucro = valorAtual.subtract(valorInvestido);

    return lucro
            .divide(valorInvestido, 6, RoundingMode.HALF_UP)
            .multiply(BigDecimal.valueOf(100));
}
   

public Investment() {
    this.ativo = true;
    this.dataInvestimento = LocalDate.now();
    this.valorInvestido = BigDecimal.ZERO;
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


   
    
}
