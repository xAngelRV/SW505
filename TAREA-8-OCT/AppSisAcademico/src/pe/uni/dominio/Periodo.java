/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.uni.dominio;

import pe.uni.excepciones.ValidacionException;

/**
 *
 * @author JHERENCIA
 */
public record Periodo(
        String codigo,
        String descripcion
        ) {
    
    public Periodo {
        if(codigo == null || codigo.isBlank()) {
            throw new ValidacionException("El codigo del periodo es obligatorio");
        }
        
        if(descripcion == null || descripcion.isBlank()) {
            throw new ValidacionException("La descripcion del periodo es obligatorio");
        }
    }
    
}