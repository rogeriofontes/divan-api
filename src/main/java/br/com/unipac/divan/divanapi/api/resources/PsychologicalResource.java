package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.psychological.PsychologicalRequest;
import br.com.unipac.divan.divanapi.api.dto.response.psychological.PsychologicalResponse;
import br.com.unipac.divan.divanapi.api.mapper.PsychologicalMapper;
import br.com.unipac.divan.divanapi.model.entities.psychological.Psychological;
import br.com.unipac.divan.divanapi.model.service.PsychologicalService;
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
@RequestMapping("/v1/psychologicals")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Psychologicals")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PsychologicalResource {
    private final PsychologicalService problemTypeService;
    private final PsychologicalMapper problemTypeMapper;

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
        List<Psychological> problemTypes = problemTypeService.listAll();
        List<PsychologicalResponse> problemTypeResponses = problemTypeMapper.map(problemTypes);
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
        Optional<Psychological> problemTypes = problemTypeService.findById(id);
        if (problemTypes.isPresent()) {
            PsychologicalResponse problemTypeResponse = problemTypeMapper.to(problemTypes.get());
            return ResponseEntity.ok(problemTypeResponse);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Add response entity.
     *
     * @param problemTypeRequest the Psychological request
     * @return the response entity
     */

    @ApiOperation(value = "Create new Psychological", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Psychological you are looking for does not exist. Try with other Psychological ID")
    })
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PsychologicalResponse> add(@Valid @RequestBody PsychologicalRequest problemTypeRequest) throws Exception {
        Psychological problemType = problemTypeMapper.from(problemTypeRequest);

        Psychological problemTypeSaved = problemTypeService.save(problemType);

        PsychologicalResponse problemTypeResponse = problemTypeMapper.to(problemTypeSaved);
        URI location = RestUtils.getUri(problemTypeResponse.getId());
        return ResponseEntity.created(location).body(problemTypeResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param problemTypeRequest the Psychological request
     * @return the response entity
     */

    @ApiOperation(value = "Update existing Psychological details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other Psychological ID")
    })
    @PutMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<PsychologicalResponse> change(@PathVariable("id") Long id, @RequestBody PsychologicalRequest problemTypeRequest) {
        Psychological problemType = problemTypeMapper.from(problemTypeRequest);

        Psychological problemTypeSaved = problemTypeService.edit(id, problemType);

        PsychologicalResponse problemTypeResponse = problemTypeMapper.to(problemTypeSaved);
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

    @ApiOperation(value = "Delete account on the basis of Psychological ID", response = ResponseEntity.class)
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