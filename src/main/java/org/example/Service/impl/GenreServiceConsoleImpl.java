package org.example.Service.impl;

import org.example.Entity.Genre;
import org.example.Repository.GenreRepository;
import org.example.Service.GenreService;

import java.util.Scanner;

public class GenreServiceConsoleImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceConsoleImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre read() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Dati numele genului cinematic: ");
        String name = keyboard.nextLine();

        System.out.println("Dati o descriere pentru genul " + name + ": ");
        String description = keyboard.nextLine();

        return new Genre(name, description);
    }

    @Override
    public Genre add() {
        // citire
        Genre newGenre = read();
        // stocare
        return genreRepository.create(newGenre);
    }

    @Override
    public void displayAll() {
        System.out.println(genreRepository.findAll());
    }
}
