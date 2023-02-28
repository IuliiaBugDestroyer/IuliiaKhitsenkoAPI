package com.epam.javacourse.api.trello.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloCard {
    private String id;

    private String name;

    private boolean closed;

    @JsonProperty("idBoard")
    private String parentBoardId;

    @JsonProperty("idList")
    private String listId;
}
