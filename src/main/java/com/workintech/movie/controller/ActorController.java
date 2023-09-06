package com.workintech.movie.controller;

import com.workintech.movie.dto.ActorResponse;
import com.workintech.movie.dto.MovieActorRequest;
import com.workintech.movie.dto.MovieActorResponse;
import com.workintech.movie.entity.Actor;
import com.workintech.movie.entity.Movie;
import com.workintech.movie.service.ActorService;
import com.workintech.movie.util.HollywoodUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/actors")
public class ActorController {
    private ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/")
    public List<ActorResponse> findAll(){
        List<ActorResponse> actorResponses = new ArrayList<>();
        List<Actor> actors = actorService.findAll();
        for (Actor actor : actors){
            actorResponses.add(new ActorResponse(actor.getId(), actor.getFirstName(),
                    actor.getLastName(), actor.getBirthDate()));
        }
        return actorResponses;
    }
    @GetMapping("/{id}")
    public ActorResponse findAll(@PathVariable int id){
        Actor foundActor = actorService.findById(id);
        return new ActorResponse(foundActor.getId(), foundActor.getFirstName(),
                foundActor.getLastName(), foundActor.getBirthDate());
    }

    @PostMapping("/")
    public MovieActorResponse save(@RequestBody MovieActorRequest movieActorRequest){
        Movie movie = movieActorRequest.getMovie();
        Actor actor = movieActorRequest.getActor();
        actor.addMovie(movie);
        Actor savedActor =  actorService.save(actor);
        return HollywoodUtility.convertMovieActorResponse(movie, savedActor);
    }

    @PutMapping("/{id}")
    public ActorResponse update(@PathVariable int id, @RequestBody Actor actor){
        Actor foundActor = actorService.findById(id);
        actor.setId(id);
        actor.setMovies(foundActor.getMovies());
        Actor updatedActor = actorService.save(actor);
        return new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate());
    }

    @DeleteMapping("/{id}")
    public ActorResponse delete(@PathVariable int id) {
        Actor actor = actorService.delete(id);
        return new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate());
    }
}
