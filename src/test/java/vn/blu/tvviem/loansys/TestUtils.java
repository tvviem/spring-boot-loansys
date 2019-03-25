package vn.blu.tvviem.loansys;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.util.List;

public class TestUtils {
    @SuppressWarnings("rawtypes")
    public static List jsonToList(String json, TypeToken token) {
        Gson gson = new Gson();
        return gson.fromJson(json, token.getType());
    }

    public static String objectToJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    public static <T> T jsonToObject(String json, Class<T> classOf) {
        Gson gson = new Gson();
        return gson.fromJson(json, classOf);
    }
}
