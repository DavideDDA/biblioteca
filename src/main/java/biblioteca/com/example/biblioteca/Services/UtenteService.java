package biblioteca.com.example.biblioteca.Services;

import biblioteca.com.example.biblioteca.DTO.UtenteCreateDTO;
import biblioteca.com.example.biblioteca.DTO.UtenteDTO;
import biblioteca.com.example.biblioteca.Models.Utente;
import biblioteca.com.example.biblioteca.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    private UtenteDTO convertToDTO(Utente utente) {
        UtenteDTO dto = new UtenteDTO();
        dto.setId(utente.getId());
        dto.setNome(utente.getNome());
        dto.setCognome(utente.getCognome());
        dto.setEmail(utente.getEmail());
        dto.setTelefono(utente.getTelefono());
        dto.setIndirizzo(utente.getIndirizzo());
        return dto;
    }

    public UtenteDTO createUser(UtenteCreateDTO utenteDTO) {
        if (utenteRepository.findByEmail(utenteDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email gia presente");
        }
        Utente utente = new Utente();
        utente.setNome(utenteDTO.getNome());
        utente.setCognome(utenteDTO.getCognome());
        utente.setEmail(utenteDTO.getEmail());
        utente.setPassword(utenteDTO.getPassword());
        utente.setTelefono(utenteDTO.getTelefono());
        utente.setIndirizzo(utenteDTO.getIndirizzo());

        return convertToDTO(utenteRepository.save(utente));
    }

    public UtenteDTO aggiornaUtente(Long id, UtenteDTO utenteDTO) {
        Utente utente = utenteRepository.findById(id).orElseThrow(() -> new RuntimeException("Utente non trovato"));

        utente.setNome(utenteDTO.getNome());
        utente.setCognome(utenteDTO.getCognome());
        utente.setTelefono(utenteDTO.getTelefono());
        utente.setIndirizzo(utenteDTO.getIndirizzo());

        return convertToDTO(utenteRepository.save(utente));
    }

    public void eliminaUtente(Long id) {
        if (!utenteRepository.existsById(id)) {
            throw new RuntimeException("Utente non trovato");
        }
        utenteRepository.deleteById(id);
    }

    public UtenteDTO getUtenteById(Long id) {
        Utente utente = utenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
        return convertToDTO(utente);
    }

}
