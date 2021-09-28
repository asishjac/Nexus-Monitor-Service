package za.co.sanlam.nexus.nexus_monitor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "result"
})

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NexusStorageScriptResult {

    @JsonProperty("result")
    private Map<String,BlobStatistics>result=null;
}
