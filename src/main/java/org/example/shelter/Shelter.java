package org.example.shelter;

import org.example.model.Animal;
import org.example.model.AdoptionStatus.java

import java.util.ArrayList;
import java.util.List;

public class Shelter <T extends Animal>{
    private final List<T> animals = new ArrayList<>();

    public void addAnimal(T animal){
        animals.add(animal);
    }

    public List<T> getAllAnimals(){
        return new ArrayList<>(animals);
    }

    public List<T> findBySpecies(String species){
        List<T> result = new ArrayList<>();

        for (T animal : animals) {
            if (animal.getSpecies().equalsIgnoreCase(species)) {
                result.add(animal);
            }
        }

        return result;
    }

    public List<T> findAvailableAnimals(){
        List<T> result = new ArrayList<>();

        for (T animal : animals) {
            if (animal.getAdoptionStatus() == AdoptionStatus.AVAILABLE) {
                result.add(animal);
            }
        }

        return result;
    }

    public void markAsAdopted(String id){
        public void markAsAdopted(String id) {
            for (T animal : animals) {
                if (animal.getId().toString().equals(id)) {
                    if (animal.getAdoptionStatus() == AdoptionStatus.ADOPTED) {
                        System.out.println("This animal has already been adopted.");
                    } else {
                        animal.markAsAdopted();
                        System.out.println("Animal successfully marked as adopted.");
                    }
                    return;
                }
            }

            System.out.println("Animal with this ID was not found.");
        }
    }
}
