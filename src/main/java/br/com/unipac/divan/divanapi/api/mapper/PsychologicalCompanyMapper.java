package br.com.unipac.divan.divanapi.api.mapper;

import br.com.unipac.divan.divanapi.api.dto.request.psychological.PsychologicalCompanyRequest;
import br.com.unipac.divan.divanapi.api.dto.response.psychological.PsychologicalCompanyResponse;
import br.com.unipac.divan.divanapi.model.entities.psychological.PsychologicalCompany;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PsychologicalCompanyMapper {

    @InheritConfiguration
    @Mapping(source = "name", target = "name")
    @Mapping(source = "companyId", target = "companyId")
    @Mapping(source = "psychological", target = "psychological")
    PsychologicalCompany from(PsychologicalCompanyRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    PsychologicalCompanyResponse to(PsychologicalCompany psychologicalCompany);
    List<PsychologicalCompanyResponse> map(List<PsychologicalCompany> psychologicalCompanys);

}
