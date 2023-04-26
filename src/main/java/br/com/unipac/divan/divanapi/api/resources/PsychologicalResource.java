package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.psychological.PsychologicalRequest;
import br.com.unipac.divan.divanapi.api.dto.response.psychological.PsychologicalResponse;
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
@RequestMapping("/v1/psychologicals")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Psychologicals")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PsychologicalResources {
    private final PsychologicalAdapter psychologicalAdapter;

    /**
     * Gets all.
     *
     * @return the all
     */

    @ApiOperation(value = "View a list of available Psychological details", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource of Psychological"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PsychologicalResponse>> list() {
        List<PsychologicalResponse> psychologicalResponses = psychologicalAdapter.list();
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

    @ApiOperation(value = "Get Psychological details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other Psychological ID"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @GetMapping(path = "/{id}")
    @ResponseBody
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PsychologicalResponse> getById(@PathVariable("id") Long id) {
        PsychologicalResponse psychologicalResponse = psychologicalAdapter.convertToFindById(id);
        return ResponseEntity.ok(psychologicalResponse);
    }

    /**
     * Add response entity.
     *
     * @param psychologicalRequest the Psychological request
     * @return the response entity
     */

    @ApiOperation(value = "Create new Psychological", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Psychological you are looking for does not exist. Try with other Psychological ID")
    })
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PsychologicalResponse> add(@Valid @RequestBody PsychologicalRequest psychologicalRequest) throws Exception {
        PsychologicalResponse psychologicalResponse = psychologicalAdapter.convertToCreate(psychologicalRequest);

        URI location = RestUtils.getUri(psychologicalResponse.getId());
        return ResponseEntity.created(location).body(psychologicalResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param psychologicalRequest the Psychological request
     * @return the response entity
     */

    @ApiOperation(value = "Update existing Psychological details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other Psychological ID")
    })
    @PutMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PsychologicalResponse> change(@PathVariable("id") Long id, @RequestBody PsychologicalRequest psychologicalRequest) {
        PsychologicalResponse psychologicalResponse = psychologicalAdapter.convertToChange(id, psychologicalRequest);
        return psychologicalResponse != null ?
                ResponseEntity.ok(psychologicalResponse) :
                ResponseEntity.notFound().build();
    }

    /**
     * Remove response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @ApiOperation(value = "Delete account on the basis of Psychological ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource")
    })
    @DeleteMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = psychologicalAdapter.convertToRemove(id);
        return removed ? ResponseEntity.ok(Constants.DADOS_DELETADOS) : ResponseEntity.notFound().build();
    }
}