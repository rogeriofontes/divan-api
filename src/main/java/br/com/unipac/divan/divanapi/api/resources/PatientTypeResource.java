package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.patient.PatientTypeRequest;
import br.com.unipac.divan.divanapi.api.dto.response.patient.PatientTypeResponse;
import br.com.unipac.divan.divanapi.api.mapper.PatientTypeMapper;
import br.com.unipac.divan.divanapi.model.entities.patient.PatientType;
import br.com.unipac.divan.divanapi.model.service.PatientTypeService;
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
@RequestMapping("/v1/patient-types")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "PatientTypeResource", description = "PatientTypeResource management APIs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientTypeResource {
    private final PatientTypeService patientTypeService;
    private final PatientTypeMapper patientTypeMapper;

    /**
     * Gets all.
     *
     * @return the all
     */

    @Operation(summary = "Retrieve all PatientTypes", tags = { "patientTypes", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PatientTypeResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PatientTypeResponse>> list() {
        List<PatientType> patientTypes = patientTypeService.listAll();
        List<PatientTypeResponse> patientTypeResponses = patientTypeMapper.map(patientTypes);
        return patientTypeResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(patientTypeResponses);
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @Operation(
            summary = "Retrieve a PatientType by Id",
            description = "Get a PatientType object by specifying its id. The response is Association object with id, title, description and published status.",
            tags = { "patientTypes", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = PatientTypeResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<PatientTypeResponse> getById(@PathVariable("id") Long id) {
        Optional<PatientType> patientTypes = patientTypeService.findById(id);
        if (patientTypes.isPresent()) {
            PatientTypeResponse patientTypeResponse = patientTypeMapper.to(patientTypes.get());
            return ResponseEntity.ok(patientTypeResponse);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Add response entity.
     *
     * @param patientTypeRequest the PatientType request
     * @return the response entity
     */

    @Operation(summary = "Create a new PatientType", tags = { "patientTypes", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = PatientTypeResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<PatientTypeResponse> add(@Valid @RequestBody PatientTypeRequest patientTypeRequest) throws Exception {
        PatientType patientType = patientTypeMapper.from(patientTypeRequest);

        PatientType patientTypeSaved = patientTypeService.save(patientType);

        PatientTypeResponse patientTypeResponse = patientTypeMapper.to(patientTypeSaved);
        URI location = RestUtils.getUri(patientTypeResponse.getId());
        return ResponseEntity.created(location).body(patientTypeResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param patientTypeRequest the PatientType request
     * @return the response entity
     */

    @Operation(summary = "Update a PatientType by Id", tags = { "patientTypes", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PatientTypeResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping(path = "/{id}")
    public ResponseEntity<PatientTypeResponse> change(@PathVariable("id") Long id, @RequestBody PatientTypeRequest patientTypeRequest) {
        PatientType patientType = patientTypeMapper.from(patientTypeRequest);

        PatientType patientTypeSaved = patientTypeService.edit(id, patientType);

        PatientTypeResponse patientTypeResponse = patientTypeMapper.to(patientTypeSaved);
        return patientTypeResponse != null ?
                ResponseEntity.ok(patientTypeResponse) :
                ResponseEntity.notFound().build();
    }

    /**
     * Remove response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @Operation(summary = "Delete a PatientType by Id", tags = { "patientTypes", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = patientTypeService.remove(id);
        return removed ? ResponseEntity.ok("Dados deletados!") : ResponseEntity.notFound().build();
    }
}