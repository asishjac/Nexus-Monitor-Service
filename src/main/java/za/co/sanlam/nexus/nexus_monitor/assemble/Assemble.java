package za.co.sanlam.nexus.nexus_monitor.assemble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.co.sanlam.nexus.nexus_monitor.dto.BlobStatistics;
import za.co.sanlam.nexus.nexus_monitor.dto.RepoStatistics;
import za.co.sanlam.nexus.nexus_monitor.model.BlobStorageStats;
import za.co.sanlam.nexus.nexus_monitor.model.NexusStorageStats;
import za.co.sanlam.nexus.nexus_monitor.model.RepositoryStats;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
@Component
public class Assemble {

    @Autowired
    private NexusStorageStats nexusStorageStats;

    @Autowired
    private BlobStorageStats blobStorageStats;

    @Autowired
    private RepositoryStats repositoryStats;

    public NexusStorageStats assembleNexusStorageStats(HashMap<String, BlobStatistics> blobStatisticsHashMap){

        nexusStorageStats.setDateTime(LocalDateTime.now(ZoneOffset.UTC));
        ArrayList <BlobStorageStats> blobStorageStatsList = new ArrayList();

        for (HashMap.Entry<String,BlobStatistics> blobStoreEntry : blobStatisticsHashMap.entrySet() ){
            blobStorageStats= new BlobStorageStats();
            ArrayList <RepositoryStats>repositoryStorageList = new ArrayList () ;
            blobStorageStats.setBlobStoreName(blobStoreEntry.getKey());
            blobStorageStats.setAvailableSpace(blobStoreEntry.getValue().getAvailableSpace());
            blobStorageStats.setTotalBlobStoreBytes(blobStoreEntry.getValue().getTotalBlobStoreBytes());
            blobStorageStats.setTotalRepoNameMissingCount(blobStoreEntry.getValue().getTotalRepoNameMissingCount());
            blobStorageStats.setTotalReclaimableBytes(blobStoreEntry.getValue().getTotalReclaimableBytes());

            for (HashMap.Entry<String, RepoStatistics> repoStoreEntry : blobStoreEntry.getValue().getRepositories().entrySet()){
                repositoryStats = new RepositoryStats();
                repositoryStats.setRepositoryName(repoStoreEntry.getKey());
                repositoryStats.setReclaimableBytes(repoStoreEntry.getValue().getReclaimableBytes());
                repositoryStats.setReclaimableBytes(repoStoreEntry.getValue().getTotalBytes());
                if (repositoryStats != null){
                    repositoryStorageList.add(repositoryStats);
                }

            }
            blobStorageStats.setRepositoryStatsList(repositoryStorageList);
            if(blobStorageStats !=null){
                blobStorageStatsList.add(blobStorageStats);
            }
        }
        nexusStorageStats.setBlobStorageStatsList(blobStorageStatsList);
        return nexusStorageStats;
    }
}
