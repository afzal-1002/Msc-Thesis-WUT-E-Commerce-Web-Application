package com.wut.msc.thesis.project.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ErrorHandlerDetails {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String trace;
    private String message;
    private String path;
}
