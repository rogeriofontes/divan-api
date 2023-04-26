package br.com.unipac.divan.divanapi.model.domain;

import br.com.unipac.divan.divanapi.constants.Constants;
import br.com.unipac.divan.divanapi.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * The type Audit model.
 *
 * @author Rogério Fontes
 */
@Data
@NoArgsConstructor
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt, updatedAt"}, allowGetters = true, ignoreUnknown = true)
@ApiModel(value = "AuditModel", description = "Model")
public abstract class AuditModel implements Serializable {

    private static final long serialVersionUID = 3661315543425633373L;

    @NotNull
    @Column(name = "created_date", nullable = false)
    @JsonIgnore
    @CreatedDate
    @ApiModelProperty(notes = "createdDate")
    private LocalDateTime createdDate = DateUtil.convert(new Date());

    @NotNull
    @Column(name = "create_by")
    @JsonIgnore
    @CreatedBy
    @ApiModelProperty(notes = "createBy")
    private String createBy = Constants.CURRENT_USER;

    @Column(name = "last_modified_date")
    @JsonIgnore
    @LastModifiedDate
    @Getter
    @Setter
    @ApiModelProperty(notes = "lastModifiedDate")
    private LocalDateTime lastModifiedDate;

    @Column(name = "last_modified_by")
    @JsonIgnore
    @LastModifiedBy
    @Getter
    @Setter
    @ApiModelProperty(notes = "lastModifiedBy")
    private String lastModifiedBy;

    @Column(name = "status", nullable = false)
    @NotNull(message = "o campo \"status\" é obrigario")
    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "status")
    private Status status = Status.ATIVO;

}
