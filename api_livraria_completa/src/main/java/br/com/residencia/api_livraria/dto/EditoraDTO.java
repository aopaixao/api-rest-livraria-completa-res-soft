package br.com.residencia.api_livraria.dto;

import java.sql.Blob;
import java.sql.Types;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ext.SqlBlobSerializer;

public class EditoraDTO {

	private Integer codigoEditora;
	private String nomeEditora;
	private String nomeImagem;
	private String nomeArquivoImagem;
	//@Lob
	//@JdbcTypeCode(Types.BINARY)
	@JdbcTypeCode(Types.BLOB)
	private byte[] imagem;
	private String img;
	private List<LivroDTO> listaLivrosDTO;

	public EditoraDTO() {
	}

	public EditoraDTO(Integer codigoEditora, String nomeEditora, String nomeImagem, String nomeArquivoImagem,
			byte[] imagem, List<LivroDTO> listaLivrosDTO) {
		super();
		this.codigoEditora = codigoEditora;
		this.nomeEditora = nomeEditora;
		this.nomeImagem = nomeImagem;
		this.nomeArquivoImagem = nomeArquivoImagem;
		this.imagem = imagem;
		this.listaLivrosDTO = listaLivrosDTO;
	}
	
	public EditoraDTO(Integer codigoEditora, String nomeEditora, String nomeImagem, String nomeArquivoImagem,
			String img, List<LivroDTO> listaLivrosDTO) {
		super();
		this.codigoEditora = codigoEditora;
		this.nomeEditora = nomeEditora;
		this.nomeImagem = nomeImagem;
		this.nomeArquivoImagem = nomeArquivoImagem;
		this.img = img;
		this.listaLivrosDTO = listaLivrosDTO;
	}

	public Integer getCodigoEditora() {
		return codigoEditora;
	}

	public void setCodigoEditora(Integer codigoEditora) {
		this.codigoEditora = codigoEditora;
	}

	public String getNomeEditora() {
		return nomeEditora;
	}

	public void setNomeEditora(String nomeEditora) {
		this.nomeEditora = nomeEditora;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public String getNomeArquivoImagem() {
		return nomeArquivoImagem;
	}

	public void setNomeArquivoImagem(String nomeArquivoImagem) {
		this.nomeArquivoImagem = nomeArquivoImagem;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<LivroDTO> getListaLivrosDTO() {
		return listaLivrosDTO;
	}

	public void setListaLivrosDTO(List<LivroDTO> listaLivrosDTO) {
		this.listaLivrosDTO = listaLivrosDTO;
	}
}