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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.residencia.api_livraria.dto.EditoraDTO;
import br.com.residencia.api_livraria.entity.Editora;
import br.com.residencia.api_livraria.service.EditoraService;

@RestController
@RequestMapping("/editoras")
public class EditoraController {
	@Autowired
	EditoraService editoraService;
	
	@GetMapping
	public ResponseEntity<List<EditoraDTO>> getAllEditoras(){
		return new ResponseEntity<>(editoraService.getAllEditoras(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EditoraDTO> getEditoraById(@PathVariable Integer id){
		EditoraDTO editoraDTO = editoraService.getEditoraById(id);
		if(editoraDTO != null) {
			return new ResponseEntity<>(editoraDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(editoraDTO, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
			 MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Editora> saveEditora(@RequestPart("editora") String editora,
			@RequestPart("source") MultipartFile file) throws IOException{
		
		Editora novaEditora = editoraService.saveEditora(editora, file); 
		if (null == novaEditora)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else
			return new ResponseEntity<>(novaEditora, HttpStatus.CREATED);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EditoraDTO> updateEditoraDTO(@RequestBody EditoraDTO editoraDTO, @PathVariable Integer id){
		return new ResponseEntity<>(editoraService.updateEditora(editoraDTO, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<EditoraDTO> deleteEditora(@PathVariable Integer id){
		EditoraDTO editoraDTO = editoraService.getEditoraById(id);
		if(editoraDTO != null) {
			return new ResponseEntity<>(editoraService.deleteEditora(id), HttpStatus.OK);
		} else {
			return null;
		}
		
	}

	@GetMapping("/count")
	public ResponseEntity<Long> count(){
		return new ResponseEntity<>(editoraService.count(), HttpStatus.OK);
	}
}
