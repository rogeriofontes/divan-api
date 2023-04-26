package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.association.AssociationSocialMediaRequest;
import br.com.unipac.divan.divanapi.api.dto.response.association.AssociationSocialMediaResponse;
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

/**
 * The type AssociationSocialMedia resources.
 *
 * @author Rogério Fontes
 */

@Slf4j
@RestController
@RequestMapping("/v1/associationSocialMedias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "AssociationSocialMedias")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssociationSocialMediaResources {
    private final AssociationSocialMediaAdapter associationSocialMediaAdapter;

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
        List<AssociationSocialMediaResponse> associationSocialMediaResponses = associationSocialMediaAdapter.list();
        return associationSocialMediaResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(associationSocialMediaResponses);
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
        AssociationSocialMediaResponse associationSocialMediaResponse = associationSocialMediaAdapter.convertToFindById(id);
        return ResponseEntity.ok(associationSocialMediaResponse);
    }

    /**
     * Add response entity.
     *
     * @param associationSocialMediaRequest the AssociationSocialMedia request
     * @return the response entity
     */

    @ApiOperation(value = "Create new AssociationSocialMedia", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! AssociationSocialMedia you are looking for does not exist. Try with other AssociationSocialMedia ID")
    })
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<AssociationSocialMediaResponse> add(@Valid @RequestBody AssociationSocialMediaRequest associationSocialMediaRequest) throws Exception {
        AssociationSocialMediaResponse associationSocialMediaResponse = associationSocialMediaAdapter.convertToCreate(associationSocialMediaRequest);

        URI location = RestUtils.getUri(associationSocialMediaResponse.getId());
        return ResponseEntity.created(location).body(associationSocialMediaResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param associationSocialMediaRequest the AssociationSocialMedia request
     * @return the response entity
     */

    @ApiOperation(value = "Update existing AssociationSocialMedia details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other AssociationSocialMedia ID")
    })
    @PutMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<AssociationSocialMediaResponse> change(@PathVariable("id") Long id, @RequestBody AssociationSocialMediaRequest associationSocialMediaRequest) {
        AssociationSocialMediaResponse associationSocialMediaResponse = associationSocialMediaAdapter.convertToChange(id, associationSocialMediaRequest);
        return associationSocialMediaResponse != null ?
                ResponseEntity.ok(associationSocialMediaResponse) :
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
        boolean removed = associationSocialMediaAdapter.convertToRemove(id);
        return removed ? ResponseEntity.ok(Constants.DADOS_DELETADOS) : ResponseEntity.notFound().build();
    }
}