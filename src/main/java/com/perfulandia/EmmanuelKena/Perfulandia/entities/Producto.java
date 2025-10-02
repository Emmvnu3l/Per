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

@Table(name="producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;
    private String nombre;
    private String descripcion;
    private String marca;
    private String categoria; 
    private String genero;  
    private int stock;
    private int precio;
    private String fragancia; 
    private int mililitros;    

}
