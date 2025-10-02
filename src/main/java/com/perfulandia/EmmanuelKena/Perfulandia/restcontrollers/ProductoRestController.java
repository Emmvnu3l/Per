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

import com.perfulandia.EmmanuelKena.Perfulandia.entities.Producto;
import com.perfulandia.EmmanuelKena.Perfulandia.services.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Productos", description = "Operaciones relacionadas con productos")
@RestController
@RequestMapping("/api/producto")
public class ProductoRestController {
    
    @Autowired
    private ProductoService productoService;
   
    //Método finByAll
    @Operation(summary = "Obtener lista de productos", description = "Devuelve todos los productos disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de productos retornada correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Producto.class)))
    @GetMapping 
    public List<Producto> mostrarProducto(){
        return productoService.findByAll();
    }


    //Método post/crear
    @Operation(summary = "Crear un nuevo producto", description = "Crea un producto con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Producto creado correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Producto.class)))
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto unProducto){  
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.crearProducto(unProducto));
    }

    //Método finById
    @Operation(summary = "Obtener producto por ID", description = "Obtiene detalles de un producto en específico")
    @ApiResponse(responseCode = "200", description = "Producto encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Producto.class)))  
    @GetMapping("/{id}")
    public ResponseEntity<?> verProducto(@PathVariable Long id){
        Optional<Producto> optionalProducto = productoService.findById(id);     //similar funcionalmente a select * from producto where condicion
        if (optionalProducto.isPresent()){
           return ResponseEntity.ok(optionalProducto.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    //Método put/modificar
    @Operation(summary = "Cambiar datos de un producto buscándolo por ID", description = "Modifica datos de un producto en específico")
    @ApiResponse(responseCode = "201", description = "Producto modificado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Producto.class)))
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarProducto(@PathVariable Long id, @RequestBody Producto unProducto){
        Optional<Producto> optionalProducto = productoService.findById(id);
        if (optionalProducto.isPresent()){
            Producto ProductoExiste = new Producto();
            ProductoExiste = optionalProducto.get();
            ProductoExiste.setId(unProducto.getId());
            ProductoExiste.setNombre(unProducto.getNombre());
            ProductoExiste.setDescripcion(unProducto.getDescripcion());
            ProductoExiste.setMarca(unProducto.getMarca());
            ProductoExiste.setCategoria(unProducto.getCategoria());
            ProductoExiste.setGenero(unProducto.getGenero());
            ProductoExiste.setStock(unProducto.getStock());
            ProductoExiste.setPrecio(unProducto.getPrecio());
            ProductoExiste.setFragancia(unProducto.getFragancia());
            ProductoExiste.setMililitros(unProducto.getMililitros());
            Producto ProductoModificado = productoService.crearProducto(ProductoExiste);
            return ResponseEntity.ok(ProductoModificado);
        }
        return ResponseEntity.notFound().build();
    }


    //Método delete/borrar
    @Operation(summary = "Elimina un produto", description = "Elimina un producto segun su ID")
    @ApiResponse(responseCode = "200", description = "Producto eliminado correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Producto.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Producto>> eliminarProveedor(@PathVariable Long id) {
        Optional<Producto> eliminado = productoService.delete(id);
        if (eliminado.isPresent()) {
            List<Producto> productos = productoService.findByAll();
            return ResponseEntity.ok(productos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}