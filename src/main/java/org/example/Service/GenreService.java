package org.example.Service;

import org.example.Entity.Genre;

public interface GenreService extends GenericService<Genre>  {

    Genre read();
}
