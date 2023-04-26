package br.com.unipac.divan.divanapi.model.entities.association;

import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import br.com.unipac.divan.divanapi.model.entities.patient.Patient;
import br.com.unipac.divan.divanapi.model.entities.patient.PatientType;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "tb_association_social_media")
public class AssociationSocialMedia extends AuditModel {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String domain;
    private String link;
    private String logo;
    private String twitter;
    private String facebook;
    private String instagram;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "association_id")
    @ApiModelProperty(value = "Association")
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
