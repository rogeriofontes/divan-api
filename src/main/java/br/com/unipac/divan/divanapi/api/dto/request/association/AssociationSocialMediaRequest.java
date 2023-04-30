package br.com.unipac.divan.divanapi.api.dto.request.association;

import br.com.unipac.divan.divanapi.model.entities.association.Association;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Schema(description = "Association Social Media Request")
public class AssociationSocialMediaRequest implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private Long associationId;


    //user_account
}
