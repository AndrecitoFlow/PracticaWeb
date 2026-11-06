package utez.edu.mx.Simulacro.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import utez.edu.mx.Simulacro.model.Estado;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CambiarEstadoEventoDTO {

    @NotNull(message = "El nuevo estado es obligatorio")
    private Estado nuevoEstado;
}
