package br.com.residencia.api_livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.api_livraria.dto.AutorDTO;
import br.com.residencia.api_livraria.dto.LivroDTO;
import br.com.residencia.api_livraria.entity.Autor;
import br.com.residencia.api_livraria.repository.AutorRepository;

@Service
public class AutorService {
	@Autowired
	AutorRepository autorRepository;

	public List<AutorDTO> getAllAutors(){
		List<Autor> listaAutor = autorRepository.findAll();
		List<AutorDTO> listaAutorDTO = new ArrayList<>();
		
		for(Autor autor: listaAutor) {
			AutorDTO autorDTO = toDTO(autor);
			listaAutorDTO.add(autorDTO);
		}
		
		return listaAutorDTO;
	}
	
	public AutorDTO getAutorById(Integer id) {
		Autor autor = autorRepository.findById(id).orElse(null);
		AutorDTO autorDTO = new AutorDTO();
		
		if(autor != null)
			autorDTO = toDTO(autor);
		
		return autorDTO;
	}
	
	public AutorDTO saveAutor(AutorDTO autorDTO) {
		return toDTO(autorRepository.save(toEntidade(autorDTO)));
	}	
	
	public AutorDTO updateAutor(AutorDTO autorDTO, Integer id) {
		Autor autorExistenteNoBanco = toEntidade(getAutorById(id));
		AutorDTO autorAtualizadaDTO = new AutorDTO();
		
		if(autorExistenteNoBanco != null) {
			autorDTO.setCodigoAutor(autorExistenteNoBanco.getCodigoAutor());
			autorExistenteNoBanco = toEntidade(autorDTO);
			
			Autor autorAtualizada = autorRepository.save(autorExistenteNoBanco);
			
			autorAtualizadaDTO = toDTO(autorAtualizada);
		}
		
		return autorAtualizadaDTO;
	}
	
	public AutorDTO deleteAutor(Integer id) {
		if(getAutorById(id) != null) {
			autorRepository.deleteById(id);
		}
		
		return getAutorById(id);	
	}
	
	public Long count() {
		return autorRepository.count();
	}
	
	private Autor toEntidade(AutorDTO autorDTO ) {
		Autor autor = new Autor();
		BeanUtils.copyProperties(autorDTO, autor);
		
		return autor;
	}
	
	private AutorDTO toDTO(Autor autor) {
		AutorDTO autorDTO = new AutorDTO();
		List<LivroDTO> listaLivrosDTO = new ArrayList<>();
		
		BeanUtils.copyProperties(autor, autorDTO);
		
		if(null != autor.getLivros()) {
			listaLivrosDTO = autor.getLivros().stream()
			        .map(entity -> new LivroDTO(entity.getCodigoLivro(), entity.getNomeLivro(), entity.getDataLancamento(),
			        		entity.getCodigoIsbn(), entity.getNomeImagem(), entity.getNomeArquivoImagem(), entity.getImagem()))
			        .collect(Collectors.toList());
			
			autorDTO.setLivrosDTO(listaLivrosDTO);
		}
		return autorDTO;
	}
}