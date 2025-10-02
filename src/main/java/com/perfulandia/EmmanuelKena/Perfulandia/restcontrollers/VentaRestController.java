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

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Venta;
import com.perfulandia.EmmanuelKena.Perfulandia.services.VentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Ventas", description = "Operaciones relacionadas con las Ventas")
@RestController
@RequestMapping("/api/venta")
public class VentaRestController {

    @Autowired
    private VentaService ventaService;

    //Método finByAll
    @Operation(summary = "Obtener lista de ventas", description = "Devuelve todas las ventas disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de ventas retornada correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Venta.class)))
    @GetMapping 
    public List<Venta> mostrarVenta(){
        return ventaService.findByAll();
    }

    //Método post/crear
    @Operation(summary = "Crear una nueva venta", description = "Crea una venta con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Venta creada correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Venta.class)))
    @PostMapping
    public ResponseEntity<Venta> crearVenta(@RequestBody Venta unVenta){  
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.crearVenta(unVenta));
    }

    //Método finById
    @Operation(summary = "Obtener venta por ID", description = "Obtiene detalles de una venta en específico")
    @ApiResponse(responseCode = "200", description = "Venta encontrada",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Venta.class)))  
    @GetMapping("/{id}")
    public ResponseEntity<?> verVenta(@PathVariable Long id){
        Optional<Venta> optionalVenta = ventaService.findById(id);     //similar funcionalmente a select * from producto where condicion
        if (optionalVenta.isPresent()){
           return ResponseEntity.ok(optionalVenta.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    //Método put/modificar
    @Operation(summary = "Cambiar datos de una venta buscándolo por ID", description = "Modifica datos de una venta en específico")
    @ApiResponse(responseCode = "201", description = "Venta modificada",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Venta.class)))
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarVenta(@PathVariable Long id, @RequestBody Venta unVenta){
        Optional<Venta> optionalVenta = ventaService.findById(id);
        if (optionalVenta.isPresent()){
            Venta VentaExiste = new Venta();
            VentaExiste = optionalVenta.get();
            VentaExiste.setClienteId(unVenta.getClienteId());
            VentaExiste.setFecha(unVenta.getFecha());
            VentaExiste.setTotal(unVenta.getTotal());
            VentaExiste.setMetodoPago(unVenta.getMetodoPago());
            VentaExiste.setEstado(unVenta.getEstado());
            VentaExiste.setCuponId(unVenta.getCuponId());
            Venta VentaModificado = ventaService.crearVenta(VentaExiste);
            return ResponseEntity.ok(VentaModificado);

        }
        return ResponseEntity.notFound().build();
    }

    //Método delete/borrar
    @Operation(summary = "Cambiar datos de una venta buscándolo por ID", description = "Modifica datos de una venta en específico")
    @ApiResponse(responseCode = "200", description = "Venta modificada",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Venta.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Venta>> eliminarVenta(@PathVariable Long id) {
        Optional<Venta> eliminado = ventaService.delete(id);
        
        if (eliminado.isPresent()) {
            List<Venta> Venta = ventaService.findByAll();
            return ResponseEntity.ok(Venta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
