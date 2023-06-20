package com.travel.travel.utility;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import java.util.List;

public interface StorageService {
    List<String> loadAll();
    String store(MultipartFile file);
    Resource load(String fileName);
    void delete(String fileName);
}
