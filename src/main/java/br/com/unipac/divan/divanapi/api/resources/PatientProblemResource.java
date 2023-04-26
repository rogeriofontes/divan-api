package br.com.unipac.divan.divanapi.api.resources;


import br.com.unipac.divan.divanapi.api.dto.request.patient.PatientProblemRequest;
import br.com.unipac.divan.divanapi.api.dto.response.patient.PatientProblemResponse;
import br.com.unipac.divan.divanapi.model.domain.AuditModel;
import br.com.unipac.divan.divanapi.model.entities.patient.Patient;
import br.com.unipac.divan.divanapi.model.entities.patient.ProblemType;
import br.com.unipac.divan.divanapi.util.RestUtils;
import io.swagger.annotations.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
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
@Api(value = "PatientProblems")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientProblemResources {
    private final PatientProblemAdapter blockAdapter;

    /**
     * Gets all.
     *
     * @return the all
     */

    @ApiOperation(value = "View a list of available PatientProblem details", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource of PatientProblem"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PatientProblemResponse>> list() {
        List<PatientProblemResponse> blockResponses = blockAdapter.list();
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

    @ApiOperation(value = "Get PatientProblem details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other PatientProblem ID"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @GetMapping(path = "/{id}")
    @ResponseBody
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PatientProblemResponse> getById(@PathVariable("id") Long id) {
        PatientProblemResponse blockResponse = blockAdapter.convertToFindById(id);
        return ResponseEntity.ok(blockResponse);
    }

    /**
     * Add response entity.
     *
     * @param blockRequest the PatientProblem request
     * @return the response entity
     */

    @ApiOperation(value = "Create new PatientProblem", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! PatientProblem you are looking for does not exist. Try with other PatientProblem ID")
    })
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PatientProblemResponse> add(@Valid @RequestBody PatientProblemRequest blockRequest) throws Exception {
        PatientProblemResponse blockResponse = blockAdapter.convertToCreate(blockRequest);

        URI location = RestUtils.getUri(blockResponse.getId());
        return ResponseEntity.created(location).body(blockResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param blockRequest the PatientProblem request
     * @return the response entity
     */

    @ApiOperation(value = "Update existing PatientProblem details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other PatientProblem ID")
    })
    @PutMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PatientProblemResponse> change(@PathVariable("id") Long id, @RequestBody PatientProblemRequest blockRequest) {
        PatientProblemResponse blockResponse = blockAdapter.convertToChange(id, blockRequest);
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

    @ApiOperation(value = "Delete account on the basis of PatientProblem ID", response = ResponseEntity.class)
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