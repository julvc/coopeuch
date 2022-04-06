package com.coopeuch.jvc.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coopeuch.jvc.app.entity.Tarea;
import com.coopeuch.jvc.app.exception.ResourceNotFoundException;
import com.coopeuch.jvc.app.repository.TareaRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class TareaController {

    @Autowired
    private TareaRepository tareaRepositorio;

    @Operation(summary = "Permite obtener el listado completo de registros disponibles")
    @ApiResponses(value = { 
    @ApiResponse(responseCode = "200", description = "Devuelve los valores disponibles, de no haber ninguno devuelve 0 []", content = { @Content(mediaType = "application/json", 
    		      schema = @Schema(implementation = Tarea.class)) }), })
    @GetMapping("/tareas")
    public List<Tarea> getAllTareas(){
        return tareaRepositorio.findAll();
    }
    
    @Operation(summary = "Permite crear una nueva tarea")
    @ApiResponses(value = { 
    @ApiResponse(responseCode = "200", description = "Ha creado un nuevo registro", content = { @Content(mediaType = "application/json", 
    		      schema = @Schema(implementation = Tarea.class)) }),
    @ApiResponse(responseCode = "400", description = "Hubo una excepción o error al momento de crear tarea nueva", content = @Content), })
    @PostMapping("/tareas")
    public Tarea crearTarea(@RequestBody @Valid Tarea tarea) {
    	return tareaRepositorio.save(tarea);
    }
    
    @Operation(summary = "Permite obtener una tarea por id")
    @ApiResponses(value = { 
    @ApiResponse(responseCode = "200", description = "Selecciona la tarea escogida por Id", content = { @Content(mediaType = "application/json", 
    		      schema = @Schema(implementation = Tarea.class)) }),
    @ApiResponse(responseCode = "400", description = "Error en la búsqueda, genera una excepción", content = @Content), 
    @ApiResponse(responseCode = "404", description = "No encuentra valores", content = @Content) })
    @GetMapping("/tareas/{id}")
    public ResponseEntity<Tarea> obtenerTareaPorId(@Parameter(description = "Id correspondiente a la tarea")@PathVariable Long id) {
    	
    	Tarea tarea = tareaRepositorio.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("No existe la tarea buscada con índice: " + id));
    	
    	return ResponseEntity.ok(tarea);
    }
    
    @Operation(summary = "Permite actualizar una sola tarea")
    @ApiResponses(value = { 
    @ApiResponse(responseCode = "200", description = "Actualiza correctamente la tarea seleccionada", content = { @Content(mediaType = "application/json", 
    		      schema = @Schema(implementation = Tarea.class)) }),
    @ApiResponse(responseCode = "400", description = "Error al actualizar, genera una excepción", content = @Content), 
    @ApiResponse(responseCode = "404", description = "No encuentra valores", content = @Content) })
    @PutMapping("/tareas/{id}")
    public ResponseEntity<Tarea> actualizarTarea(@Parameter(description = "Id correspondiente a la tarea")@PathVariable Long id, @RequestBody @Valid Tarea tarea){
    	Tarea tareaActualizar = tareaRepositorio.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("No existe la tarea buscada con índice: " + id));
    	tareaActualizar.setDescripcion(tarea.getDescripcion());
    	tareaActualizar.setFechaCreacion(tarea.getFechaCreacion());
    	tareaActualizar.setVigente(tarea.isVigente());
    	
    	Tarea actualizarTarea =  tareaRepositorio.save(tareaActualizar);
    	return ResponseEntity.ok(actualizarTarea);
    }
    
    @Operation(summary = "Permite eliminar una tarea")
    @ApiResponses(value = { 
    @ApiResponse(responseCode = "200", description = "Elimina correctamente la tarea escogida", content = { @Content(mediaType = "application/json", 
    		      schema = @Schema(implementation = Tarea.class)) }),
    @ApiResponse(responseCode = "400", description = "Error al eliminar", content = @Content), 
    @ApiResponse(responseCode = "404", description = "No encuentra valores", content = @Content) })
    @DeleteMapping("/tareas/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarTarea(@Parameter(description = "Id correspondiente a la tarea")@PathVariable Long id){
    	Tarea tareaActualizar = tareaRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No existe la tarea buscada con índice: " + id));
		
    	tareaRepositorio.delete(tareaActualizar);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
