package br.com.unipac.divan.divanapi.model.entities.association;

import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "tb_association_social_media")
@Schema(description = "AssociationSocialMedia object")
public class AssociationSocialMedia extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the Association.",
            example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
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
    @ManyToOne
    @JoinColumn(name = "association_id")
    private Association association;

    public void update(Long id, AssociationSocialMedia associationSocialMedia) {
        this.id = id;
        this.domain = associationSocialMedia.getDomain();
        this.link = associationSocialMedia.getLink();
        this.logo = associationSocialMedia.getLogo();
        this.twitter = associationSocialMedia.getTwitter();
        this.facebook = associationSocialMedia.getFacebook();
        this.instagram = associationSocialMedia.getInstagram();
        this.association = associationSocialMedia.getAssociation();
    }

    //user_account
}
