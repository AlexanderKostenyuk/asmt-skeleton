package com.asmt.qa.factory;

import static com.asmt.qa.utils.FileUtil.getResource;
import static com.asmt.qa.utils.JsonUtils.toObject;

import lombok.SneakyThrows;

public class CommonRequestResponseFactory {

    private CommonRequestResponseFactory() {
    }

    public static final String REQUESTS_FOLDER = "requests";
    public static final String RESPONSES_FOLDER = "responses";

    // JSON -> Object (requests)
    public static <T> T readRequest(Class<T> clazz, String fileName) {
        String requestJson = readJson(fileName, REQUESTS_FOLDER);
        return toObject(requestJson, clazz);
    }

    // JSON -> Object (responses)
    public static <T> T readResponse(String fileName, Class<T> clazz) {
        String responseJson = readJson(fileName, RESPONSES_FOLDER);
        return toObject(responseJson, clazz);
    }

    private static String readJson(String fileName, String rootFolder) {
        // fileName may include subfolders, e.g. "delivery_options_requests/sample"
        return com.asmt.qa.utils.FileUtil.readFromFileNamed(rootFolder, "%s.json".formatted(fileName));
    }
}
