package Homework_4;

//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;
//import java.util.ArrayList;
//import java.util.List;

//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder( {
//        "ingredients"
//})
//public class Value {
//
//    @JsonProperty("ingredients")
//        private List<Ingredient> ingredients;
//
//        public void setIngredients(List<Ingredient> ingredients) {
//            this.ingredients = ingredients;
//        }
//    public Value(List<Ingredient> ingredients) {
//        this.ingredients = ingredients;
//
//    }
//}
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ingredients"
})

public class Value {

    @JsonProperty("ingredients")
    private List<Ingredient> ingredients = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public Value(List<Ingredient> ingredients) {
        super();
        this.ingredients = ingredients;
    }

    @JsonProperty("ingredients")
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    @JsonProperty("ingredients")
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}