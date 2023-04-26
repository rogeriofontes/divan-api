package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.psychological.PsychologicalSpecialityRequest;
import br.com.unipac.divan.divanapi.api.dto.response.psychological.PsychologicalSpecialityResponse;
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

@Slf4j
@RestController
@RequestMapping("/v1/blocks")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "PsychologicalSpecialitys")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PsychologicalSpecialityResources {
    private final PsychologicalSpecialityAdapter blockAdapter;

    /**
     * Gets all.
     *
     * @return the all
     */

    @ApiOperation(value = "View a list of available PsychologicalSpeciality details", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource of PsychologicalSpeciality"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PsychologicalSpecialityResponse>> list() {
        List<PsychologicalSpecialityResponse> blockResponses = blockAdapter.list();
        return blockResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(blockResponses);
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @ApiOperation(value = "Get PsychologicalSpeciality details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other PsychologicalSpeciality ID"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @GetMapping(path = "/{id}")
    @ResponseBody
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PsychologicalSpecialityResponse> getById(@PathVariable("id") Long id) {
        PsychologicalSpecialityResponse blockResponse = blockAdapter.convertToFindById(id);
        return ResponseEntity.ok(blockResponse);
    }

    /**
     * Add response entity.
     *
     * @param blockRequest the PsychologicalSpeciality request
     * @return the response entity
     */

    @ApiOperation(value = "Create new PsychologicalSpeciality", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! PsychologicalSpeciality you are looking for does not exist. Try with other PsychologicalSpeciality ID")
    })
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PsychologicalSpecialityResponse> add(@Valid @RequestBody PsychologicalSpecialityRequest blockRequest) throws Exception {
        PsychologicalSpecialityResponse blockResponse = blockAdapter.convertToCreate(blockRequest);

        URI location = RestUtils.getUri(blockResponse.getId());
        return ResponseEntity.created(location).body(blockResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param blockRequest the PsychologicalSpeciality request
     * @return the response entity
     */

    @ApiOperation(value = "Update existing PsychologicalSpeciality details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other PsychologicalSpeciality ID")
    })
    @PutMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PsychologicalSpecialityResponse> change(@PathVariable("id") Long id, @RequestBody PsychologicalSpecialityRequest blockRequest) {
        PsychologicalSpecialityResponse blockResponse = blockAdapter.convertToChange(id, blockRequest);
        return blockResponse != null ?
                ResponseEntity.ok(blockResponse) :
                ResponseEntity.notFound().build();
    }

    /**
     * Remove response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @ApiOperation(value = "Delete account on the basis of PsychologicalSpeciality ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource")
    })
    @DeleteMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = blockAdapter.convertToRemove(id);
        return removed ? ResponseEntity.ok(Constants.DADOS_DELETADOS) : ResponseEntity.notFound().build();
    }
}