package biblioteca.com.example.biblioteca.DTO;

import lombok.Data;

/**
 * Questa classe rappresenta un Data Transfer Object (DTO) per l'utente.
 * Viene utilizzata per trasferire i dati dell'utente tra i diversi strati
 * dell'applicazione,
 * in particolare quando si restituiscono informazioni sull'utente al client.
 * Contiene tutti i campi rilevanti dell'utente, incluso l'ID, ma esclude
 * informazioni sensibili come la password.
 * L'annotazione @Data di Lombok genera automaticamente i metodi getter, setter,
 * equals, hashCode e toString.
 * Viene utilizzata per per rappresentare un utente esistente.
 */
@Data
public class UtenteDTO {
    private Long id;
    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    private String indirizzo;
}
