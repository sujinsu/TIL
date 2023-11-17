package com.example.demo.util;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUploadUtil {
    private final String baseUploadPath;
    private final Long maxFileSize;

    public FileUploadUtil(String uploadPath, Long maxFileSize) {
        this.baseUploadPath = uploadPath;
        this.maxFileSize = maxFileSize;
    }

    // 허용된 파일 확장자 목록을 상수로 정의
    private static final Set<String> PERMITTED_EXTENSIONS = new HashSet<>(
            Arrays.asList("jpg", "jpeg", "tif", "tiff", "gif", "png", "bmp", "doc",
                    "docx", "pdf", "xls", "txt", "xlsx", "hwp", "ppt", "csv",
                    "pptx", "hwpx"));

    public List<FileInfo> findFilesInfo(String path) throws IOException {
        // 디렉토리 경로
        Path directoryPath = Paths.get(baseUploadPath, path);

        ArrayList<FileInfo> fileInfos = new ArrayList<>(List.of());

        if (!Files.exists(directoryPath)) return fileInfos;

        // 디렉토리 내의 모든 파일 목록 가져오기
        try (Stream<Path> stream = Files.list(directoryPath)) { // stream 객체 자동 닫기
            List<FileInfo> onlyFiles = stream.filter(Files::isRegularFile)   // 파일만 필터링
                    .map(p -> {
                        if (Files.exists(p)) {
                            try {
                                BasicFileAttributes attrs = Files.readAttributes(p, BasicFileAttributes.class);
                                return new FileInfo(attrs, p);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        return null;
                    }).collect(Collectors.toList());
            fileInfos.addAll(onlyFiles);
        }

        return fileInfos;
    }

    public Boolean isExceededFileSizeLimit(List<MultipartFile> files) {
        long totalSize = files.stream()
                .mapToLong(MultipartFile::getSize)
                .reduce(0L, Long::sum);

        return totalSize > this.maxFileSize;
    }

    public Boolean isPermittedFileNameExtension(List<MultipartFile> files){

        return files.stream().anyMatch(f ->{
            String fileExtension = FilenameUtils.getExtension(f.getOriginalFilename()).toLowerCase();
            return PERMITTED_EXTENSIONS.contains(fileExtension);
        });

    }

    public void uploadFiles(String path, List<MultipartFile> files) throws IOException {
        if (files.isEmpty()) return;
        // 파일 사이즈 확인
        if(this.isExceededFileSizeLimit(files)) throw new IllegalArgumentException("파일 제한 용량(10m) 초과 입니다");
        // 파일 확장자 확인
        if(!this.isPermittedFileNameExtension(files)) throw new IllegalArgumentException("허용된 파일 확장자가 아닙니다.");

        Path targetDirectory = Paths.get(baseUploadPath, path);
        // 폴더 존재 여부 확인
        if (!Files.exists(targetDirectory)) {
            Files.createDirectories(targetDirectory);
        }

//        for (MultipartFile multipartFile : files) {
//            String fileName = multipartFile.getOriginalFilename();
//            String baseName = FilenameUtils.getBaseName(fileName);
//            String extension = FilenameUtils.getExtension(fileName);
//
//            Path fullPath = targetDirectory.resolve(fileName);
//
//            if (Files.exists(fullPath)) {
//                int num = 1;
//                while (Files.exists(fullPath.resolveSibling(String.format("%s(%d)%s%s", baseName, num, FilenameUtils.EXTENSION_SEPARATOR, extension)))) {
//                    num++;
//                }
//                fullPath = fullPath.resolveSibling(String.format("%s(%d)%s%s", baseName, num, FilenameUtils.EXTENSION_SEPARATOR, extension));
//            }
//            multipartFile.transferTo(fullPath);
//        }
    }

    @Getter
    @Setter
    public static class FileInfo{
        private BasicFileAttributes fileAttrs;
        private Path filePathObj;

        public FileInfo(BasicFileAttributes fileAttrs, Path filePathObj) {
            this.fileAttrs = fileAttrs;
            this.filePathObj = filePathObj;
        }
    }
}
