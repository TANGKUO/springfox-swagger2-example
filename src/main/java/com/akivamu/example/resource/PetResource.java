package com.akivamu.example.resource;

import com.akivamu.example.exception.NotFoundException;
import com.akivamu.example.models.Pet;
import com.akivamu.example.models.Response;
import com.akivamu.example.repositories.PetRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "Pets", description = "Pets related endpoints")
@RestController
public class PetResource {
    @Resource
    private PetRepository petRepository;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Response exception(NotFoundException e) {
        return new Response(Response.ERROR, e.getMessage());
    }

    @ApiOperation(notes = "Returns a pet by ID. Using non-integers ID will simulate API error conditions",
            value = "Get pet by ID", nickname = "getPetById")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Nice!", response = Pet.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = Response.class),
            @ApiResponse(code = 404, message = "Pet not found", response = Response.class)
    })
    @RequestMapping(value = "/pets/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Pet> getPetById(@ApiParam(value = "ID of pet that needs to be fetched", allowableValues = "range[1,10]", required = true) @PathVariable("id") Long petId) throws Exception {
        Pet pet = petRepository.findOne(petId);

        if (pet != null) {
            return ResponseEntity.ok().body(pet);
        } else {
            throw new NotFoundException("Pet " + petId + " not found");
        }
    }

    @ApiOperation(notes = "Returns pets by search term",
            value = "Find pet by term", nickname = "findPets")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Nice!", response = Pet.class),
            @ApiResponse(code = 404, message = "Pet not found", response = Response.class)
    })
    @RequestMapping(value = "/pets/search", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Pet> getPetByName(@ApiParam(value = "Name of pet") @RequestParam("q") String q) throws Exception {
        return ResponseEntity.ok().body(null);
    }
}
