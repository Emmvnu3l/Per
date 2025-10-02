package com.perfulandia.EmmanuelKena.Perfulandia.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Resenia;
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Cupon;
import com.perfulandia.EmmanuelKena.Perfulandia.services.ReseniaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Reseñas", description = "Operaciones relacionadas con reseñas")
@RestController
@RequestMapping("/api/resenia")
public class ReseniaRestController {

    @Autowired
    private ReseniaService reseniaService;

    //Método finByAll
    @Operation(summary = "Obtener lista de reseñas", description = "Devuelve todas las reseñas disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de reseñas retornada correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Cupon.class)))
    @GetMapping 
    public List<Resenia> mostrarResenia(){
        return reseniaService.findByAll();
    }

    //Método post/crear
    @Operation(summary = "Crear una nueva reseña", description = "Crea una reseñaa con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Reseña creada correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Cupon.class)))    
    @PostMapping
    public ResponseEntity<Resenia> crearResenia(@RequestBody Resenia unResenia){  
        return ResponseEntity.status(HttpStatus.CREATED).body(reseniaService.crearResenia(unResenia));
    }

    //Método finById
    @Operation(summary = "Obtener reseña por ID", description = "Obtiene detalles de una reseña en específico")
    @ApiResponse(responseCode = "200", description = "Reseña encontrada",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Cupon.class)))  
                
    @GetMapping("/{id}")
    public ResponseEntity<?> verResenia(@PathVariable Long id){
        Optional<Resenia> optionalResenia = reseniaService.findById(id); 
        if (optionalResenia.isPresent()){
           return ResponseEntity.ok(optionalResenia.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    //Método put/modificar
    @Operation(summary = "Modificación de una reseña", description = "Modifica los datos de un reseña")
    @ApiResponse(responseCode = "201", description = "Reseña modificada correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Cupon.class)))
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarResenia(@PathVariable Long id, @RequestBody Resenia unResenia){
        Optional<Resenia> optionalResenia = reseniaService.findById(id);
        if (optionalResenia.isPresent()){
            Resenia ReseniaExiste = new Resenia();
            ReseniaExiste = optionalResenia.get();
            ReseniaExiste.setProductoId(unResenia.getProductoId());
            ReseniaExiste.setClienteId(unResenia.getClienteId());
            ReseniaExiste.setCalificacion(unResenia.getCalificacion());
            ReseniaExiste.setComentario(unResenia.getComentario());
            ReseniaExiste.setFecha(unResenia.getFecha());
            Resenia ReseniaModificado = reseniaService.crearResenia(ReseniaExiste);
            return ResponseEntity.ok(ReseniaModificado);

        }
        return ResponseEntity.notFound().build();
    }

    //Método delete/borrar
    @Operation(summary = "Elimina un reseña", description = "Elimina un reseña segun su ID")
    @ApiResponse(responseCode = "200", description = "Reseña eliminada correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Cupon.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Resenia>> eliminarResenia(@PathVariable Long id) {
        Optional<Resenia> eliminado = reseniaService.delete(id);
        
        if (eliminado.isPresent()) {
            List<Resenia> Resenia = reseniaService.findByAll();
            return ResponseEntity.ok(Resenia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

