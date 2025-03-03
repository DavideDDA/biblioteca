package biblioteca.com.example.biblioteca.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Questa classe rappresenta un Data Transfer Object (DTO) per la creazione di
 * un nuovo utente.
 * Contiene i campi necessari per registrare un utente nel sistema della
 * biblioteca.
 * Utilizza annotazioni di validazione per garantire che i dati inseriti siano
 * corretti e completi.
 * I campi obbligatori sono: nome, cognome, email e password.
 * I campi opzionali sono: telefono e indirizzo.
 * L'annotazione @Data di Lombok genera automaticamente i metodi getter, setter,
 * equals, hashCode e toString.
 * Viene utilizzata per creare un nuovo utente nel sistema.
 */
@Data
public class UtenteCreateDTO {
    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;

    @NotBlank(message = "L'email è obbligatoria")
    @Email(message = "Formato email non valido")
    private String email;

    @NotBlank(message = "La password è obbligatoria")
    @Size(min = 6, message = "La password deve contenere almeno 6 caratteri")
    private String password;

    private String telefono;
    private String indirizzo;
}
