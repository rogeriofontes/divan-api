package br.com.unipac.divan.divanapi.api.dto.response.association;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class AssociationSocialMediaResponse implements Serializable {

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
