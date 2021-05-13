package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
//import javax.validation.Valid;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.PositiveOrZero;
//import javax.validation.groups.ConvertGroup;
//import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

//import com.algaworks.algafood.core.validation.Groups;
import com.algaworks.algafood.core.validation.ValorZeroIncluiDescricao;

import lombok.Data;
import lombok.EqualsAndHashCode;

@ValorZeroIncluiDescricao(valorField = "taxaFrete", 
		descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
//	@NotNull  				// não permite o nome nulo
//	@NotEmpty 				// não permite nulo, nem vazio
//	@NotBlank				// não permite nulo, vazio e nem carácteres em branco
	@Column(nullable = false)
	private String nome;
	
//	@TaxaFrete
//	@DecimalMin("0")
//	@Multiplo(numero = 5)
//	@PositiveOrZero  		// esta anotação é igual a da linha de cima
//	@NotNull
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

//	@Valid
//	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
//	@NotNull
	@ManyToOne 				// (fetch = FetchType.LAZY)
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;
	
	@Embedded
	private Endereco endereco;
	
	@Column(nullable = false)
	private Boolean ativo = Boolean.TRUE;
	
	@Column(nullable = false)
	private Boolean aberto = Boolean.FALSE;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;
	
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento", 
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento = new HashSet<>();
	
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "restaurante_usuario_responsavel",
	        joinColumns = @JoinColumn(name = "restaurante_id"),
	        inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> responsaveis = new HashSet<>();        
	
	public void ativar() {
		setAtivo(true);
	}
	
	public void inativar() {
		setAtivo(false);
	}
	
	public void abrir() {
		setAberto(true);
	}
	
	public void fechar() {
		setAberto(false);
	}
	
	public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().remove(formaPagamento);
	}
	
	public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().add(formaPagamento);
	}
	
	public boolean removerProduto(Produto produto) {
		return getProdutos().remove(produto);
	}
	
	public boolean adicionarProduto(Produto produto) {
		return getProdutos().add(produto);
	}
	
	public boolean removerResponsavel(Usuario usuario) {
	    return getResponsaveis().remove(usuario);
	}

	public boolean adicionarResponsavel(Usuario usuario) {
	    return getResponsaveis().add(usuario);
	}
	
	public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
	    return getFormasPagamento().contains(formaPagamento);
	}

	public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
	    return !aceitaFormaPagamento(formaPagamento);
	}
	
}
