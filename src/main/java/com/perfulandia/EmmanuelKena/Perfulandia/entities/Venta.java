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

@NoArgsConstructor /*Constructor vacio */
@AllArgsConstructor /*Constructor con parámetros */
@Getter @Setter /*Getter y Setter */

@Entity
@Table(name="venta")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;
    private Long clienteId;
    private LocalDate fecha;
    private Double total;               
    private String metodoPago;         
    private String estado;              
    private Long cuponId;               
    
}
