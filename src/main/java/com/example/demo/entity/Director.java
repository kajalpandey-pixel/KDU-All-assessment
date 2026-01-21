package com.example.demo.entity;





import java.util.Arrays;
import java.util.List;

public record Director(String id, String firstName, String lastName) {

    private static List<Director> directors = Arrays.asList(
            new Director("Director-1", "Joanne", "Rowling"),
            new Director("Director-2", "Herman", "Melville"),
            new Director("Director-3", "Anne", "Rice")
    );

    public static Director getById(String id) {
        return directors.stream().filter(Director -> Director.id().equals(id)).findFirst().orElse(null);
    }

}