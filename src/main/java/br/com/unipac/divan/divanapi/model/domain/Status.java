package br.com.unipac.divan.divanapi.model.domain;

import io.swagger.annotations.ApiModel;

/**
 * The enum Status.
 */
@ApiModel(value = "Status", description = "Model")
public enum Status {
    /**
     * Ativo status.
     */
    ATIVO,
    /**
     * Inativo status.
     */
    INATIVO;
}
