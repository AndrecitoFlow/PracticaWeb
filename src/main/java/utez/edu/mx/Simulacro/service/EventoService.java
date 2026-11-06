package utez.edu.mx.Simulacro.service;

import org.springframework.stereotype.Service;
import utez.edu.mx.Simulacro.controller.dto.CambiarEstadoEventoDTO;
import utez.edu.mx.Simulacro.controller.dto.EventoDTO;
import utez.edu.mx.Simulacro.exception.CustomException;
import utez.edu.mx.Simulacro.model.Estado;
import utez.edu.mx.Simulacro.model.Evento;
import utez.edu.mx.Simulacro.repository.EventoRepository;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public Evento registrarEvento(EventoDTO dto) {
        Evento evento = new Evento();
        evento.setTitulo(dto.getTitulo());
        evento.setDescripcion(dto.getDescripcion());
        evento.setFechaEvento(dto.getFechaEvento());
        evento.setEstado(Estado.DISPONIBLE); // Estado inicial por regla de negocio

        return eventoRepository.save(evento);
    }

    public Evento cambiarEstado(Long id, CambiarEstadoEventoDTO dto) {

        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new CustomException("El evento no existe"));

        if (evento.getEstado() == Estado.FINALIZADO) {
            throw new CustomException("No se puede cambiar el estado de un evento que ya está FINALIZADO");
        }

        Estado actual = evento.getEstado();
        Estado nuevo = dto.getNuevoEstado();

        boolean esValido = (actual == Estado.DISPONIBLE && (nuevo == Estado.LLENO || nuevo == Estado.FINALIZADO))
                || (actual == Estado.LLENO && nuevo == Estado.FINALIZADO);

        if (!esValido) {
            throw new CustomException("Transición de estado no permitida de " + actual + " a " + nuevo);
        }

        evento.setEstado(nuevo);
        return eventoRepository.save(evento);
    }

    public List<Evento> obtenerDisponibles() {
        return eventoRepository.findByEstado(Estado.DISPONIBLE);
    }
}