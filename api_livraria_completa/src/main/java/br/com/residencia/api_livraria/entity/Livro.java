package br.com.residencia.api_livraria.entity;

import java.sql.Types;
import java.time.Instant;

import org.hibernate.annotations.JdbcTypeCode;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "livro")
@JsonIdentityInfo(
	generator = ObjectIdGenerators.PropertyGenerator.class,
	property = "codigoLivro",
	scope = Livro.class
)
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_livro")
	private Integer codigoLivro;

	@Column(name = "nome_livro")
	private String nomeLivro;

	@Column(name = "data_lancamento")
	private Instant dataLancamento;

	@Column(name = "codigo_isbn")
	private Integer codigoIsbn;

	@Column(name = "nome_imagem")
	private String nomeImagem;

	@Column(name = "nome_arquivo_imagem")
	private String nomeArquivoImagem;

	//@Lob
	//@JdbcTypeCode(Types.BINARY)
	@JdbcTypeCode(Types.BLOB)
	private byte[] imagem;
	
	private String img;

	@ManyToOne
	@JoinColumn(name = "codigo_editora", referencedColumnName = "codigo_editora")
	private Editora editora;
	
	@ManyToOne
	@JoinColumn(name = "codigo_autor", referencedColumnName = "codigo_autor")
	private Autor autor;
	
	public Livro() {
	}

	public Livro(Integer codigoLivro, String nomeLivro, Instant dataLancamento, Integer codigoIsbn, String nomeImagem,
			String nomeArquivoImagem, byte[] imagem, String img) {
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

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

}