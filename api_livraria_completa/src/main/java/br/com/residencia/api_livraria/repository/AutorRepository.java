package br.com.residencia.api_livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.api_livraria.entity.Autor;

public interface AutorRepository extends
	JpaRepository<Autor, Integer>
{

}
