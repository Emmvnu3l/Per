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
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Proveedor;
import com.perfulandia.EmmanuelKena.Perfulandia.services.ProveedorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Proveedor", description = "Operaciones relacionadas con Proveedores")
@RestController
@RequestMapping("/api/proveedor")
public class ProveedorRestController {
    
    
    @Autowired
    private ProveedorService proveedorService;

    //Método finByAll
    @Operation(summary = "Obtener lista de Proveedores", description = "Devuelve todos los proveedores disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de proveedores retornada correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Proveedor.class)))
    @GetMapping 
    public List<Proveedor> mostrarProveedor(){
        return proveedorService.findByAll();
    }

    //Método post/crear
    @Operation(summary = "Crear un nuevo proveedor", description = "Crea un proveedor con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Proveedor creado correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Proveedor.class)))
    @PostMapping
    public ResponseEntity<Proveedor> crearProveedor(@RequestBody Proveedor unProveedor){  
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.crearProveedor(unProveedor));
    }

    //Método finById
    @Operation(summary = "Obtener proveedor por ID", description = "Obtiene detalles de un proveedor en específico")
    @ApiResponse(responseCode = "200", description = "Proveedor encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Proveedor.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> verProveedor(@PathVariable Long id){
        Optional<Proveedor> optionalProveedor = proveedorService.findById(id);     //similar funcionalmente a select * from Proveedor where condicion
        if (optionalProveedor.isPresent()){
           return ResponseEntity.ok(optionalProveedor.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }


    //Método put/modificar
    @Operation(summary = "Cambiar datos de Proovedor buscandolo por ID", description = "OModifica datos de proveedor en específico")
    @ApiResponse(responseCode = "201", description = "Proveedor modificado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Proveedor.class)))
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarProveedor(@PathVariable Long id, @RequestBody Proveedor unProveedor){
        Optional<Proveedor> optionalProveedor = proveedorService.findById(id);
        if (optionalProveedor.isPresent()){
            Proveedor ProveedorExiste = new Proveedor();
            ProveedorExiste = optionalProveedor.get();
            ProveedorExiste.setId(unProveedor.getId());
            ProveedorExiste.setNombre(unProveedor.getNombre());
            ProveedorExiste.setRut(unProveedor.getRut());
            ProveedorExiste.setCorreo(unProveedor.getCorreo());
            ProveedorExiste.setTelefono(unProveedor.getTelefono());
            ProveedorExiste.setDireccion(unProveedor.getDireccion());
            ProveedorExiste.setPais(unProveedor.getPais());
            ProveedorExiste.setCiudad(unProveedor.getCiudad());
            Proveedor ProveedorModificado = proveedorService.crearProveedor(ProveedorExiste);
            return ResponseEntity.ok(ProveedorModificado);
        }
        return ResponseEntity.notFound().build();
    }

    //Método delete/borrar
    @Operation(summary = "Elimina un proveedor", description = "Elimina un proveedor segun su ID")
    @ApiResponse(responseCode = "200", description = "Proveedor eliminado correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Proveedor.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Proveedor>> eliminarProveedor(@PathVariable Long id) {
        Optional<Proveedor> eliminado = proveedorService.delete(id);
        
        if (eliminado.isPresent()) {
            List<Proveedor> proveedores = proveedorService.findByAll();
            return ResponseEntity.ok(proveedores);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
