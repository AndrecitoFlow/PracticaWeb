package utez.edu.mx.Simulacro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.Simulacro.model.Estado;
import utez.edu.mx.Simulacro.model.Evento;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {

    List<Evento> findByEstado(Estado estado);
}
