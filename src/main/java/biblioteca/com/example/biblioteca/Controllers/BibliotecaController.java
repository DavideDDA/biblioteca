package biblioteca.com.example.biblioteca.Controllers;


import biblioteca.com.example.biblioteca.Models.Libro;
import biblioteca.com.example.biblioteca.Services.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libri")
public class BibliotecaController {
    @Autowired
    private BibliotecaService service;

    @GetMapping("/id/{id}")
    public Optional<Libro> findById(@PathVariable int id) throws Exception{
        return service.searchById(id);
    }
    @GetMapping("/titolo/{titolo}")
    public Optional<Libro> findLibro(@PathVariable String titolo) throws Exception{
        return service.cercaLibro(titolo);
    }

    @GetMapping("/findByGenere")
    public Optional<List<Libro>> findByGenre(@Param(value = "genere") String genere) throws Exception{
       return service.searchByGenere(genere);
    }
    @GetMapping("/genereAutore")
    public Optional<List<Libro>> findByGenreAndAuthor(@Param(value = "genre") String genre, @Param(value = "author") String author) throws Exception{
        return service.findByGenereAndAutore(genre,author);
    }

    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable Integer id) throws Exception{
        service.rimuoviLibro(id);
    }
    @PostMapping("/aggiungi")
    public void addBook(@RequestBody Libro libro) throws Exception{
        service.aggiungiLibro(libro);
    }

    @PutMapping("/modifica")
    public void modifyBook(@RequestBody Libro libro) throws Exception{
        service.aggiornaLibro(libro);
    }

    @GetMapping("/lista")
    public Page<Libro> getAllLibri(@RequestParam(value = "size", defaultValue = "5") int size,@RequestParam(value = "page", defaultValue = "0") int page){
        Pageable pagina = PageRequest.of(page,size);
        return service.listLibri(pagina);
    }


}
