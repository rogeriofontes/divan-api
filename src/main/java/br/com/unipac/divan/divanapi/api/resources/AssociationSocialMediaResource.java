package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.association.AssociationSocialMediaRequest;
import br.com.unipac.divan.divanapi.api.dto.response.association.AssociationSocialMediaResponse;
import br.com.unipac.divan.divanapi.api.mapper.AssociationSocialMediaMapper;
import br.com.unipac.divan.divanapi.model.entities.association.AssociationSocialMedia;
import br.com.unipac.divan.divanapi.model.service.AssociationSocialMediaService;
import br.com.unipac.divan.divanapi.util.RestUtils;
import io.swagger.annotations.*;
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
@Api(value = "AssociationSocialMedias")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssociationSocialMediaResource {
    private final AssociationSocialMediaService associationService;
    private final AssociationSocialMediaMapper associationMapper;

    /**
     * Gets all.
     *
     * @return the all
     */

    @ApiOperation(value = "View a list of available AssociationSocialMedia details", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource of AssociationSocialMedia"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
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

    @ApiOperation(value = "Get AssociationSocialMedia details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other AssociationSocialMedia ID"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @GetMapping(path = "/{id}")
    @ResponseBody
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
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

    @ApiOperation(value = "Create new AssociationSocialMedia", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! AssociationSocialMedia you are looking for does not exist. Try with other AssociationSocialMedia ID")
    })
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
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

    @ApiOperation(value = "Update existing AssociationSocialMedia details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other AssociationSocialMedia ID")
    })
    @PutMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
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

    @ApiOperation(value = "Delete account on the basis of AssociationSocialMedia ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource")
    })
    @DeleteMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = associationService.remove(id);
        return removed ? ResponseEntity.ok("Dados deletados!") : ResponseEntity.notFound().build();
    }
}