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

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Cupon;
import com.perfulandia.EmmanuelKena.Perfulandia.services.CuponService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cupones", description = "Operaciones relacionadas con cupones")
@RestController
@RequestMapping("/api/cupon")
public class CuponRestController {

    @Autowired
    private CuponService cuponService;


    //Método finByAll
    @Operation(summary = "Obtener lista de cupones", description = "Devuelve todos los cupones disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de cupones retornada correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Cupon.class)))
    @GetMapping 
    public List<Cupon> mostrarCupon(){
        return cuponService.findByAll();
    }


    //Método post/crear
    @Operation(summary = "Crear un nuevo cupón", description = "Crea un cupón con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Cupón creado correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Cupon.class)))
    @PostMapping
    public ResponseEntity<Cupon> crearCupon(@RequestBody Cupon unCupon){  
        return ResponseEntity.status(HttpStatus.CREATED).body(cuponService.crearCupon(unCupon));
    }

    //Método finById
    @Operation(summary = "Obtener cupón por ID", description = "Obtiene detalles de un cupón en específico")
    @ApiResponse(responseCode = "200", description = "Cupón encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Cupon.class)))
                
    @GetMapping("/{id}")
    public ResponseEntity<?> verCupon(@PathVariable Long id){
        Optional<Cupon> optionalCupon = cuponService.findById(id);    
        if (optionalCupon.isPresent()){
           return ResponseEntity.ok(optionalCupon.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    //Método put/modificar
    @Operation(summary = "Modificación de un Cupón", description = "Modifica los datos de un cupón")
    @ApiResponse(responseCode = "200", description = "Cupón modificado correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Cupon.class)))
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarCupon(@PathVariable Long id, @RequestBody Cupon unCupon){
        Optional<Cupon> optionalCupon = cuponService.findById(id);
        if (optionalCupon.isPresent()){
            Cupon CuponExiste = new Cupon();
            CuponExiste = optionalCupon.get();
            CuponExiste.setCodigo(unCupon.getCodigo());
            CuponExiste.setDescuento(unCupon.getDescuento());
            CuponExiste.setFechaInicio(unCupon.getFechaInicio());
            CuponExiste.setFechaExpiracion(unCupon.getFechaExpiracion());
            CuponExiste.setActivo(unCupon.getActivo());
            CuponExiste.setUsosDisponibles(unCupon.getUsosDisponibles());
            Cupon CuponModificado = cuponService.crearCupon(CuponExiste);
            return ResponseEntity.ok(CuponModificado);

        }
        return ResponseEntity.notFound().build();
    }

    //Método delete/borrar
    @Operation(summary = "Elimina un cupón", description = "Elimina un cupón segun su ID")
    @ApiResponse(responseCode = "200", description = "Cupón eliminado correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Cupon.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Cupon>> eliminarCupon(@PathVariable Long id) {
        Optional<Cupon> eliminado = cuponService.delete(id);
        
        if (eliminado.isPresent()) {
            List<Cupon> Cupon = cuponService.findByAll();
            return ResponseEntity.ok(Cupon);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
