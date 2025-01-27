package biblioteca.com.example.biblioteca.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "libro")
public class Libro {
    @Id
    private Integer id;
    @NotNull(message = "Il titolo del libro non puo essere vuoto!")
    private String titolo;
    @NotNull(message = "L'autore del libro non puo essere vuoto!")
    private String autore;
    @Min(value = 1, message = "Le copie disponibili devono essere almeno 1")
    private int copieDisponibili;
    @NotNull(message = "Il genere del libro non puo essere vuoto!")
    private String genere;
}
