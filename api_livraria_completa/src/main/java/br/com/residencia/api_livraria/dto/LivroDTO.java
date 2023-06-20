package br.com.residencia.api_livraria.dto;

import java.sql.Types;
import java.time.Instant;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.Lob;

public class LivroDTO {
	private Integer codigoLivro;
	private String nomeLivro;
	private Instant dataLancamento;
	private Integer codigoIsbn;
	private String nomeImagem;
	private String nomeArquivoImagem;
	private EditoraDTO editoraDTO;
	private AutorDTO autorDTO;
	//@Lob
	//@JdbcTypeCode(Types.BINARY)
	@JdbcTypeCode(Types.BLOB)
	private byte[] imagem;
	private String img;

	public LivroDTO() {

	}

	public LivroDTO(Integer codigoLivro, String nomeLivro, Instant dataLancamento, Integer codigoIsbn,
			String nomeImagem, String nomeArquivoImagem, EditoraDTO editoraDTO, AutorDTO autorDTO, byte[] imagem) {

		super();

		this.codigoLivro = codigoLivro;
		this.nomeLivro = nomeLivro;
		this.dataLancamento = dataLancamento;
		this.codigoIsbn = codigoIsbn;
		this.nomeImagem = nomeImagem;
		this.nomeArquivoImagem = nomeArquivoImagem;
		this.editoraDTO = editoraDTO;
		this.autorDTO = autorDTO;
		this.imagem = imagem;

	}

	public LivroDTO(Integer codigoLivro, String nomeLivro, Instant dataLancamento, Integer codigoIsbn,
			String nomeImagem, String nomeArquivoImagem, byte[] imagem) {
		super();
		this.codigoLivro = codigoLivro;
		this.nomeLivro = nomeLivro;
		this.dataLancamento = dataLancamento;
		this.codigoIsbn = codigoIsbn;
		this.nomeImagem = nomeImagem;
		this.nomeArquivoImagem = nomeArquivoImagem;
		this.imagem = imagem;
	}
	
	public LivroDTO(Integer codigoLivro, String nomeLivro, Instant dataLancamento, Integer codigoIsbn,
			String nomeImagem, String nomeArquivoImagem, byte[] imagem, String img) {
		super();
		this.codigoLivro = codigoLivro;
		this.nomeLivro = nomeLivro;
		this.dataLancamento = dataLancamento;
		this.codigoIsbn = codigoIsbn;
		this.nomeImagem = nomeImagem;
		this.nomeArquivoImagem = nomeArquivoImagem;
		this.imagem = imagem;
		this.img = img;
	}

	public Integer getCodigoLivro() {
		return codigoLivro;
	}

	public void setCodigoLivro(Integer codigoLivro) {
		this.codigoLivro = codigoLivro;
	}

	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public Instant getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Instant dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Integer getCodigoIsbn() {
		return codigoIsbn;
	}

	public void setCodigoIsbn(Integer codigoIsbn) {
		this.codigoIsbn = codigoIsbn;
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

	public EditoraDTO getEditoraDTO() {
		return editoraDTO;
	}

	public void setEditoraDTO(EditoraDTO editoraDTO) {
		this.editoraDTO = editoraDTO;
	}

	public AutorDTO getAutorDTO() {
		return autorDTO;
	}

	public void setAutorDTO(AutorDTO autorDTO) {
		this.autorDTO = autorDTO;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}