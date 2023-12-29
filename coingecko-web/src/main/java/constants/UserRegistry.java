package constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.User;

import java.io.FileReader;
import java.util.Map;


public class UserRegistry {
    Map<String, User> resultMap;

    public UserRegistry() {
        Gson gson = new GsonBuilder().create();
        try {
            FileReader fileReader = new FileReader("src/main/resources/users.json");
            this.resultMap = gson.fromJson(fileReader, new TypeToken<Map<String, User>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUser(String key) {
        return this.resultMap.get(key);
    }
}
