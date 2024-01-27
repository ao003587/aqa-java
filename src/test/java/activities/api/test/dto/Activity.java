package activities.api.test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Activity(
        @JsonProperty Integer id,
        @JsonProperty String title,
        @JsonProperty String dueDate,
        @JsonProperty Boolean completed) {

}
