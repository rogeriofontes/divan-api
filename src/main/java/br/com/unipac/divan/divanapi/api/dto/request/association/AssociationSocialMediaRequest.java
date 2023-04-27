package br.com.unipac.divan.divanapi.api.dto.request.association;

import lombok.*;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class AssociationSocialMediaRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String domain;
    private String link;
    private String logo;
    private String twitter;
    private String facebook;
    private String instagram;
    private Long associationId;


    //user_account
}
