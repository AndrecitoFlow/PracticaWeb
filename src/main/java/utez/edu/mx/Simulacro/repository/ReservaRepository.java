package utez.edu.mx.Simulacro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utez.edu.mx.Simulacro.model.Reserva;

import java.awt.*;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long > {
  List<Reserva> findByCorreo(String correo);
}
