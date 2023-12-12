package com.minh.labweek07.backend.models;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Email implements Serializable {
    private String to;
    private String subject;
    private String body;
}
