package forecast.autotests.pojos;

import lombok.Data;

import java.util.ArrayList;




@Data
public class Current {

    private String observation_time;
    private Integer temperature;
    private Integer weather_code;
    private ArrayList<String> weather_icons;
    private ArrayList<String> weather_descriptions;
    private Integer wind_speed;
    private Integer wind_degree;
    private String wind_dir;
    private Integer pressure;
    private Integer precip;
    private Integer humidity;
    private Integer cloudcover;
    private Integer feelslike;
    private Integer uv_index;
    private Integer visibility;
    private String is_day;

}
