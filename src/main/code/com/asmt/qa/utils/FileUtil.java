package com.asmt.qa.utils;

import static java.lang.String.format;
import static java.util.Objects.nonNull;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {

    private FileUtil() {
    }

    @SneakyThrows
    public static File getResource(String resourcePath) {
        final URL resource = Thread.currentThread().getContextClassLoader()
            .getResource(resourcePath);
        if (!nonNull(resource)) {
            throw new IllegalArgumentException(format("file %s should exist!", resourcePath));
        }
        return new File(resource.toURI());
    }

    @SneakyThrows
    public static String readFromFileNamed(final String directory, final String fileName) {
        final var file = new File(getResource(directory), fileName);
        if (!file.exists()) {
            throw new IllegalArgumentException(format("File %s not found!", file));
        }
        return Files.readString(Path.of(file.toURI()), Charset.defaultCharset());
    }
}
