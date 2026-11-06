package utez.edu.mx.Simulacro.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.Simulacro.controller.dto.CambiarEstadoEventoDTO;
import utez.edu.mx.Simulacro.controller.dto.EventoDTO;
import utez.edu.mx.Simulacro.model.Evento;
import utez.edu.mx.Simulacro.service.EventoService;

import java.util.List;

@RestController
@RequestMapping("/eventos")
@CrossOrigin("*")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<Evento> registrar(@RequestBody @Valid EventoDTO payload) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(eventoService.registrarEvento(payload));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Evento> cambiarEstado(
            @PathVariable Long id,
            @RequestBody @Valid CambiarEstadoEventoDTO payload) {
        return ResponseEntity.ok(eventoService.cambiarEstado(id, payload));
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Evento>> listarDisponibles() {
        return ResponseEntity.ok(eventoService.obtenerDisponibles());
    }
}
