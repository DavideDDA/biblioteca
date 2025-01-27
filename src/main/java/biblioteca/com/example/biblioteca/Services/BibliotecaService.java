package biblioteca.com.example.biblioteca.Services;

import biblioteca.com.example.biblioteca.Models.Libro;
import biblioteca.com.example.biblioteca.Repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BibliotecaService {
    @Autowired
    private BibliotecaRepository libri;

    public void aggiungiLibro(Libro libro) throws Exception {
        if (libri.findById(libro.getId()).isPresent())
            throw new Exception("LIbro gia esistente con questo id!");
        libri.save(libro);
    }

    public void stampaLibri() {
        for (Libro libro : libri.findAll()) {
            System.out.println("Id: " + libro.getId());
            System.out.println("Titolo: " + libro.getTitolo());
            System.out.println("Autore: " + libro.getAutore());
            System.out.println("Copie disponibili: " + libro.getCopieDisponibili());
            System.out.println("Genere: " + libro.getGenere());
            System.out.println();
        }
    }

    public Page<Libro> listLibri(Pageable pageable) {
        return libri.findAll(pageable);
    }

    public Libro stampaLibro(Libro libro) {
        System.out.println("Id: " + libro.getId());
        System.out.println("Titolo: " + libro.getTitolo());
        System.out.println("Autore: " + libro.getAutore());
        System.out.println("Copie disponibili: " + libro.getCopieDisponibili());
        System.out.println("Genere: " + libro.getGenere());
        return libro;
    }

    public void aggiornaLibro(Libro libro) throws Exception {
        Optional<Libro> trovato = searchById(libro.getId());
        if (trovato.isEmpty())
            throw new Exception("Non puoi modificare l'id!");
        trovato.get().setAutore(libro.getAutore());
        trovato.get().setGenere(libro.getGenere());
        trovato.get().setTitolo(libro.getTitolo());
        trovato.get().setCopieDisponibili(libro.getCopieDisponibili());
        libri.save(trovato.get());
    }

    public void rimuoviLibro(Integer id) throws Exception {
        if (searchById(id).isEmpty())
            throw new Exception("Non trovato!");
        libri.deleteById(id);
    }

    public Optional<Libro> cercaLibro(String titolo) throws Exception {
        Optional<Libro> trovato = libri.findAll().stream()
                .filter(libro -> libro.getTitolo().equalsIgnoreCase(titolo)).findFirst();
        if (trovato.isPresent()) {
            System.out.println("Libro trovato: ");
            stampaLibro(trovato.get());
        } else {
            System.out.println("Libro non trovato");
            throw new Exception("Libro non trovato");
        }
        return trovato;
    }

    public Optional<List<Libro>> searchByGenere(String genere) throws Exception {
        List<Libro> genereTrovat =
                libri.findAll().stream().filter(libro -> libro.getGenere().equals(genere))
                        .collect(Collectors.toList());
        Optional<List<Libro>> trovato = genereTrovat.isEmpty() ? Optional.empty() : Optional.of(genereTrovat);

        if (trovato.isEmpty()) {
            System.out.println("Niente trovato");
            throw new Exception("Libro non trovato per genere");
        } else {
            System.out.println("Trovato");
            trovato.get().forEach(this::stampaLibro);
        }
        return trovato;
    }

    public Optional<Libro> searchById(int id) throws Exception {
        Optional<Libro> libroTro = libri.findAll().stream().filter(lib -> lib.getId() == id).findAny();
        if (libroTro.isEmpty()) {
            System.out.println("Non trovato!");
            throw new Exception("Libro per id non trovato");
        } else {
            System.out.println("Trovato!");
            stampaLibro(libroTro.get());
        }
        return libroTro;
    }

    public Optional<List<Libro>> findByGenereAndAutore(String genere, String autore) throws Exception {
        List<Libro> trov = libri.findAll().stream()
                .filter(lib -> lib.getGenere().equals(genere) && lib.getAutore().equals(autore))
                .collect(Collectors.toList());
        Optional<List<Libro>> trovOp = trov.isEmpty() ? Optional.empty()
                : Optional.of(trov);
        if (trovOp.isEmpty()) {
            System.out.println("Nessun libro trovato");
            throw new Exception("Libro per genere e autore non trovato!");
        } else {
            System.out.println("trovato:");
            trovOp.get().forEach(this::stampaLibro);
        }
        return trovOp;
    }
}