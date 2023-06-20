package br.com.residencia.api_livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.api_livraria.entity.Editora;

public interface EditoraRepository extends
	JpaRepository<Editora, Integer>
{

}
