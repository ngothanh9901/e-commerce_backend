package com.example.mediamarkbe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(length = 50)
//    @Enumerated(EnumType.STRING)
//    private StorageType storageType;
    private Long refId;
    private String fileName;
    private String saveLocation;
    private Long fileSize;
}
