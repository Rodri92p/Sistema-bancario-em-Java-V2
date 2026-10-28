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
	import javax.persistence.EnumType;
	import javax.persistence.Enumerated;
	
	
	@Entity
	@Table(name = "Transações")
	public class Movement {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TipoExtrato tipo;

	@Column(nullable = false)
	private LocalDateTime data;
	
	@Column(nullable = false)
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name = "destinatario_id", nullable = false)
	private Client destinatario;

	@ManyToOne
	@JoinColumn(name = "remetente_id", nullable = false)
	private Client remetente;
	
	public Movement() {
			
	}
	
	public Movement(TipoExtrato tipo, Client destinatario, Client remetente, BigDecimal valor) {
	  this.tipo = tipo;
	  this.destinatario = destinatario;
	  this.remetente = remetente;
	  this.valor = valor;
	  this.data = LocalDateTime.now();
	}
	

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public TipoExtrato getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoExtrato tipo) {
		this.tipo = tipo;
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
	
	public Client getDestinatario() {
		return destinatario;
	}
	
	public void setDestinatario(Client destinatario) {
		this.destinatario = destinatario;
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
