package com.epam.javacourse.api.trello.request.specifications.query;

import java.util.HashMap;
import java.util.Map;
import lombok.Builder;

@Builder
public class TrelloBoardReadParams {
    private ListsParam lists;

    public Map<String, String> asQueryParams() {
        var result = new HashMap<String, String>();

        result.put("lists", lists.name());

        return result;
    }
}
