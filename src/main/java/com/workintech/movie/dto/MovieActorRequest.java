package com.workintech.movie.dto;

import com.workintech.movie.entity.Actor;
import com.workintech.movie.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieActorRequest {
    private Movie movie;
    private Actor actor;
}
