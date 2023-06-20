package br.com.residencia.api_livraria.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.residencia.api_livraria.dto.EditoraDTO;
import br.com.residencia.api_livraria.dto.LivroDTO;
import br.com.residencia.api_livraria.entity.Editora;
import br.com.residencia.api_livraria.entity.Livro;
import br.com.residencia.api_livraria.repository.EditoraRepository;
import br.com.residencia.api_livraria.repository.LivroRepository;

@Service
public class EditoraService {
	@Autowired
	EditoraRepository editoraRepository;
	
	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	LivroService livroService;
	
	public List<EditoraDTO> getAllEditoras(){
		List<Editora> listaEditora = editoraRepository.findAll();
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();
		
		for(Editora editora: listaEditora) {
			EditoraDTO editoraDTO = toDTO(editora);
			listaEditoraDTO.add(editoraDTO);
		}
		
		return listaEditoraDTO;
	}
	
	public EditoraDTO getEditoraById(Integer id) {
		Editora editora = editoraRepository.findById(id).orElse(null);
		EditoraDTO editoraDTO = new EditoraDTO();
		
		if(editora != null)
			editoraDTO = toDTO(editora);
		
		return editoraDTO;
	}
	
	public Editora saveEditora(
			String editora,
			MultipartFile file
	) throws IOException {
		Editora novaEditora = new Editora(); 
		Editora editoraFromJson = convertEditoraFromStringJson(editora);
		if(null != editoraFromJson.getNomeEditora()) {
			editoraFromJson.setNomeArquivoImagem(file.getOriginalFilename());
			editoraFromJson.setImagem(file.getBytes());
		}
		novaEditora = editoraRepository.save(editoraFromJson);
		
		return novaEditora;
	}	
	
	public EditoraDTO updateEditora(EditoraDTO editoraDTO, Integer id) {
		Editora editoraExistenteNoBanco = toEntidade(getEditoraById(id));
		EditoraDTO editoraAtualizadaDTO = new EditoraDTO();
		
		if(editoraExistenteNoBanco != null) {
			editoraDTO.setCodigoEditora(editoraExistenteNoBanco.getCodigoEditora());
			editoraExistenteNoBanco = toEntidade(editoraDTO);
			
			Editora editoraAtualizada = editoraRepository.save(editoraExistenteNoBanco);
			
			editoraAtualizadaDTO = toDTO(editoraAtualizada);
		}
		
		return editoraAtualizadaDTO;
	}
	
	public EditoraDTO deleteEditora(Integer id) {
		if(getEditoraById(id) != null) {
			editoraRepository.deleteById(id);
		}
		
		return getEditoraById(id);	
	}
	
	public Long count() {
		return editoraRepository.count();
	}
	
	private Editora toEntidade(EditoraDTO editoraDTO ) {
		Editora editora = new Editora();
		BeanUtils.copyProperties(editoraDTO, editora);
		
		return editora;
	}
	
	private EditoraDTO toDTO(Editora editora) {
		EditoraDTO editoraDTO = new EditoraDTO();
		List<LivroDTO> listaLivrosDTO = new ArrayList<>();
		
		BeanUtils.copyProperties(editora, editoraDTO);
		editoraDTO.setImg(Base64.getEncoder().encodeToString(editoraDTO.getImagem()));
		listaLivrosDTO = editora.getLivros().stream()
	        .map(entity -> new LivroDTO(entity.getCodigoLivro(), entity.getNomeLivro(), entity.getDataLancamento(),
	        		entity.getCodigoIsbn(), entity.getNomeImagem(), entity.getNomeArquivoImagem(), entity.getImagem()))
	        .collect(Collectors.toList());
		
		editoraDTO.setListaLivrosDTO(listaLivrosDTO);
		return editoraDTO;
	}
	
	public List<EditoraDTO> getAllEditorasLivrosDTO(){
		List<Editora> listaEditora = editoraRepository.findAll();
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();
		
		for(Editora editora: listaEditora) {
			EditoraDTO editoraDTO = toDTO(editora);
			List<Livro> listaLivros = new ArrayList<>();
			List<LivroDTO> listaLivrosDTO = new ArrayList<>();
			
			listaLivros = livroRepository.findByEditora(editora);
			for(Livro livro : listaLivros) {
				LivroDTO livroDTO = livroService.toDTO(livro);
				listaLivrosDTO.add(livroDTO);
			}
			editoraDTO.setListaLivrosDTO(listaLivrosDTO);
			
			listaEditoraDTO.add(editoraDTO);
		}
		
		return listaEditoraDTO;
	}
	
	private Editora convertEditoraFromStringJson(String editoraJson) {
		Editora editora = new Editora();
		
		try {
			ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
					false);

			objectMapper.registerModule(new JavaTimeModule());
			editora = objectMapper.readValue(editoraJson, Editora.class);
		} catch (IOException err) {
			System.out.printf("Ocorreu um erro ao tentar converter a string json para um instância de Editora", err.toString());
		}
		
		return editora;
	}
}