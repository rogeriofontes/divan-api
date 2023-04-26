package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.patient.PatientTypeRequest;
import br.com.unipac.divan.divanapi.api.dto.response.patient.PatientTypeResponse;
import br.com.unipac.divan.divanapi.api.mapper.PatientTypeMapper;
import br.com.unipac.divan.divanapi.model.entities.patient.PatientType;
import br.com.unipac.divan.divanapi.model.service.PatientTypeService;
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
@RequestMapping("/v1/patientTypes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "PatientTypes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PatientTypeResource {
    private final PatientTypeService patientTypeService;
    private final PatientTypeMapper patientTypeMapper;

    /**
     * Gets all.
     *
     * @return the all
     */

    @ApiOperation(value = "View a list of available PatientType details", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource of PatientType"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PatientTypeResponse>> list() {
        List<PatientType> patientTypes = patientTypeService.listAll();
        List<PatientTypeResponse> patientTypeResponses = patientTypeMapper.map(patientTypes);
        return patientTypeResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(patientTypeResponses);
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @ApiOperation(value = "Get PatientType details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other PatientType ID"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @GetMapping(path = "/{id}")
    @ResponseBody
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PatientTypeResponse> getById(@PathVariable("id") Long id) {
        Optional<PatientType> patientTypes = patientTypeService.findById(id);
        if (patientTypes.isPresent()) {
            PatientTypeResponse patientTypeResponse = patientTypeMapper.to(patientTypes.get());
            return ResponseEntity.ok(patientTypeResponse);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Add response entity.
     *
     * @param patientTypeRequest the PatientType request
     * @return the response entity
     */

    @ApiOperation(value = "Create new PatientType", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! PatientType you are looking for does not exist. Try with other PatientType ID")
    })
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PatientTypeResponse> add(@Valid @RequestBody PatientTypeRequest patientTypeRequest) throws Exception {
        PatientType patientType = patientTypeMapper.from(patientTypeRequest);

        PatientType patientTypeSaved = patientTypeService.save(patientType);

        PatientTypeResponse patientTypeResponse = patientTypeMapper.to(patientTypeSaved);
        URI location = RestUtils.getUri(patientTypeResponse.getId());
        return ResponseEntity.created(location).body(patientTypeResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param patientTypeRequest the PatientType request
     * @return the response entity
     */

    @ApiOperation(value = "Update existing PatientType details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other PatientType ID")
    })
    @PutMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PatientTypeResponse> change(@PathVariable("id") Long id, @RequestBody PatientTypeRequest patientTypeRequest) {
        PatientType patientType = patientTypeMapper.from(patientTypeRequest);

        PatientType patientTypeSaved = patientTypeService.edit(id, patientType);

        PatientTypeResponse patientTypeResponse = patientTypeMapper.to(patientTypeSaved);
        return patientTypeResponse != null ?
                ResponseEntity.ok(patientTypeResponse) :
                ResponseEntity.notFound().build();
    }

    /**
     * Remove response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @ApiOperation(value = "Delete account on the basis of PatientType ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource")
    })
    @DeleteMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = patientTypeService.remove(id);
        return removed ? ResponseEntity.ok("Dados deletados!") : ResponseEntity.notFound().build();
    }
}