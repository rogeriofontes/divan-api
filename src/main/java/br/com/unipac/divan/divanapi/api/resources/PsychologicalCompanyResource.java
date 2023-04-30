package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.psychological.PsychologicalCompanyRequest;
import br.com.unipac.divan.divanapi.api.dto.response.psychological.PsychologicalCompanyResponse;
import br.com.unipac.divan.divanapi.api.mapper.PsychologicalCompanyMapper;
import br.com.unipac.divan.divanapi.model.entities.psychological.PsychologicalCompany;
import br.com.unipac.divan.divanapi.model.service.PsychologicalCompanyService;
import br.com.unipac.divan.divanapi.util.RestUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1/psychological-companies")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "PsychologicalCompanyResource", description = "PsychologicalCompanyResource management APIs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PsychologicalCompanyResource {
    private final PsychologicalCompanyService psychologicalCompanyService;
    private final PsychologicalCompanyMapper psychologicalCompanyMapper;

    /**
     * Gets all.
     *
     * @return the all
     */

    @Operation(summary = "Retrieve all PsychologicalCompany", tags = { "psychologicalCompanys", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PsychologicalCompanyResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PsychologicalCompanyResponse>> list() {
        List<PsychologicalCompany> psychologicalSpecialities = psychologicalCompanyService.listAll();
        List<PsychologicalCompanyResponse> psychologicalResponses = psychologicalCompanyMapper.map(psychologicalSpecialities);
        return psychologicalResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(psychologicalResponses);
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @Operation(
            summary = "Retrieve a Association by Id",
            description = "Get a PsychologicalCompany object by specifying its id. The response is Association object with id, title, description and published status.",
            tags = { "psychologicalCompanys", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = PsychologicalCompanyResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<PsychologicalCompanyResponse> getById(@PathVariable("id") Long id) {
        Optional<PsychologicalCompany> problemTypes = psychologicalCompanyService.findById(id);
        if (problemTypes.isPresent()) {
            PsychologicalCompanyResponse problemTypeResponse = psychologicalCompanyMapper.to(problemTypes.get());
            return ResponseEntity.ok(problemTypeResponse);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Add response entity.
     *
     * @param problemTypeRequest the PsychologicalCompany request
     * @return the response entity
     */

    @Operation(summary = "Create a new PsychologicalCompany", tags = { "psychologicalCompanys", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = PsychologicalCompanyResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<PsychologicalCompanyResponse> add(@Valid @RequestBody PsychologicalCompanyRequest problemTypeRequest) throws Exception {
        PsychologicalCompany problemType = psychologicalCompanyMapper.from(problemTypeRequest);

        PsychologicalCompany problemTypeSaved = psychologicalCompanyService.save(problemType);

        PsychologicalCompanyResponse problemTypeResponse = psychologicalCompanyMapper.to(problemTypeSaved);
        URI location = RestUtils.getUri(problemTypeResponse.getId());
        return ResponseEntity.created(location).body(problemTypeResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param problemTypeRequest the PsychologicalCompany request
     * @return the response entity
     */

    @Operation(summary = "Update a PsychologicalCompany by Id", tags = { "psychologicalCompanys", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PsychologicalCompanyResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping(path = "/{id}")
    public ResponseEntity<PsychologicalCompanyResponse> change(@PathVariable("id") Long id, @RequestBody PsychologicalCompanyRequest problemTypeRequest) {
        PsychologicalCompany problemType = psychologicalCompanyMapper.from(problemTypeRequest);

        PsychologicalCompany problemTypeSaved = psychologicalCompanyService.edit(id, problemType);

        PsychologicalCompanyResponse problemTypeResponse = psychologicalCompanyMapper.to(problemTypeSaved);
        return problemTypeResponse != null ?
                ResponseEntity.ok(problemTypeResponse) :
                ResponseEntity.notFound().build();
    }

    /**
     * Remove response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @Operation(summary = "Delete a PsychologicalCompany by Id", tags = { "psychologicalCompanys", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = psychologicalCompanyService.remove(id);
        return removed ? ResponseEntity.ok("Dados deletados!") : ResponseEntity.notFound().build();
    }
}