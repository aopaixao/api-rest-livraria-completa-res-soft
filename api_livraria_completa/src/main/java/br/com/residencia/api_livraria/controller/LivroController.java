package br.com.residencia.api_livraria.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.residencia.api_livraria.dto.LivroDTO;
import br.com.residencia.api_livraria.entity.Livro;
import br.com.residencia.api_livraria.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {
	@Autowired
	LivroService livroService;
	
	@GetMapping("/entity")
	public ResponseEntity<List<Livro>> getAllLivrosEntity(){
		return new ResponseEntity<>(livroService.getAllLivros(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<LivroDTO>> getAllLivros(@RequestParam(required = false) 
			Integer pagina, @RequestParam(required = false) Integer qtdRegistros){
		return new ResponseEntity<>(livroService.getAllLivrosDTO(pagina, qtdRegistros), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LivroDTO> getLivroById(@PathVariable Integer id){
		LivroDTO livroDTO = livroService.getLivroById(id);
		if(livroDTO != null) {
			return new ResponseEntity<>(livroDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(livroDTO, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/por-editora/{codigoEditora}")
	public ResponseEntity<List<LivroDTO>> getAllLivrosByEditora(@PathVariable Integer codigoEditora,
			@RequestParam(required = false) Integer pagina, 
			@RequestParam(required = false) Integer qtdRegistros
			){
		return new ResponseEntity<>(livroService.getAllLivrosByEditora(codigoEditora, pagina, qtdRegistros),
				HttpStatus.OK);
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
			 MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Livro> saveLivroDTO(@RequestPart("livro") String livro,
			@RequestPart("source") MultipartFile file) throws IOException{
		
		Livro novoLivro = livroService.saveLivro(livro, file); 
		if (null == novoLivro)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(novoLivro, HttpStatus.CREATED);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<LivroDTO> updateLivroDTO(@RequestBody LivroDTO livroDTO, @PathVariable Integer id){
		return new ResponseEntity<>(livroService.updateLivro(livroDTO, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<LivroDTO> deleteLivro(@PathVariable Integer id){
		LivroDTO livroDTO = livroService.getLivroById(id);
		if(livroDTO != null) {
			return new ResponseEntity<>(livroService.deleteLivro(id), HttpStatus.OK);
		} else {
			return null;
		}
		
	}

	@GetMapping("/count")
	public ResponseEntity<Long> count(){
		return new ResponseEntity<>(livroService.count(), HttpStatus.OK);
	}
}