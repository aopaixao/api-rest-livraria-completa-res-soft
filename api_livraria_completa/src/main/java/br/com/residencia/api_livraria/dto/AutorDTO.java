package br.com.residencia.api_livraria.dto;

import java.util.List;
import java.util.Set;

public class AutorDTO {
	private Integer codigoAutor;
	private String nomeAutor;
	private List<LivroDTO> livrosDTO;

	public AutorDTO() {
	}

	public AutorDTO(Integer codigoAutor, String nomeAutor, List<LivroDTO> livrosDTO) {
		super();
		this.codigoAutor = codigoAutor;
		this.nomeAutor = nomeAutor;
		this.livrosDTO = livrosDTO;
	}

	public Integer getCodigoAutor() {
		return codigoAutor;
	}

	public void setCodigoAutor(Integer codigoAutor) {
		this.codigoAutor = codigoAutor;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public List<LivroDTO> getLivrosDTO() {
		return livrosDTO;
	}

	public void setLivrosDTO(List<LivroDTO> livrosDTO) {
		this.livrosDTO = livrosDTO;
	}
}