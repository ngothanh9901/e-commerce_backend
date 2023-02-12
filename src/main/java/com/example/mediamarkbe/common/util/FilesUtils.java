package com.example.mediamarkbe.common.util;

import org.springframework.data.util.Pair;
import org.springframework.util.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FilesUtils {
    public static String SPECIAL_CHARS_REGEX = "[\\\\/:*?\"<>|]";

    public static String getFileNameWithoutExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }

        String[] fileNameInfo = StringUtils.delimitedListToStringArray(getName(fileName), ".");

        if (fileNameInfo.length == 1) {
            return getName(fileName);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fileNameInfo.length - 1; i++) {
            sb.append(fileNameInfo[i]);
        }
        return sb.toString();
    }

    public static String getExtension(String fileName) {

        if (fileName == null || fileName.isEmpty()) {
            return "";
        }

        String[] fileNameInfo = StringUtils.delimitedListToStringArray(getName(fileName), ".");

        if (fileNameInfo.length == 1) {
            return "";
        }
        return fileNameInfo[fileNameInfo.length - 1].toLowerCase();
    }

    public static String getName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }

        String[] filePath = getFilePath(fileName);
        if (filePath.length == 1) {
            return fileName;
        }
        return filePath[filePath.length - 1];
    }

    public static String getParent(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }

        boolean isStartWithRoot = fileName.startsWith("/");


        String[] filePath = getFilePath(fileName);
        if (filePath.length <= (isStartWithRoot ? 2 : 1)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        if (!isStartWithRoot) {
            sb.append(filePath[0]);
        }
        for (int i = 1; i < filePath.length - 1; i++) {
            sb.append("/" + filePath[i]);
        }
        return sb.toString();
    }


    public static String getNewFileName(String fullFileName) {
        String uploadPath = getParent(fullFileName);
        String extension = getExtension(fullFileName);
        String newFileName = getFileNameWithoutExtension(fullFileName);

        // 파일명은 어떻게?
        newFileName = DateUtils.getToday();        // yyyyMMddHHmmss 형식으로..

        boolean existsFile = true;
        int idx = 1;

        do {
            String filePath = uploadPath + File.separator + newFileName + "." + extension;
            File file = new File(filePath);
            if (file.exists()) {
                newFileName = newFileName + "(" + idx + ")";
                ++idx;
                existsFile = true;
            } else {
                existsFile = false;
            }
        } while (existsFile);

        return newFileName + "." + extension;
    }


    public static String[] getFilePath(String fileName) {
        fileName = StringUtils.cleanPath(fileName);
        return StringUtils.delimitedListToStringArray(fileName, "/");
    }

    public static ByteArrayOutputStream zipMultipleByteFiles(List<Pair<String, ByteArrayOutputStream>> listDataFile) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ZipOutputStream zipOut = new ZipOutputStream(bos);
        for (Pair<String, ByteArrayOutputStream> dataFile : listDataFile) {
            String fileName = dataFile.getFirst();
            ByteArrayOutputStream fileOutputStream = dataFile.getSecond();
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            zipOut.write(fileOutputStream.toByteArray(), 0, fileOutputStream.toByteArray().length);
            fileOutputStream.close();
        }
        zipOut.close();
        return bos;
    }


}
