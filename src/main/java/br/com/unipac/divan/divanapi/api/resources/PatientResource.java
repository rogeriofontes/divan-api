package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.patient.PatientRequest;
import br.com.unipac.divan.divanapi.api.dto.response.patient.PatientResponse;
import br.com.unipac.divan.divanapi.api.mapper.PatientMapper;
import br.com.unipac.divan.divanapi.model.entities.patient.Patient;
import br.com.unipac.divan.divanapi.model.service.PatientService;
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

/**
 * The type Patient resources.
 *
 * @author Rogério Fontes
 */

@Slf4j
@RestController
@RequestMapping("/v1/patients")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "PatientResource", description = "PatientResource management APIs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientResource {
    private final PatientService patientService;
    private final PatientMapper patientMapper;

    /**
     * Gets all.
     *
     * @return the all
     */

    @Operation(summary = "Retrieve all Patient", tags = { "patients", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PatientResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PatientResponse>> list() {
        List<Patient> patients = patientService.listAll();
        List<PatientResponse> patientResponses = patientMapper.map(patients);
        return patientResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(patientResponses);
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @Operation(
            summary = "Retrieve a Patient by Id",
            description = "Get a Patient object by specifying its id. The response is Association object with id, title, description and published status.",
            tags = { "patients", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = PatientResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<PatientResponse> getById(@PathVariable("id") Long id) {
        Optional<Patient> patients = patientService.findById(id);
        if (patients.isPresent()) {
            PatientResponse patientResponse = patientMapper.to(patients.get());
            return ResponseEntity.ok(patientResponse);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Add response entity.
     *
     * @param patientRequest the Patient request
     * @return the response entity
     */

    @Operation(summary = "Create a new Patient", tags = { "patients", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = PatientResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<PatientResponse> add(@Valid @RequestBody PatientRequest patientRequest) throws Exception {
        Patient patient = patientMapper.from(patientRequest);

        Patient patientSaved = patientService.save(patient);

        PatientResponse patientResponse = patientMapper.to(patientSaved);
        URI location = RestUtils.getUri(patientResponse.getId());
        return ResponseEntity.created(location).body(patientResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param patientRequest the Patient request
     * @return the response entity
     */

    @Operation(summary = "Update a Patient by Id", tags = { "patients", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PatientResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping(path = "/{id}")
    public ResponseEntity<PatientResponse> change(@PathVariable("id") Long id, @RequestBody PatientRequest patientRequest) {
        Patient patient = patientMapper.from(patientRequest);

        Patient patientSaved = patientService.edit(id, patient);

        PatientResponse patientResponse = patientMapper.to(patientSaved);
        return patientResponse != null ?
                ResponseEntity.ok(patientResponse) :
                ResponseEntity.notFound().build();
    }

    /**
     * Remove response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @Operation(summary = "Delete a Patient by Id", tags = { "patients", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = patientService.remove(id);
        return removed ? ResponseEntity.ok("Dados deletados!") : ResponseEntity.notFound().build();
    }
}