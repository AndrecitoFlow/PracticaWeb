package utez.edu.mx.Simulacro.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReservaDTO {


    @NotNull(message = "ID obligatorio")
    private Long eventoId;

    @NotBlank(message = "Nombre obligatorio")
    private String nombreAsistente;

    @NotNull(message = "Edad obligatoria")
    @Min(value = 1, message = "Edad valida rey")
    private Integer edad;

    @NotBlank(message = "El correo no puede etar vacio")
    @Email(message = "El formato de correo")
    private String correo;
}
