package br.com.unipac.divan.divanapi.api.dto.request.association.project;


import br.com.unipac.divan.divanapi.api.dto.request.association.AssociationRequest;
import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class ProjectRequest extends AuditModel {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String shortDescription;
    private String description;
    private LocalDateTime start_date;

    private LocalDateTime foundationDate;
    private String email;
    private String address;
    private String about;
    private boolean online;
    private String url;
    private boolean repeat;
    private AssociationRequest association;

}
