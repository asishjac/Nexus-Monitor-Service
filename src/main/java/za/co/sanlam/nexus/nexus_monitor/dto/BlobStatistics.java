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
    "repositories",
    "totalRepoNameMissingCount",
    "totalBlobStoreBytes",
    "totalReclaimableBytes",
    "availableSpace"
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class BlobStatistics {

    @JsonProperty("totalRepoNameMissingCount")
    private String totalRepoNameMissingCount;

    @JsonProperty("totalBlobStoreBytes")
    private String totalBlobStoreBytes;

    @JsonProperty("totalReclaimableBytes")
    private String totalReclaimableBytes;

    @JsonProperty("availableSpace")
    private String availableSpace;

    @JsonProperty("repositories")
    private Map<String,RepoStatistics>repositories=null;

}
