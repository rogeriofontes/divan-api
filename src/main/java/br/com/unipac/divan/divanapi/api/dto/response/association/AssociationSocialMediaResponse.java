package br.com.unipac.divan.divanapi.api.dto.response.association;

import br.com.unipac.divan.divanapi.model.entities.association.Association;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Schema(description = "Patient object")
public class AssociationSocialMediaResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Domain of the Association.",
            example = "jessicaabigail", required = false)
    private String domain;

    @Schema(description = "Link of domain of the Association.",
            example = "www.jessicaabigail.com.br", required = false)
    private String link;

    @Schema(description = "Logo of the Association.",
            example = "img/png", required = false)
    private String logo;

    @Schema(description = "Twitter of the Association.",
            example = "@jessica_abigail", required = false)
    private String twitter;

    @Schema(description = "Facebook of the Association.",
            example = "@jessica_abigail", required = false)
    private String facebook;

    @Schema(description = "Instagram of the Association.",
            example = "@jessica_abigail", required = false)
    private String instagram;

    @Schema(description = "Name of the Association.",
            example = "Jessica Abigail Association", required = false, ref = "Association")
    @NotNull
    private Association association;

    //user_account
}
