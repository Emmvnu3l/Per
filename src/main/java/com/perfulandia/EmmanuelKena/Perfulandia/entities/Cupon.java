package com.perfulandia.EmmanuelKena.Perfulandia.entities;

import java.time.LocalDate;

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
@NoArgsConstructor 
@AllArgsConstructor
@Getter @Setter 

@Entity 
@Table(name="cupon")
public class Cupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;
    private String codigo;            
    private Double descuento;         
    private LocalDate fechaInicio;
    private LocalDate fechaExpiracion;
    private Boolean activo;          
    private Integer usosDisponibles; 
}
