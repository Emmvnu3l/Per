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
import com.perfulandia.EmmanuelKena.Perfulandia.entities.Usuario;
import com.perfulandia.EmmanuelKena.Perfulandia.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;




@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioRestController {
   
    @Autowired
    private UsuarioService usuarioService;


    //Método finByAll
    @Operation(summary = "Obtener lista de usuarios", description = "Devuelve todos los usuarios disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios retornada correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class)))
    @GetMapping 
    public List<Usuario> mostrarUsuario(){
        return usuarioService.findByAll();
    }
   

    //Método post/crear
    @Operation(summary = "Crear un nuevo usuario", description = "Crea un usuario con los datos proporcionados")
    @ApiResponse(responseCode = "201", description = "Usuario creado correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class)))
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario unUsuario){  
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crearUsuario(unUsuario));
    }
    

    //Método finById
    @Operation(summary = "Obtener usuario por ID", description = "Obtiene detalles de un usuario en específico")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class)))
    @GetMapping("/{id}")
    public ResponseEntity<?> verUsuario(@PathVariable Long id){
        Optional<Usuario> optionalUsuario = usuarioService.findById(id);     //similar funcionalmente a select * from producto where condicion
        if (optionalUsuario.isPresent()){
           return ResponseEntity.ok(optionalUsuario.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

   
   //Método put/modificar
    @Operation(summary = "Cambiar datos de usuario buscandolo por ID", description = "Modifica datos de un usuario en específico")
    @ApiResponse(responseCode = "200", description = "Usuario modificado",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class)))
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable Long id, @RequestBody Usuario unUsuario){
        Optional<Usuario> optionalUsuario = usuarioService.findById(id);
        if (optionalUsuario.isPresent()){
            Usuario usuarioExiste = new Usuario();
            usuarioExiste = optionalUsuario.get();
            usuarioExiste.setNombre(unUsuario.getNombre());
            usuarioExiste.setApellido(unUsuario.getApellido());
            usuarioExiste.setRut(unUsuario.getRut());
            usuarioExiste.setEmail(unUsuario.getEmail());
            usuarioExiste.setDireccion(unUsuario.getDireccion());
            usuarioExiste.setNumero(unUsuario.getNumero());
            usuarioExiste.setSucursal(unUsuario.getSucursal());
            usuarioExiste.setRol(unUsuario.getRol());
            Usuario usuarioModificado = usuarioService.crearUsuario(usuarioExiste);
            return ResponseEntity.ok(usuarioModificado);

        }
        return ResponseEntity.notFound().build();
    }
    
    //Método delete/borrar
    @Operation(summary = "Elimina un usuario", description = "Elimina un usuario segun su ID")
    @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Usuario.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<List<Usuario>> eliminarUsuario(@PathVariable Long id) {
        Optional<Usuario> eliminado = usuarioService.delete(id);
        
        if (eliminado.isPresent()) {
            List<Usuario> usuario = usuarioService.findByAll();
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
