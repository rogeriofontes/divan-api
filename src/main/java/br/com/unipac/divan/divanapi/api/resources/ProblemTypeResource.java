package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.patient.ProblemTypeRequest;
import br.com.unipac.divan.divanapi.api.dto.response.patient.ProblemTypeResponse;
import br.com.unipac.divan.divanapi.api.mapper.ProblemTypeMapper;
import br.com.unipac.divan.divanapi.model.entities.patient.ProblemType;
import br.com.unipac.divan.divanapi.model.service.ProblemTypeService;
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
@RequestMapping("/v1/problem-types")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "ProblemTypeResource", description = "ProblemTypeResource management APIs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProblemTypeResource {
    private final ProblemTypeService problemTypeService;
    private final ProblemTypeMapper problemTypeMapper;

    /**
     * Gets all.
     *
     * @return the all
     */

    @Operation(summary = "Retrieve all ProblemTypes", tags = { "problemTypes", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProblemTypeResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProblemTypeResponse>> list() {
        List<ProblemType> problemTypes = problemTypeService.listAll();
        List<ProblemTypeResponse> problemTypeResponses = problemTypeMapper.map(problemTypes);
        return problemTypeResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(problemTypeResponses);
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @Operation(
            summary = "Retrieve a ProblemType by Id",
            description = "Get a ProblemType object by specifying its id. The response is Association object with id, title, description and published status.",
            tags = { "problemTypes", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ProblemTypeResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<ProblemTypeResponse> getById(@PathVariable("id") Long id) {
        Optional<ProblemType> problemTypes = problemTypeService.findById(id);
        if (problemTypes.isPresent()) {
            ProblemTypeResponse problemTypeResponse = problemTypeMapper.to(problemTypes.get());
            return ResponseEntity.ok(problemTypeResponse);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Add response entity.
     *
     * @param problemTypeRequest the ProblemType request
     * @return the response entity
     */

    @Operation(summary = "Create a new ProblemType", tags = { "problemTypes", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = ProblemTypeResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<ProblemTypeResponse> add(@Valid @RequestBody ProblemTypeRequest problemTypeRequest) throws Exception {
        ProblemType problemType = problemTypeMapper.from(problemTypeRequest);

        ProblemType problemTypeSaved = problemTypeService.save(problemType);

        ProblemTypeResponse problemTypeResponse = problemTypeMapper.to(problemTypeSaved);
        URI location = RestUtils.getUri(problemTypeResponse.getId());
        return ResponseEntity.created(location).body(problemTypeResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param problemTypeRequest the ProblemType request
     * @return the response entity
     */

    @Operation(summary = "Update a ProblemType by Id", tags = { "problemTypes", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProblemTypeResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping(path = "/{id}")
    public ResponseEntity<ProblemTypeResponse> change(@PathVariable("id") Long id, @RequestBody ProblemTypeRequest problemTypeRequest) {
        ProblemType problemType = problemTypeMapper.from(problemTypeRequest);

        ProblemType problemTypeSaved = problemTypeService.edit(id, problemType);

        ProblemTypeResponse problemTypeResponse = problemTypeMapper.to(problemTypeSaved);
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

    @Operation(summary = "Delete a ProblemType by Id", tags = { "problemTypes", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = problemTypeService.remove(id);
        return removed ? ResponseEntity.ok("Dados deletados!") : ResponseEntity.notFound().build();
    }
}