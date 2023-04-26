package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.patient.PatientRequest;
import br.com.unipac.divan.divanapi.api.dto.response.patient.PatientResponse;
import br.com.unipac.divan.divanapi.api.mapper.PatientMapper;
import br.com.unipac.divan.divanapi.model.entities.patient.Patient;
import br.com.unipac.divan.divanapi.model.service.PatientService;
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
 * The type Patient resources.
 *
 * @author Rogério Fontes
 */

@Slf4j
@RestController
@RequestMapping("/v1/patients")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Patients")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientResource {
    private final PatientService patientService;
    private final PatientMapper patientMapper;

    /**
     * Gets all.
     *
     * @return the all
     */

    @ApiOperation(value = "View a list of available Patient details", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource of Patient"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PatientResponse>> list() {
        List<Patient> patients = patientService.listAll();
        List<PatientResponse> patientResponses = patientMapper.map(patients);
        return patientResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(patientResponses);
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @ApiOperation(value = "Get Patient details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other Patient ID"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @GetMapping(path = "/{id}")
    @ResponseBody
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PatientResponse> getById(@PathVariable("id") Long id) {
        Optional<Patient> patients = patientService.findById(id);
        if (patients.isPresent()) {
            PatientResponse patientResponse = patientMapper.to(patients.get());
            return ResponseEntity.ok(patientResponse);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Add response entity.
     *
     * @param patientRequest the Patient request
     * @return the response entity
     */

    @ApiOperation(value = "Create new Patient", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Patient you are looking for does not exist. Try with other Patient ID")
    })
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PatientResponse> add(@Valid @RequestBody PatientRequest patientRequest) throws Exception {
        Patient patient = patientMapper.from(patientRequest);

        Patient patientSaved = patientService.save(patient);

        PatientResponse patientResponse = patientMapper.to(patientSaved);
        URI location = RestUtils.getUri(patientResponse.getId());
        return ResponseEntity.created(location).body(patientResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param patientRequest the Patient request
     * @return the response entity
     */

    @ApiOperation(value = "Update existing Patient details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other Patient ID")
    })
    @PutMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PatientResponse> change(@PathVariable("id") Long id, @RequestBody PatientRequest patientRequest) {
        Patient patient = patientMapper.from(patientRequest);

        Patient patientSaved = patientService.edit(id, patient);

        PatientResponse patientResponse = patientMapper.to(patientSaved);
        return patientResponse != null ?
                ResponseEntity.ok(patientResponse) :
                ResponseEntity.notFound().build();
    }

    /**
     * Remove response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @ApiOperation(value = "Delete account on the basis of Patient ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource")
    })
    @DeleteMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = patientService.remove(id);
        return removed ? ResponseEntity.ok("Dados deletados!") : ResponseEntity.notFound().build();
    }
}