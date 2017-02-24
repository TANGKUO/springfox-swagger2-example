/**
 * Copyright 2016 SmartBear Software
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.akivamu.example.data;

import com.akivamu.example.models.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetData {
    static List<Pet> pets = new ArrayList<Pet>();

    static {
        pets.add(createPet(1, "Cat 1", new String[]{"url1", "url2"}, "available"));
        pets.add(createPet(2, "Cat 2", new String[]{"url1", "url2"}, "available"));
        pets.add(createPet(3, "Cat 3", new String[]{"url1", "url2"}, "available"));
        pets.add(createPet(4, "Cat 4", new String[]{"url1", "url2"}, "available"));
        pets.add(createPet(5, "Cat 5", new String[]{"url1", "url2"}, "available"));
        pets.add(createPet(6, "Cat 6", new String[]{"url1", "url2"}, "available"));
    }

    public Pet getPetById(long petId) {
        for (Pet pet : pets) {
            if (pet.getId() == petId) {
                return pet;
            }
        }
        return null;
    }

    public List<Pet> findPetByStatus(String status) {
        String[] statues = status.split(",");
        List<Pet> result = new ArrayList<Pet>();
        for (Pet pet : pets) {
            for (String s : statues) {
                if (s.equals(pet.getStatus())) {
                    result.add(pet);
                }
            }
        }
        return result;
    }


    public void addPet(Pet pet) {
        if (pets.size() > 0) {
            for (int i = pets.size() - 1; i >= 0; i--) {
                if (pets.get(i).getId() == pet.getId()) {
                    pets.remove(i);
                }
            }
        }
        pets.add(pet);
    }

    static Pet createPet(long id, String name, String[] urls, String status) {
        Pet pet = new Pet();
        pet.setId(id);
        pet.setName(name);
        if (null != urls) {
            List<String> urlObjs = new ArrayList<String>();
            for (String urlString : urls) {
                urlObjs.add(urlString);
            }
            pet.setPhotoUrls(urlObjs);
        }
        pet.setStatus(status);
        return pet;
    }
}