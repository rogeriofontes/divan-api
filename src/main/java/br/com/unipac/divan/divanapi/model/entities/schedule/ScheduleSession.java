package br.com.unipac.divan.divanapi.model.entities.schedule;

import br.com.unipac.divan.divanapi.constants.Constants;
import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import br.com.unipac.divan.divanapi.model.entities.patient.Patient;
import br.com.unipac.divan.divanapi.model.entities.psychological.Psychological;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "tb_schedule_session")
@Schema(description = "Schedule Session object")
public class ScheduleSession extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the Patient.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Schema(description = "Description of the Patient.",
            example = "Jessica Abigail", required = true)
    @Column(name = "short_description")
    @NotNull
    private String shortDescription;

    @Schema(description = "Descriptione of the Patient.",
            example = "Jessica Abigail", required = true)
    @Column(name = "last_name")
    @NotNull
    private String description;

    @Column(name = "start_date", nullable = false)
    @Schema(title = "The publication date of this post.",
            format = "ISO8601 date string",
            description = "Notes about the contact.",
            example = "13/09/2022 14:31:34", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.BRAZILIAN_DATE)
    @DateTimeFormat(pattern = Constants.BRAZILIAN_DATE)
    @NotNull
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    @Schema(title = "The publication date of this post.",
            format = "ISO8601 date string",
            description = "Notes about the contact.",
            example = "13/09/2022 14:31:34", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.BRAZILIAN_DATE)
    @DateTimeFormat(pattern = Constants.BRAZILIAN_DATE)
    @NotNull
    private LocalDateTime endDate;

    @Schema(description = "Association is Active.",
            example = "true or false", required = true)
    @NotNull
    private boolean answered;

    @Schema(description = "Name of the Association.",
            example = "Jessica Abigail Association", required = false, ref = "Association")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @Schema(description = "Name of the Association.",
            example = "Jessica Abigail Association", required = false, ref = "Association")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "psychological_id")
    private Psychological psychological;;

    public void update(Long id, ScheduleSession scheduleSession) {
        this.id = id;
        this.shortDescription = scheduleSession.getShortDescription();
        this.description = scheduleSession.getDescription();
        this.startDate = scheduleSession.getStartDate();
        this.endDate = scheduleSession.getEndDate();
        this.answered = scheduleSession.isAnswered();
        this.patient = scheduleSession.getPatient();
        this.psychological = scheduleSession.getPsychological();
    }

    //user_account
}
