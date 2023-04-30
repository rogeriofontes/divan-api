package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.psychological.PsychologicalSpecialityRequest;
import br.com.unipac.divan.divanapi.api.dto.response.psychological.PsychologicalSpecialityResponse;
import br.com.unipac.divan.divanapi.api.mapper.PsychologicalSpecialityMapper;
import br.com.unipac.divan.divanapi.model.entities.psychological.PsychologicalSpeciality;
import br.com.unipac.divan.divanapi.model.service.PsychologicalSpecialityService;
import br.com.unipac.divan.divanapi.util.RestUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/v1/psychological-specialities")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "PsychologicalSpecialityResource", description = "PsychologicalSpecialityResource management APIs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PsychologicalSpecialityResource {
    private final PsychologicalSpecialityService psychologicalSpecialityService;
    private final PsychologicalSpecialityMapper psychologicalSpecialityMapper;

    /**
     * Gets all.
     *
     * @return the all
     */

    @Operation(summary = "Retrieve all PsychologicalSpeciality", tags = { "psychologicalSpecialitys", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PsychologicalSpecialityResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PsychologicalSpecialityResponse>> list() {
        List<PsychologicalSpeciality> psychologicalSpecialities = psychologicalSpecialityService.listAll();
        List<PsychologicalSpecialityResponse> psychologicalResponses = psychologicalSpecialityMapper.map(psychologicalSpecialities);
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

    @Operation(
            summary = "Retrieve a Association by Id",
            description = "Get a PsychologicalSpeciality object by specifying its id. The response is Association object with id, title, description and published status.",
            tags = { "psychologicalSpecialitys", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = PsychologicalSpecialityResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<PsychologicalSpecialityResponse> getById(@PathVariable("id") Long id) {
        Optional<PsychologicalSpeciality> problemTypes = psychologicalSpecialityService.findById(id);
        if (problemTypes.isPresent()) {
            PsychologicalSpecialityResponse problemTypeResponse = psychologicalSpecialityMapper.to(problemTypes.get());
            return ResponseEntity.ok(problemTypeResponse);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Add response entity.
     *
     * @param problemTypeRequest the PsychologicalSpeciality request
     * @return the response entity
     */

    @Operation(summary = "Create a new PsychologicalSpeciality", tags = { "psychologicalSpecialitys", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = PsychologicalSpecialityResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<PsychologicalSpecialityResponse> add(@Valid @RequestBody PsychologicalSpecialityRequest problemTypeRequest) throws Exception {
        PsychologicalSpeciality problemType = psychologicalSpecialityMapper.from(problemTypeRequest);

        PsychologicalSpeciality problemTypeSaved = psychologicalSpecialityService.save(problemType);

        PsychologicalSpecialityResponse problemTypeResponse = psychologicalSpecialityMapper.to(problemTypeSaved);
        URI location = RestUtils.getUri(problemTypeResponse.getId());
        return ResponseEntity.created(location).body(problemTypeResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param problemTypeRequest the PsychologicalSpeciality request
     * @return the response entity
     */

    @Operation(summary = "Update a PsychologicalSpeciality by Id", tags = { "psychologicalSpecialitys", "put" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = PsychologicalSpecialityResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping(path = "/{id}")
    public ResponseEntity<PsychologicalSpecialityResponse> change(@PathVariable("id") Long id, @RequestBody PsychologicalSpecialityRequest problemTypeRequest) {
        PsychologicalSpeciality problemType = psychologicalSpecialityMapper.from(problemTypeRequest);

        PsychologicalSpeciality problemTypeSaved = psychologicalSpecialityService.edit(id, problemType);

        PsychologicalSpecialityResponse problemTypeResponse = psychologicalSpecialityMapper.to(problemTypeSaved);
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

    @Operation(summary = "Delete a PsychologicalSpeciality by Id", tags = { "psychologicalSpecialitys", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = psychologicalSpecialityService.remove(id);
        return removed ? ResponseEntity.ok("Dados deletados!") : ResponseEntity.notFound().build();
    }
}