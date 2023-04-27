package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.patient.PatientProblemRequest;
import br.com.unipac.divan.divanapi.api.dto.response.patient.PatientProblemResponse;
import br.com.unipac.divan.divanapi.api.mapper.PatientProblemMapper;
import br.com.unipac.divan.divanapi.model.entities.patient.PatientProblem;
import br.com.unipac.divan.divanapi.model.service.PatientProblemService;
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

@Slf4j
@RestController
@RequestMapping("/v1/patient-problems")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "PatientProblems")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientProblemResource {
    private final PatientProblemService patientProblemService;
    private final PatientProblemMapper patientProblemMapper;

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
        List<PatientProblem> patientProblems = patientProblemService.listAll();
        List<PatientProblemResponse> patientProblemResponses = patientProblemMapper.map(patientProblems);
        return patientProblemResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(patientProblemResponses);
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
        Optional<PatientProblem> patientProblems = patientProblemService.findById(id);
        if (patientProblems.isPresent()) {
            PatientProblemResponse patientProblemResponse = patientProblemMapper.to(patientProblems.get());
            return ResponseEntity.ok(patientProblemResponse);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Add response entity.
     *
     * @param patientProblemRequest the PatientProblem request
     * @return the response entity
     */

    @ApiOperation(value = "Create new PatientProblem", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! PatientProblem you are looking for does not exist. Try with other PatientProblem ID")
    })
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PatientProblemResponse> add(@Valid @RequestBody PatientProblemRequest patientProblemRequest) throws Exception {
        PatientProblem patientProblem = patientProblemMapper.from(patientProblemRequest);

        PatientProblem patientProblemSaved = patientProblemService.save(patientProblem);

        PatientProblemResponse patientProblemResponse = patientProblemMapper.to(patientProblemSaved);
        URI location = RestUtils.getUri(patientProblemResponse.getId());
        return ResponseEntity.created(location).body(patientProblemResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param patientProblemRequest the PatientProblem request
     * @return the response entity
     */

    @ApiOperation(value = "Update existing PatientProblem details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other PatientProblem ID")
    })
    @PutMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PatientProblemResponse> change(@PathVariable("id") Long id, @RequestBody PatientProblemRequest patientProblemRequest) {
        PatientProblem patientProblem = patientProblemMapper.from(patientProblemRequest);

        PatientProblem patientProblemSaved = patientProblemService.edit(id, patientProblem);

        PatientProblemResponse patientProblemResponse = patientProblemMapper.to(patientProblemSaved);
        return patientProblemResponse != null ?
                ResponseEntity.ok(patientProblemResponse) :
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
        boolean removed = patientProblemService.remove(id);
        return removed ? ResponseEntity.ok("Dados deletados!") : ResponseEntity.notFound().build();
    }
}