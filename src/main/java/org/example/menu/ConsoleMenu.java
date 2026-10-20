package org.example.menu;

import org.example.model.Animal;
import org.example.model.AnimalId;
import org.example.shelter.Shelter;
import org.example.model.Dog;
import org.example.model.Cat;
import org.example.model.Bird;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final Shelter<Animal> shelter;
    private final Scanner scanner =  new Scanner(System.in);
    public ConsoleMenu(Shelter<Animal> shelter) {
        this.shelter = shelter;
    }

    public void start(){
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> addAnimal();
                case 2 -> listAnimals(shelter.getAllAnimals());
                case 3 -> findAnimalsBySpecies();
                case 4 -> listAnimals(shelter.findAvailableAnimals());
                case 5 -> markAnimalAsAdopted();
                case 0 -> {
                    running = false;
                    System.out.println("Thank you for visiting the Animal Shelter!");
                }
                default -> System.out.println("Invalid option. Please try again.");
            }

            System.out.println();
        }
    }

    private void addAnimal() {
        System.out.println("Choose animal type:");
        System.out.println("1. Dog");
        System.out.println("2. Cat");
        System.out.println("3. Bird");

        int type = readInt("Enter animal type: ");

        String name;

        while (true) {
            System.out.print("Enter animal name: ");
            name = scanner.nextLine().trim();

            if (!name.isEmpty()) {
                break;
            }

            System.out.println("Name cannot be empty. Please try again.");
        }

        int age;

        while (true) {
            age = readInt("Enter animal age: ");

            if (age >= 0) {
                break;
            }

            System.out.println("Age cannot be negative. Please try again.");
        }

        Animal animal;

        switch (type) {
            case 1 -> animal = new Dog(new AnimalId(), name, age);
            case 2 -> animal = new Cat(new AnimalId(), name, age);
            case 3 -> animal = new Bird(new AnimalId(), name, age);
            default -> {
                System.out.println("Invalid animal type. Animal was not added.");
                return;
            }
        }

        shelter.addAnimal(animal);
        System.out.println("Animal added successfully!");
        System.out.println(animal);
    }

    private void findAnimalsBySpecies() {
        System.out.print("Enter species (Dog, Cat, Bird): ");
        String species = scanner.nextLine().trim();

        if (species.isEmpty()) {
            System.out.println("Species cannot be empty.");
            return;
        }

        List<Animal> animals = shelter.findBySpecies(species);
        listAnimals(animals);
    }

    private void listAnimals(List<? extends Animal> animals) {
        if (animals.isEmpty()) {
            System.out.println("No animals found.");
            return;
        }

        System.out.println("Animals:");
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    private void markAnimalAsAdopted() {
        System.out.print("Enter animal ID: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("Animal ID cannot be empty.");
            return;
        }

        shelter.markAsAdopted(id);
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private void printMenu(){
        System.out.println("""
                1. Add animal
                2. List all animals
                3. Find animals by species
                4. List available animals
                5. Mark animal as adopted
                0. Exit
                """);
    }
}
