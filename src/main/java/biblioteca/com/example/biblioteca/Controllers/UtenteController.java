package biblioteca.com.example.biblioteca.Controllers;

import biblioteca.com.example.biblioteca.DTO.UtenteCreateDTO;
import biblioteca.com.example.biblioteca.DTO.UtenteDTO;
import biblioteca.com.example.biblioteca.Services.UtenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;


    @PostMapping
    public ResponseEntity<UtenteDTO> creaUtente(@Valid @RequestBody UtenteCreateDTO utenteCreateDTO){
        UtenteDTO creato = utenteService.createUser(utenteCreateDTO);
        return new ResponseEntity<>(creato, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtenteDTO> getUtente(@PathVariable Long id){
        return ResponseEntity.ok(utenteService.getUtenteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtenteDTO> updateUtente(@PathVariable Long id,@Valid @RequestBody UtenteDTO utenteDTO){
        UtenteDTO utenteDTO1 = utenteService.aggiornaUtente(id, utenteDTO);
        return ResponseEntity.ok(utenteDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtente(@PathVariable Long id){
        utenteService.eliminaUtente(id);
        return ResponseEntity.noContent().build();
    }
}
