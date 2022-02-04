package com.trello.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {

    private String id;

    @JsonProperty("desc")
    private String description;
}
