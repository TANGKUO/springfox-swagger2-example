package com.akivamu.example.repositories;

import com.akivamu.example.models.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {

}
