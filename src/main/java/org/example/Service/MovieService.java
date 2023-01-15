package org.example.Service;

import org.example.Entity.Movie;

public interface MovieService extends GenericService<Movie> {

    Movie read();
}
