package br.com.unipac.divan.divanapi.api.dto.request.schedule;

import br.com.unipac.divan.divanapi.constants.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Schema(description = "Schedule Session Request")
public class ScheduleSessionRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Description of the Patient.",
            example = "Jessica Abigail", required = true)
    @NotNull
    private String shortDescription;

    @Schema(description = "Descriptione of the Patient.",
            example = "Jessica Abigail", required = true)
    @NotNull
    private String description;

    @Schema(type = "LocalDateTime", title = "The publication date of this post.",
            format = "ISO8601 date string",
            description = "Notes about the contact.",
            example = "13/09/2022 14:31:34", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.BRAZILIAN_DATE)
    @DateTimeFormat(pattern = Constants.BRAZILIAN_DATE)
    @NotNull
    private LocalDateTime startDate;

    @Schema(type = "LocalDateTime", title = "The publication date of this post.",
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

    @Schema(type = "Long", description = "Name of the Association.",
            example = "Jessica Abigail Association", required = false, ref = "Association")
    @NotNull
    private Long patientId;

    @Schema(type = "Long", description = "Name of the Association.",
            example = "Jessica Abigail Association", required = false, ref = "Association")
    @NotNull
    private Long psychologicalId;

    //user_account
}
