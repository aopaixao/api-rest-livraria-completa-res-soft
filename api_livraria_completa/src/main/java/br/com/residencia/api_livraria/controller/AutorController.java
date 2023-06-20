package br.com.residencia.api_livraria.controller;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.residencia.api_livraria.dto.AutorDTO;
import br.com.residencia.api_livraria.service.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorController {
	@Autowired
	AutorService autorService;
	
	@GetMapping
	public ResponseEntity<List<AutorDTO>> getAllAutors(){
		return new ResponseEntity<>(autorService.getAllAutors(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AutorDTO> getAutorById(@PathVariable Integer id){
		AutorDTO autorDTO = autorService.getAutorById(id);
		if(autorDTO != null) {
			return new ResponseEntity<>(autorDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(autorDTO, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
			 MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<AutorDTO> saveAutorDTO(@RequestBody AutorDTO autorDTO){
		
		AutorDTO novaAutorDTO = autorService.saveAutor(autorDTO); 
		if (null == novaAutorDTO)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(novaAutorDTO, HttpStatus.CREATED);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AutorDTO> updateAutorDTO(@RequestBody AutorDTO autorDTO, @PathVariable Integer id){
		return new ResponseEntity<>(autorService.updateAutor(autorDTO, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<AutorDTO> deleteAutor(@PathVariable Integer id){
		AutorDTO autorDTO = autorService.getAutorById(id);
		if(autorDTO != null) {
			return new ResponseEntity<>(autorService.deleteAutor(id), HttpStatus.OK);
		} else {
			return null;
		}
		
	}

	@GetMapping("/count")
	public ResponseEntity<Long> count(){
		return new ResponseEntity<>(autorService.count(), HttpStatus.OK);
	}
}