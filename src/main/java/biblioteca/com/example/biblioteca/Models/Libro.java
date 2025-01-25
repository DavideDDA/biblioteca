package biblioteca.com.example.biblioteca.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table
public class Libro {
    @Id
    private Integer id;
    private String titolo;
    private String autore;
    private int copieDisponibili;
    private String genere;
}
