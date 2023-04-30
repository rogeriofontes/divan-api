package br.com.unipac.divan.divanapi.api.resources;

import br.com.unipac.divan.divanapi.api.dto.request.schedule.ScheduleSessionRequest;
import br.com.unipac.divan.divanapi.api.dto.response.schedule.ScheduleSessionResponse;
import br.com.unipac.divan.divanapi.api.mapper.ScheduleSessionMapper;
import br.com.unipac.divan.divanapi.model.entities.schedule.ScheduleSession;
import br.com.unipac.divan.divanapi.model.service.ScheduleSessionService;
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
@RequestMapping("/v1/schedule-sessions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "ScheduleSessionResource", description = "AssociationResource management APIs")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ScheduleSessionResource {
    private final ScheduleSessionService problemTypeService;
    private final ScheduleSessionMapper problemTypeMapper;

    /**
     * Gets all.
     *
     * @return the all
     */

    @Operation(summary = "Retrieve all ScheduleSessions", tags = { "scheduleSessions", "get", "filter" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ScheduleSessionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "204", description = "There are no Associations", content = {
                    @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ScheduleSessionResponse>> list() {
        List<ScheduleSession> problemTypes = problemTypeService.listAll();
        List<ScheduleSessionResponse> problemTypeResponses = problemTypeMapper.map(problemTypes);
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

    @Operation(
            summary = "Retrieve a ScheduleSession by Id",
            description = "Get a ScheduleSessions object by specifying its id. The response is ScheduleSession object with id, title, description and published status.",
            tags = { "scheduleSessions", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ScheduleSessionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<ScheduleSessionResponse> getById(@PathVariable("id") Long id) {
        Optional<ScheduleSession> problemTypes = problemTypeService.findById(id);
        if (problemTypes.isPresent()) {
            ScheduleSessionResponse problemTypeResponse = problemTypeMapper.to(problemTypes.get());
            return ResponseEntity.ok(problemTypeResponse);
        }

        return ResponseEntity.noContent().build();
    }

    /**
     * Add response entity.
     *
     * @param problemTypeRequest the ScheduleSession request
     * @return the response entity
     */

    @Operation(summary = "Create a new ScheduleSessions", tags = { "scheduleSessions", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = ScheduleSessionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping
    public ResponseEntity<ScheduleSessionResponse> add(@Valid @RequestBody ScheduleSessionRequest problemTypeRequest) throws Exception {
        ScheduleSession problemType = problemTypeMapper.from(problemTypeRequest);

        ScheduleSession problemTypeSaved = problemTypeService.save(problemType);

        ScheduleSessionResponse problemTypeResponse = problemTypeMapper.to(problemTypeSaved);
        URI location = RestUtils.getUri(problemTypeResponse.getId());
        return ResponseEntity.created(location).body(problemTypeResponse);
    }


    /**
     * Change response entity.
     *
     * @param id             the id
     * @param problemTypeRequest the ScheduleSession request
     * @return the response entity
     */

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ScheduleSessionResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }) })
    @PutMapping(path = "/{id}")
    public ResponseEntity<ScheduleSessionResponse> change(@PathVariable("id") Long id, @RequestBody ScheduleSessionRequest problemTypeRequest) {
        ScheduleSession problemType = problemTypeMapper.from(problemTypeRequest);

        ScheduleSession problemTypeSaved = problemTypeService.edit(id, problemType);

        ScheduleSessionResponse problemTypeResponse = problemTypeMapper.to(problemTypeSaved);
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
    @Operation(summary = "Delete a ScheduleSessions by Id", tags = { "scheduleSessions", "delete" })
    @ApiResponses({ @ApiResponse(responseCode = "204", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        boolean removed = problemTypeService.remove(id);
        return removed ? ResponseEntity.ok("Dados deletados!") : ResponseEntity.notFound().build();
    }
}