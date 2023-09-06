package com.workintech.movie.service;

import com.workintech.movie.dao.ActorDao;
import com.workintech.movie.entity.Actor;
import com.workintech.movie.exceptions.MovieException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService{

    private ActorDao actorDao;

    @Autowired
    public ActorServiceImpl(ActorDao actorDao) {
        this.actorDao = actorDao;
    }

    @Override
    public List<Actor> findAll() {
        return actorDao.findAll();
    }

    @Override
    public Actor findById(int id) {
        Optional<Actor> actor = actorDao.findById(id);
        if(actor.isPresent()){
            return actor.get();
        }
        throw new MovieException("Actor is not exist: " + id, HttpStatus.BAD_REQUEST);
    }

    @Override
    public Actor save(Actor actor) {
        return actorDao.save(actor);
    }

    @Override
    public Actor delete(int id) {
        Actor actor = findById(id);
        actorDao.delete(actor);
        return actor;
    }
}
