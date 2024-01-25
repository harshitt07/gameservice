package com.gameservice.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@Document(collection = "gamestore")
public class Game {
    @MongoId
    private String id;
    private String url;
    private String name;
    private String author;
    private LocalDateTime publishedDate;
}