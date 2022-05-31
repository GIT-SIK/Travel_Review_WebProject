package com.travel.file;

import com.travel.domain.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UploadFileRepository extends JpaRepository<UploadFile, Long>{
  UploadFile findBySaveFileName(String saveFileName);
}

