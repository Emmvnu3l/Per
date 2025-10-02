package com.perfulandia.EmmanuelKena.Perfulandia.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor /*Constructor vacio */
@AllArgsConstructor /*Constructor con parámetros */
@Getter @Setter /*Getter y Setter */
@Entity
@Table(name="usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;
    private String nombre;
    private String apellido;
    private String rut;
    private String email;
    private String direccion;
    private int numero;
    private String sucursal;
    private String rol;
    


}

    
    