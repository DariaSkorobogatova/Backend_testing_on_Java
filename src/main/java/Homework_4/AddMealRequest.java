package Homework_4;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder( {
        "date",
        "slot",
        "position",
        "type",
        "value"
        })

public class AddMealRequest {
    @JsonProperty("date")
    private Integer date;
    @JsonProperty("slot")
    private Integer slot;
    @JsonProperty("position")
    private Integer position;
    @JsonProperty("type")
    private String type;
    @JsonProperty("value")
    private Value value;

    public void setDate(Integer date) {
        this.date = date;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
