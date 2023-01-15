package org.example.Service.impl;

import org.example.Entity.Genre;
import org.example.Entity.Movie;
import org.example.Repository.GenreRepository;
import org.example.Repository.MovieRepository;
import org.example.Service.MovieService;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MovieServiceConsoleImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    public MovieServiceConsoleImpl(
            MovieRepository movieRepository,
            GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Movie read() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Numele filmului: ");
        String name = keyboard.nextLine();

        Date releaseDate = null;
        while (releaseDate == null) {
            System.out.println("Data lansării: ");
            String userInput = keyboard.nextLine();
            try {
                releaseDate = Date.valueOf(userInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Ati introdus o data incorectă. Respectați formatul yyyy-MM-dd.");
            }
        }

        List<Genre> genreList = genreRepository.findAll();
        Genre genre = null;
        while (genre == null) {
            System.out.println("Introduceti unul dintre genurile: " +
                    genreList
                            .stream()
                            .map(g -> g.getName())
                            .collect(Collectors.toList()));
            String userInput = keyboard.nextLine().trim(); // șterge spațiile goale înainte sau după text 'horror ' -> 'horror'
            try {
                genre = genreList
                        .stream()
                        .filter(g -> g.getName().equalsIgnoreCase(userInput)) // Horror = horror
                        .findFirst()
                        .get();
            } catch (NoSuchElementException e) {
                System.out.println("Ați introdus un gen cinematic care nu se află în lista noastră. Respectați losta oferită.");
            }
        }
        return new Movie(name, releaseDate, genre);
    }

    @Override
    public Movie add() {
        Movie newMovie = read();
        return movieRepository.create(newMovie);
    }

    @Override
    public void displayAll() {
        System.out.println(movieRepository.findAll());
    }
}
