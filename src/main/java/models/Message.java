package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private LocalDateTime createdAt;
    private User user;
    private String text;
}
