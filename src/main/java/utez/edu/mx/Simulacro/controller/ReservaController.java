package utez.edu.mx.Simulacro.controller;

import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.Simulacro.controller.dto.CalificacionDTO;
import utez.edu.mx.Simulacro.controller.dto.ReservaDTO;
import utez.edu.mx.Simulacro.model.Reserva;
import utez.edu.mx.Simulacro.service.ReservaService;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@CrossOrigin("*")
public class ReservaController {

    private ReservaService reservaService;
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<Reserva> crear(@RequestBody @Valid ReservaDTO paylod) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservaService.createReserva(paylod));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Reserva> cancelar(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.cancelarReserva(id));
    }

    @PutMapping("/{id}/calificacion")
    public ResponseEntity<Reserva> registrarCalificacion(
            @PathVariable Long id,
            @RequestBody @Valid CalificacionDTO payload) {
        return ResponseEntity.ok(reservaService.registrarCalificacion(id, payload));
    }

    @GetMapping("/correo/{correo}")
    public ResponseEntity<List<Reserva>> obtenerPorCorreo(@PathVariable String correo) {
        return ResponseEntity.ok(reservaService.obtenerPorCorreo(correo));
    }
}
