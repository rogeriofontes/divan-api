package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.psychological.PsychologicalTypeRequest;
import br.com.unipac.divan.divanapi.api.dto.response.psychological.PsychologicalTypeResponse;
import br.com.unipac.divan.divanapi.api.mapper.PsychologicalTypeMapper;
import br.com.unipac.divan.divanapi.model.entities.psychological.PsychologicalType;
import br.com.unipac.divan.divanapi.model.service.PsychologicalTypeService;
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
@RequestMapping("/v1/psychological-specialities")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "PsychologicalTypeResource", description = "PsychologicalTypeResource management APIs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PsychologicalTypeResource {
    private final PsychologicalTypeService psychologicalTypeService;
    private final PsychologicalTypeMapper psychologicalTypeMapper;

    /**
     * Gets all.
     *
     * @return the all
     */

    @Operation(summary = "Retrieve all PsychologicalType", tags = { "psychologicalTypes", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PsychologicalTypeResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no PsychologicalTypes", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PsychologicalTypeResponse>> list() {
        List<PsychologicalType> psychologicalSpecialities = psychologicalTypeService.listAll();
        List<PsychologicalTypeResponse> psychologicalResponses = psychologicalTypeMapper.map(psychologicalSpecialities);
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
            summary = "Retrieve a PsychologicalType by Id",
            description = "Get a PsychologicalType object by specifying its id. The response is PsychologicalType object with id, title, description and published status.",
            tags = { "psychologicalTypes", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = PsychologicalTypeResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<PsychologicalTypeResponse> getById(@PathVariable("id") Long id) {
        Optional<PsychologicalType> problemTypes = psychologicalTypeService.findById(id);
        if (problemTypes.isPresent()) {
            PsychologicalTypeResponse problemTypeResponse = psychologicalTypeMapper.to(problemTypes.get());
            return ResponseEntity.ok(problemTypeResponse);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Add response entity.
     *
     * @param problemTypeRequest the PsychologicalType request
     * @return the response entity
     */

    @Operation(summary = "Create a new PsychologicalType", tags = { "psychologicalTypes", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = PsychologicalTypeResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<PsychologicalTypeResponse> add(@Valid @RequestBody PsychologicalTypeRequest problemTypeRequest) throws Exception {
        PsychologicalType problemType = psychologicalTypeMapper.from(problemTypeRequest);

        PsychologicalType problemTypeSaved = psychologicalTypeService.save(problemType);

        PsychologicalTypeResponse problemTypeResponse = psychologicalTypeMapper.to(problemTypeSaved);
        URI location = RestUtils.getUri(problemTypeResponse.getId());
        return ResponseEntity.created(location).body(problemTypeResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param problemTypeRequest the PsychologicalType request
     * @return the response entity
     */

    @Operation(summary = "Update a PsychologicalType by Id", tags = { "psychologicalTypes", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PsychologicalTypeResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping(path = "/{id}")
    public ResponseEntity<PsychologicalTypeResponse> change(@PathVariable("id") Long id, @RequestBody PsychologicalTypeRequest problemTypeRequest) {
        PsychologicalType problemType = psychologicalTypeMapper.from(problemTypeRequest);

        PsychologicalType problemTypeSaved = psychologicalTypeService.edit(id, problemType);

        PsychologicalTypeResponse problemTypeResponse = psychologicalTypeMapper.to(problemTypeSaved);
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

    @Operation(summary = "Delete a PsychologicalType by Id", tags = { "psychologicalTypes", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = psychologicalTypeService.remove(id);
        return removed ? ResponseEntity.ok("Dados deletados!") : ResponseEntity.notFound().build();
    }
}