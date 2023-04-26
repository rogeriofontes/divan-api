package br.com.unipac.divan.divanapi.api.dto.response.association;

import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "tb_association_social_media")
public class AssociationSocialMediaResponse extends AuditModel {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String domain;
    private String link;
    private String logo;
    private String twitter;
    private String facebook;
    private String instagram;
    private AssociationResponse association;


    //user_account
}
