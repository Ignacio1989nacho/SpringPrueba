
package com.example.SpringEgg.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Data;
@Data
@Entity
public class Libro {
    
    @Id
    private Long isbn;
    private String titulo;
    private Integer ejemplares;
    @Temporal(TemporalType.DATE)
    private Date fechaAlta;
    @ManyToOne
    private Autor autor;
    @ManyToOne
    private Editorial editorial;
    
    
}
