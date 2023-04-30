package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.patient.PatientProblemRequest;
import br.com.unipac.divan.divanapi.api.dto.response.patient.PatientProblemResponse;
import br.com.unipac.divan.divanapi.api.mapper.PatientProblemMapper;
import br.com.unipac.divan.divanapi.model.entities.patient.PatientProblem;
import br.com.unipac.divan.divanapi.model.service.PatientProblemService;
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
@RequestMapping("/v1/patient-problems")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "PatientProblemResource", description = "PatientProblemResource management APIs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientProblemResource {
    private final PatientProblemService patientProblemService;
    private final PatientProblemMapper patientProblemMapper;

    /**
     * Gets all.
     *
     * @return the all
     */

    @Operation(summary = "Retrieve all PatientProblems", tags = { "patientProblems", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PatientProblemResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PatientProblemResponse>> list() {
        List<PatientProblem> patientProblems = patientProblemService.listAll();
        List<PatientProblemResponse> patientProblemResponses = patientProblemMapper.map(patientProblems);
        return patientProblemResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(patientProblemResponses);
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @Operation(
            summary = "Retrieve a PatientProblem by Id",
            description = "Get a PatientProblem object by specifying its id. The response is Association object with id, title, description and published status.",
            tags = { "patientProblems", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = PatientProblemResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<PatientProblemResponse> getById(@PathVariable("id") Long id) {
        Optional<PatientProblem> patientProblems = patientProblemService.findById(id);
        if (patientProblems.isPresent()) {
            PatientProblemResponse patientProblemResponse = patientProblemMapper.to(patientProblems.get());
            return ResponseEntity.ok(patientProblemResponse);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Add response entity.
     *
     * @param patientProblemRequest the PatientProblem request
     * @return the response entity
     */

    @Operation(summary = "Create a new PatientProblem", tags = { "patientProblems", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = PatientProblemResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<PatientProblemResponse> add(@Valid @RequestBody PatientProblemRequest patientProblemRequest) throws Exception {
        PatientProblem patientProblem = patientProblemMapper.from(patientProblemRequest);

        PatientProblem patientProblemSaved = patientProblemService.save(patientProblem);

        PatientProblemResponse patientProblemResponse = patientProblemMapper.to(patientProblemSaved);
        URI location = RestUtils.getUri(patientProblemResponse.getId());
        return ResponseEntity.created(location).body(patientProblemResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param patientProblemRequest the PatientProblem request
     * @return the response entity
     */

    @Operation(summary = "Update a PatientProblem by Id", tags = { "patientProblems", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PatientProblemResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping(path = "/{id}")
    public ResponseEntity<PatientProblemResponse> change(@PathVariable("id") Long id, @RequestBody PatientProblemRequest patientProblemRequest) {
        PatientProblem patientProblem = patientProblemMapper.from(patientProblemRequest);

        PatientProblem patientProblemSaved = patientProblemService.edit(id, patientProblem);

        PatientProblemResponse patientProblemResponse = patientProblemMapper.to(patientProblemSaved);
        return patientProblemResponse != null ?
                ResponseEntity.ok(patientProblemResponse) :
                ResponseEntity.notFound().build();
    }

    /**
     * Remove response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @Operation(summary = "Delete a PatientProblem by Id", tags = { "patientProblems", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = patientProblemService.remove(id);
        return removed ? ResponseEntity.ok("Dados deletados!") : ResponseEntity.notFound().build();
    }
}