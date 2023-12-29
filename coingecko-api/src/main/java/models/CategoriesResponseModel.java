package models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CategoriesResponseModel {
        private String id;
        private String category_id;
        private String name;
        private double market_cap;
        private double market_cap_change_24h;
        private String content;
        private ArrayList<String> top_3_coins;
        private double volume_24h;
        private String updated_at;
        private String error;


}
