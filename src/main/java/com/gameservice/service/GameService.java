package com.gameservice.service;

import com.gameservice.entity.Game;
import com.gameservice.exception.NotFoundException;
import com.gameservice.repository.GameStoreRepository;
import com.gameservice.request.CreateGameRequest;
import com.gameservice.request.UpdateGameRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameStoreRepository gameStoreRepository;
    private final MongoTemplate mongoTemplate;

    public Game addGame(CreateGameRequest createGameRequest) {
        Game game = Game.builder()
                .name(createGameRequest.getName())
                .author(createGameRequest.getAuthor())
                .publishedDate(LocalDateTime.now())
                .url(createGameRequest.getUrl())
                .build();
        return gameStoreRepository.save(game);
    }

    public Game getGame(String gameId) {
        Optional<Game> gameOptional = gameStoreRepository.findById(gameId);
        return gameOptional.orElseThrow(() -> {
            throw new NotFoundException("Game with " + gameId + "not found!");
        });
    }

    public List<Game> getAllGame() {
        return gameStoreRepository.findAll();
    }

    public String deleteGame(String gameId) {
        Optional<Game> gameOptional = gameStoreRepository.findOne(Example.of(Game.builder().id(gameId).build()));
        gameOptional.ifPresentOrElse(gameStoreRepository::delete, () -> {
            throw new NotFoundException("Game with " + gameId + "not found!");
        });
        return "Game with " + gameId + " deleted successfully!";
    }

    public String updateGame(String gameId, UpdateGameRequest updateGameRequest) {
        Query query = new Query(Criteria.where("id").is(gameId));
        Update update = new Update();
        if(updateGameRequest.getName() != null) {
            update.set("name", updateGameRequest.getName());
        }
        if(updateGameRequest.getAuthor() != null) {
            update.set("author", updateGameRequest.getAuthor());
        }
        if(updateGameRequest.getUrl() != null) {
            update.set("url", updateGameRequest.getUrl());
        }
        if(updateGameRequest.getPublishedDate() != null) {
            update.set("publishedDate", updateGameRequest.getPublishedDate());
        }
        if(mongoTemplate.findAndModify(query, update, Game.class) != null) {
            return "Game modified successfully!!!";
        } else {
            throw new NotFoundException("Game with " + gameId + "not found!");
        }
    }

}
