package br.com.unipac.divan.divanapi.api.dto.response.association.project;

import br.com.unipac.divan.divanapi.api.dto.response.association.AssociationResponse;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class ProjectResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String shortDescription;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime foundationDate;
    private String email;
    private String address;
    private String about;
    private boolean online;
    private String url;
    private boolean repeat;
    private AssociationResponse association;

}
