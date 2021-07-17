package com.example.fileuploadtest.dao;

import com.example.fileuploadtest.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo,Integer> {
}
