package br.com.unipac.divan.divanapi.api.resources;


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
@RequestMapping("/v1/problemTypes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Projects")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectResource {
    private final ProjectService problemTypeService;
    private final ProjectMapper problemTypeMapper;

    /**
     * Gets all.
     *
     * @return the all
     */

    @ApiOperation(value = "View a list of available Project details", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource of Project"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ProjectResponse>> list() {
        List<Project> problemTypes = problemTypeService.listAll();
        List<ProjectResponse> problemTypeResponses = problemTypeMapper.map(problemTypes);
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

    @ApiOperation(value = "Get Project details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other Project ID"),
            @ApiResponse(code = 404, message = "The resource you were looling for is not found")
    })
    @GetMapping(path = "/{id}")
    @ResponseBody
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<ProjectResponse> getById(@PathVariable("id") Long id) {
        Optional<Project> problemTypes = problemTypeService.findById(id);
        if (problemTypes.isPresent()) {
            ProjectResponse problemTypeResponse = problemTypeMapper.to(problemTypes.get());
            return ResponseEntity.ok(problemTypeResponse);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Add response entity.
     *
     * @param problemTypeRequest the Project request
     * @return the response entity
     */

    @ApiOperation(value = "Create new Project", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Project you are looking for does not exist. Try with other Project ID")
    })
    @PostMapping
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<ProjectResponse> add(@Valid @RequestBody ProjectRequest problemTypeRequest) throws Exception {
        Project problemType = problemTypeMapper.from(problemTypeRequest);

        Project problemTypeSaved = problemTypeService.save(problemType);

        ProjectResponse problemTypeResponse = problemTypeMapper.to(problemTypeSaved);
        URI location = RestUtils.getUri(problemTypeResponse.getId());
        return ResponseEntity.created(location).body(problemTypeResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param problemTypeRequest the Project request
     * @return the response entity
     */

    @ApiOperation(value = "Update existing Project details on the basis of account ID", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved Resource"),
            @ApiResponse(code = 400, message = "Oops! Account you are looking for does not exist. Try with other Project ID")
    })
    @PutMapping(path = "/{id}")
    @ApiImplicitParam(name = "Authorization", value = "Baerer token", required = true, dataType = "string", paramType = "header")
    public ResponseEntity<ProjectResponse> change(@PathVariable("id") Long id, @RequestBody ProjectRequest problemTypeRequest) {
        Project problemType = problemTypeMapper.from(problemTypeRequest);

        Project problemTypeSaved = problemTypeService.edit(id, problemType);

        ProjectResponse problemTypeResponse = problemTypeMapper.to(problemTypeSaved);
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

    @ApiOperation(value = "Delete account on the basis of Project ID", response = ResponseEntity.class)
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