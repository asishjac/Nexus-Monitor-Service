package za.co.sanlam.nexus.nexus_monitor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "totalBytes",
    "reclaimableBytes"
})

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepoStatistics {

    @JsonProperty("totalBytes")
    private String totalBytes;

    @JsonProperty("reclaimableBytes")
    private String reclaimableBytes;

}
