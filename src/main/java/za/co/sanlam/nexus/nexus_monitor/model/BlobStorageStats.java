package za.co.sanlam.nexus.nexus_monitor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlobStorageStats {

    private String blobStoreName;

    private String totalRepoNameMissingCount;

    private String totalBlobStoreBytes;

    private String totalReclaimableBytes;

    private String availableSpace;

    private ArrayList<RepositoryStats> repositoryStatsList;

}
