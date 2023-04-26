package br.com.unipac.divan.divanapi.api.resources;


import br.com.unipac.divan.divanapi.api.dto.request.association.AssociationRequest;
import br.com.unipac.divan.divanapi.api.dto.response.association.AssociationResponse;
import br.com.unipac.divan.divanapi.api.mapper.AssociationMapper;
import br.com.unipac.divan.divanapi.model.entities.association.Association;
import br.com.unipac.divan.divanapi.model.service.AssociationService;
import br.com.unipac.divan.divanapi.util.RestUtils;
import io.swagger.annotations.*;
import jakarta.validation.Valid;
import lombok.*;
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
@Api(value = "Associations")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssociationResource {
    private final AssociationService associationService;
    private final AssociationMapper associationMapper;

    /**
     * Gets all.
     *
     * @return the all
     */

    @ApiOperation(value = "View a list of available Association details", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource of Association"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
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

    @ApiOperation(value = "Get Association details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other Association ID"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @GetMapping(path = "/{id}")
    @ResponseBody
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
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

    @ApiOperation(value = "Create new Association", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Association you are looking for does not exist. Try with other Association ID")
    })
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
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

    @ApiOperation(value = "Update existing Association details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other Association ID")
    })
    @PutMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
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

    @ApiOperation(value = "Delete account on the basis of Association ID", response = ResponseEntity.class)
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