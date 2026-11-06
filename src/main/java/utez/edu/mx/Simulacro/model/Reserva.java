package utez.edu.mx.Simulacro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reserva")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreAsistente;

    private Integer edad;

    private String correo;

    private LocalDate fechaReserva;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estadoReserva;

    private Double calificacion = null;

    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;
}
