package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.association.AssociationRequest;
import br.com.unipac.divan.divanapi.api.dto.response.association.AssociationResponse;
import br.com.unipac.divan.divanapi.api.mapper.AssociationMapper;
import br.com.unipac.divan.divanapi.model.entities.association.Association;
import br.com.unipac.divan.divanapi.model.service.AssociationService;
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
 * The type Association resources.
 *
 * @author Rogério Fontes
 */

@Slf4j
@RestController
@RequestMapping("/v1/associations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "AssociationResource", description = "AssociationResource management APIs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssociationResource {
    private final AssociationService associationService;
    private final AssociationMapper associationMapper;

    /**
     * Gets all.
     *
     * @return the all
     */

    @Operation(summary = "Retrieve all Tutorials", tags = { "tutorials", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AssociationResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no Tutorials", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<AssociationResponse>> list() {
        List<Association> associations = associationService.listAll();
        List<AssociationResponse> associationResponses = associationMapper.map(associations);
        return associationResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(associationResponses);
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @Operation(
            summary = "Retrieve a Tutorial by Id",
            description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status.",
            tags = { "tutorials", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = AssociationResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<AssociationResponse> getById(@PathVariable("id") Long id) {
        Optional<Association> associations = associationService.findById(id);
        if (associations.isPresent()) {
            AssociationResponse associationResponse = associationMapper.to(associations.get());
            return ResponseEntity.ok(associationResponse);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Add response entity.
     *
     * @param associationRequest the Association request
     * @return the response entity
     */

    @Operation(summary = "Create a new Tutorial", tags = { "tutorials", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = AssociationResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<AssociationResponse> add(@Valid @RequestBody AssociationRequest associationRequest) throws Exception {
        Association association = associationMapper.from(associationRequest);

        Association associationSaved = associationService.save(association);

        AssociationResponse associationResponse = associationMapper.to(associationSaved);
        URI location = RestUtils.getUri(associationResponse.getId());
        return ResponseEntity.created(location).body(associationResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param associationRequest the Association request
     * @return the response entity
     */

    @Operation(summary = "Update a Tutorial by Id", tags = { "tutorials", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AssociationResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping(path = "/{id}")
    public ResponseEntity<AssociationResponse> change(@PathVariable("id") Long id, @RequestBody AssociationRequest associationRequest) {
        Association association = associationMapper.from(associationRequest);

        Association associationSaved = associationService.edit(id, association);

        AssociationResponse associationResponse = associationMapper.to(associationSaved);
        return associationResponse != null ?
                ResponseEntity.ok(associationResponse) :
                ResponseEntity.notFound().build();
    }

    /**
     * Remove response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @Operation(summary = "Delete a Tutorial by Id", tags = { "tutorials", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = associationService.remove(id);
        return removed ? ResponseEntity.ok("Dados deletados!") : ResponseEntity.notFound().build();
    }
}