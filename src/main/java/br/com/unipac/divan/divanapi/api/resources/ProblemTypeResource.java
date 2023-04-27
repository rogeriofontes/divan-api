package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.patient.ProblemTypeRequest;
import br.com.unipac.divan.divanapi.api.dto.response.patient.ProblemTypeResponse;
import br.com.unipac.divan.divanapi.api.mapper.ProblemTypeMapper;
import br.com.unipac.divan.divanapi.model.entities.patient.ProblemType;
import br.com.unipac.divan.divanapi.model.service.ProblemTypeService;
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
@RequestMapping("/v1/problem-types")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "ProblemTypes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProblemTypeResource {
    private final ProblemTypeService problemTypeService;
    private final ProblemTypeMapper problemTypeMapper;

    /**
     * Gets all.
     *
     * @return the all
     */

    @ApiOperation(value = "View a list of available ProblemType details", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource of ProblemType"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProblemTypeResponse>> list() {
        List<ProblemType> problemTypes = problemTypeService.listAll();
        List<ProblemTypeResponse> problemTypeResponses = problemTypeMapper.map(problemTypes);
        return problemTypeResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(problemTypeResponses);
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @ApiOperation(value = "Get ProblemType details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other ProblemType ID"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @GetMapping(path = "/{id}")
    @ResponseBody
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<ProblemTypeResponse> getById(@PathVariable("id") Long id) {
        Optional<ProblemType> problemTypes = problemTypeService.findById(id);
        if (problemTypes.isPresent()) {
            ProblemTypeResponse problemTypeResponse = problemTypeMapper.to(problemTypes.get());
            return ResponseEntity.ok(problemTypeResponse);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Add response entity.
     *
     * @param problemTypeRequest the ProblemType request
     * @return the response entity
     */

    @ApiOperation(value = "Create new ProblemType", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! ProblemType you are looking for does not exist. Try with other ProblemType ID")
    })
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<ProblemTypeResponse> add(@Valid @RequestBody ProblemTypeRequest problemTypeRequest) throws Exception {
        ProblemType problemType = problemTypeMapper.from(problemTypeRequest);

        ProblemType problemTypeSaved = problemTypeService.save(problemType);

        ProblemTypeResponse problemTypeResponse = problemTypeMapper.to(problemTypeSaved);
        URI location = RestUtils.getUri(problemTypeResponse.getId());
        return ResponseEntity.created(location).body(problemTypeResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param problemTypeRequest the ProblemType request
     * @return the response entity
     */

    @ApiOperation(value = "Update existing ProblemType details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other ProblemType ID")
    })
    @PutMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<ProblemTypeResponse> change(@PathVariable("id") Long id, @RequestBody ProblemTypeRequest problemTypeRequest) {
        ProblemType problemType = problemTypeMapper.from(problemTypeRequest);

        ProblemType problemTypeSaved = problemTypeService.edit(id, problemType);

        ProblemTypeResponse problemTypeResponse = problemTypeMapper.to(problemTypeSaved);
        return problemTypeResponse != null ?
                ResponseEntity.ok(problemTypeResponse) :
                ResponseEntity.notFound().build();
    }

    /**
     * Remove response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @ApiOperation(value = "Delete account on the basis of ProblemType ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource")
    })
    @DeleteMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = problemTypeService.remove(id);
        return removed ? ResponseEntity.ok("Dados deletados!") : ResponseEntity.notFound().build();
    }
}