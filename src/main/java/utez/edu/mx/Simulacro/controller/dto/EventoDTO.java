package utez.edu.mx.Simulacro.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {

    @NotBlank(message = "El título del evento no puede estar vacío")
    private String titulo;

    private String descripcion;

    @NotNull(message = "La fecha del evento es obligatoria")
    private LocalDate fechaEvento;
}
