package com.gameservice.request;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Component
public class UpdateGameRequest {
    private String url;
    private String name;
    private String author;
    private LocalDateTime publishedDate;
}

