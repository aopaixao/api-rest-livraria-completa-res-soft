package br.com.residencia.api_livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.api_livraria.entity.Editora;
import br.com.residencia.api_livraria.entity.Livro;

public interface LivroRepository
	extends JpaRepository<Livro,Integer>
{
	public List<Livro> findByEditora(Editora editora);
	
	public Livro findByNomeLivro(String nome);
}
