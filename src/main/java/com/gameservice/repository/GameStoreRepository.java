package com.gameservice.repository;

import com.gameservice.entity.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameStoreRepository extends MongoRepository<Game, String> {

}

