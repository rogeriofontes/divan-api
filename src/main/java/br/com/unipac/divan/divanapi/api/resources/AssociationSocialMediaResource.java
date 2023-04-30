package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.association.AssociationSocialMediaRequest;
import br.com.unipac.divan.divanapi.api.dto.response.association.AssociationSocialMediaResponse;
import br.com.unipac.divan.divanapi.api.mapper.AssociationSocialMediaMapper;
import br.com.unipac.divan.divanapi.model.entities.association.AssociationSocialMedia;
import br.com.unipac.divan.divanapi.model.service.AssociationSocialMediaService;
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
 * The type AssociationSocialMediaSocialMedia resources.
 *
 * @author Rogério Fontes
 */

@Slf4j
@RestController
@RequestMapping("/v1/association-social-medias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "AssociationSocialMediaResource", description = "AssociationSocialMediaResource management APIs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssociationSocialMediaResource {
    private final AssociationSocialMediaService associationService;
    private final AssociationSocialMediaMapper associationMapper;

    /**
     * Gets all.
     *
     * @return the all
     */

    @Operation(summary = "Retrieve all AssociationSocialMedias", tags = { "associationSocialMedias", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AssociationSocialMediaResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no AssociationSocialMedias", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<AssociationSocialMediaResponse>> list() {
        List<AssociationSocialMedia> associations = associationService.listAll();
        List<AssociationSocialMediaResponse> associationResponses = associationMapper.map(associations);
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
            summary = "Retrieve a AssociationSocialMedia by Id",
            description = "Get a AssociationSocialMedia object by specifying its id. The response is AssociationSocialMedia object with id, title, description and published status.",
            tags = { "associationSocialMedias", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = AssociationSocialMediaResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<AssociationSocialMediaResponse> getById(@PathVariable("id") Long id) {
        Optional<AssociationSocialMedia> associations = associationService.findById(id);
        if (associations.isPresent()) {
            AssociationSocialMediaResponse associationResponse = associationMapper.to(associations.get());
            return ResponseEntity.ok(associationResponse);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Add response entity.
     *
     * @param associationRequest the AssociationSocialMedia request
     * @return the response entity
     */

    @Operation(summary = "Create a new AssociationSocialMedia", tags = { "associationSocialMedias", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = AssociationSocialMediaResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<AssociationSocialMediaResponse> add(@Valid @RequestBody AssociationSocialMediaRequest associationRequest) throws Exception {
        AssociationSocialMedia association = associationMapper.from(associationRequest);

        AssociationSocialMedia associationSaved = associationService.save(association);

        AssociationSocialMediaResponse associationResponse = associationMapper.to(associationSaved);
        URI location = RestUtils.getUri(associationResponse.getId());
        return ResponseEntity.created(location).body(associationResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param associationRequest the AssociationSocialMedia request
     * @return the response entity
     */

    @Operation(summary = "Update a AssociationSocialMedia by Id", tags = { "associationSocialMedias", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = AssociationSocialMediaResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping(path = "/{id}")
    public ResponseEntity<AssociationSocialMediaResponse> change(@PathVariable("id") Long id, @RequestBody AssociationSocialMediaRequest associationRequest) {
        AssociationSocialMedia association = associationMapper.from(associationRequest);

        AssociationSocialMedia associationSaved = associationService.edit(id, association);

        AssociationSocialMediaResponse associationResponse = associationMapper.to(associationSaved);
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
    @Operation(summary = "Delete a AssociationSocialMedia by Id", tags = { "associationSocialMedias", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = associationService.remove(id);
        return removed ? ResponseEntity.ok("Dados deletados!") : ResponseEntity.notFound().build();
    }
}