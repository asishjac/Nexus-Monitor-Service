package za.co.sanlam.nexus.nexus_monitor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.ArrayList;



@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "nexusStorageStats")
public class NexusStorageStats {

    @Id
    private String id;

    private LocalDateTime dateTime;


    private ArrayList<BlobStorageStats> blobStorageStatsList;


}
