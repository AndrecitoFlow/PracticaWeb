package utez.edu.mx.Simulacro.service;

import org.springframework.stereotype.Service;
import utez.edu.mx.Simulacro.controller.dto.CalificacionDTO;
import utez.edu.mx.Simulacro.controller.dto.ReservaDTO;
import utez.edu.mx.Simulacro.exception.CustomException;
import utez.edu.mx.Simulacro.model.Estado;
import utez.edu.mx.Simulacro.model.EstadoReserva;
import utez.edu.mx.Simulacro.model.Evento;
import utez.edu.mx.Simulacro.model.Reserva;
import utez.edu.mx.Simulacro.repository.EventoRepository;
import utez.edu.mx.Simulacro.repository.ReservaRepository;

import javax.swing.undo.CannotUndoException;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final EventoRepository eventoRepository;

    public ReservaService(ReservaRepository reservaRepository, EventoRepository eventoRepository) {
        this.reservaRepository = reservaRepository;
        this.eventoRepository = eventoRepository;
    }

    public Reserva createReserva(ReservaDTO dto){
        Evento evento = eventoRepository.findById(dto.getEventoId())
                .orElseThrow(() -> new CustomException("No existe el evento"));
        if (evento.getEstado() != Estado.DISPONIBLE){
            throw new CustomException("no disponible crack");
        }
        Reserva reserva = new Reserva();
        reserva.setNombreAsistente(dto.getNombreAsistente());
        reserva.setEdad(dto.getEdad());
        reserva.setCorreo(dto.getCorreo());
        reserva.setFechaReserva(LocalDate.now());
        reserva.setEstadoReserva(EstadoReserva.CONFIRMADA);
        reserva.setEvento(evento);

        return reservaRepository.save(reserva);

    }

    public Reserva cancelarReserva(Long id){
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new CustomException("No existe el evento"));

        if (reserva.getEstadoReserva() != EstadoReserva.CONFIRMADA) {
            throw new CustomException("Debe de estar confirmada");
        }
        if (!reserva.getEvento().getFechaEvento().isAfter(LocalDate.now())){
            throw new CustomException("no se puede cancelar si ya paso o es hoy ");
        }

        reserva.setEstadoReserva(EstadoReserva.CANCELADA);
        return reservaRepository.save(reserva);
    }

    public Reserva registrarCalificacion(Long id, CalificacionDTO dto) {

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new CustomException("La reserva no existe"));

        if (reserva.getEvento().getEstado() != Estado.FINALIZADO) {
            throw new CustomException("No se puede registrar calificación hasta que el evento esté FINALIZADO");
        }

        if (reserva.getEstadoReserva() != EstadoReserva.CONFIRMADA) {
            throw new CustomException("Solo se puede registrar calificación en reservas con estado CONFIRMADA");
        }

        reserva.setCalificacion(dto.getCalificacion());
        reserva.setEstadoReserva(EstadoReserva.ASISTIDA);
        return reservaRepository.save(reserva);
    }

    public List<Reserva> obtenerPorCorreo(String correo) {
        return reservaRepository.findByCorreo(correo);
    }
}
