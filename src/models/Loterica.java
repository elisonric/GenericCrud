package models;

import annotations.Column;
import annotations.Id;
import annotations.Table;

@Table("loterica")
public class Loterica {
	@Id
	@Column(value = "id")
	private Long id;
	@Column("nome")
	private String nome;
	
	
	public Loterica(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
