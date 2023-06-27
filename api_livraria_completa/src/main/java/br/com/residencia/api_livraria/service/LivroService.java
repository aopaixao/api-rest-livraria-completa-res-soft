package br.com.residencia.api_livraria.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.residencia.api_livraria.dto.AutorDTO;
import br.com.residencia.api_livraria.dto.EditoraDTO;
import br.com.residencia.api_livraria.dto.LivroDTO;
import br.com.residencia.api_livraria.entity.Autor;
import br.com.residencia.api_livraria.entity.Editora;
import br.com.residencia.api_livraria.entity.Livro;
import br.com.residencia.api_livraria.repository.EditoraRepository;
import br.com.residencia.api_livraria.repository.LivroRepository;

@Service
public class LivroService {
	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	EditoraRepository editoraRepository;
	
	public List<Livro> getAllLivros(){
		List<Livro> livros = livroRepository.findAll();
		List<Livro> listaLivros = new ArrayList<>();
		for(Livro lLivro:livros) {
			Livro cLivro = lLivro;
			cLivro.setImg(Base64.getEncoder().encodeToString(cLivro.getImagem()));
			listaLivros.add(cLivro);
		}
		return listaLivros;
	}
	
	public List<LivroDTO> getAllLivrosDTO(Integer pagina, Integer qtdRegistros){
		Pageable page = null;
		List<Livro> listaLivro = new ArrayList<>();
		List<LivroDTO> listaLivroDTO = new ArrayList<>();
		
		if (pagina != null && qtdRegistros != null) {
			page = PageRequest.of(pagina, qtdRegistros);
			listaLivro = livroRepository.findAll(page).getContent();
		}else {
			listaLivro = livroRepository.findAll();
		}
		
		for(Livro livro: listaLivro) {
			LivroDTO livroDTO = toDTO(livro);
			listaLivroDTO.add(livroDTO);
		}
		
		return listaLivroDTO;
	}
	
	public LivroDTO getLivroById(Integer id) {
		Livro livro = livroRepository.findById(id).orElse(null);
		LivroDTO livroDTO = new LivroDTO();
		
		if(livro != null)
			livroDTO = toDTO(livro);
		
		return livroDTO;
	}
	
	public List<LivroDTO> getAllLivrosByEditora(Integer codigoEditora,
			Integer pagina, Integer qtdRegistros) {
		
		Editora editora = editoraRepository.findById(codigoEditora).orElse(null);
		Pageable page = null;
		List<Livro> listaLivros = new ArrayList<>();
		List<LivroDTO> listaLivrosDTO = new ArrayList<>();
		
		if(editora != null) {
			if (pagina != null && qtdRegistros != null) {
				page = PageRequest.of(pagina, qtdRegistros);
				listaLivros = livroRepository.findByEditora(editora, page);
			}else {
				listaLivros = livroRepository.findByEditora(editora);
			}
		}

		if(!listaLivros.isEmpty()) 
			listaLivros = listaLivros.stream()
			        .map(entity -> new Livro(entity.getCodigoLivro(), entity.getNomeLivro(), entity.getDataLancamento(),
			        		entity.getCodigoIsbn(), entity.getNomeImagem(), entity.getNomeArquivoImagem(), entity.getImagem(), Base64.getEncoder().encodeToString(entity.getImagem())))
			        .collect(Collectors.toList());
		
		
		if(!listaLivros.isEmpty()) 
			listaLivrosDTO = listaLivros.stream()
			        .map(entity -> new LivroDTO(entity.getCodigoLivro(), entity.getNomeLivro(), entity.getDataLancamento(),
			        		entity.getCodigoIsbn(), entity.getNomeImagem(), entity.getNomeArquivoImagem(), entity.getImagem(), entity.getImg()))
			        .collect(Collectors.toList());
		
		return listaLivrosDTO;
	}
	
	public Livro saveLivro(
			String livro,
			MultipartFile file
	) throws IOException {
		
		Livro livroFromJson = convertLivroFromStringJson(livro);
		Livro novoLivro = new Livro();
		if(null != livroFromJson.getAutor()) {
			livroFromJson.setNomeArquivoImagem(file.getOriginalFilename());
			livroFromJson.setImagem(file.getBytes());;
			novoLivro = livroRepository.save(livroFromJson);
		}
		return novoLivro;
	}	
	
	public LivroDTO updateLivro(LivroDTO livroDTO, Integer id) {
		Livro livroExistenteNoBanco = toEntidade(getLivroById(id));
		LivroDTO livroAtualizadaDTO = new LivroDTO();
		
		if(livroExistenteNoBanco != null) {
			livroDTO.setCodigoLivro(livroExistenteNoBanco.getCodigoLivro());
			livroExistenteNoBanco = toEntidade(livroDTO);
			
			Livro livroAtualizada = livroRepository.save(livroExistenteNoBanco);
			
			livroAtualizadaDTO = toDTO(livroAtualizada);
		}
		
		return livroAtualizadaDTO;
	}
	
	public LivroDTO deleteLivro(Integer id) {
		if(getLivroById(id) != null) {
			livroRepository.deleteById(id);
		}
		
		return getLivroById(id);	
	}
	
	public Long count() {
		return livroRepository.count();
	}
	
	private Livro toEntidade(LivroDTO livroDTO ) {
		Livro livro = new Livro();
		Editora editora = new Editora();
		Autor autor = new Autor();
		
		BeanUtils.copyProperties(livroDTO, livro);
		if(null != livroDTO.getEditoraDTO())
			BeanUtils.copyProperties(livroDTO.getEditoraDTO(), editora);
		
		if(null != livroDTO.getAutorDTO())
			BeanUtils.copyProperties(livroDTO.getAutorDTO(), autor);
		
		livro.setEditora(editora);
		livro.setAutor(autor);
		
		return livro;
	}
	
	public LivroDTO toDTO(Livro livro) {
		LivroDTO livroDTO = new LivroDTO();
		EditoraDTO editoraDTO = new EditoraDTO();
		AutorDTO autorDTO = new AutorDTO();
		
		BeanUtils.copyProperties(livro, livroDTO);
		if(null != livro.getEditora())
			BeanUtils.copyProperties(livro.getEditora(), editoraDTO);
		if(null != livro.getAutor())
			BeanUtils.copyProperties(livro.getAutor(), autorDTO);
		
		livroDTO.setImg(Base64.getEncoder().encodeToString(livro.getImagem()));
		
		livroDTO.setEditoraDTO(editoraDTO);
		livroDTO.setAutorDTO(autorDTO);
		
		return livroDTO;
	}
	
	private Livro convertLivroFromStringJson(String livroJson) {
		Livro livro = new Livro();
		
		try {
			ObjectMapper objectMapper = 
					new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);

			objectMapper.registerModule(new JavaTimeModule());
			livro = objectMapper.readValue(livroJson, Livro.class);
		} catch (IOException err) {
			System.out.printf("Ocorreu um erro ao tentar converter a string json para um instância de Livro: " + err.toString());
		}
		
		return livro;
	}
}