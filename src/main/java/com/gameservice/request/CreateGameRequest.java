package com.gameservice.request;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
public class CreateGameRequest {
    private String url;
    private String name;
    private String author;
    private LocalDateTime publishedDate;
}
