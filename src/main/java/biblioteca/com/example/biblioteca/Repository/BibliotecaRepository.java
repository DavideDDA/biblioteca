package biblioteca.com.example.biblioteca.Repository;

import biblioteca.com.example.biblioteca.Models.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BibliotecaRepository extends JpaRepository<Libro, Integer> {
    public Page<Libro> findAll(Pageable pageable);

}
