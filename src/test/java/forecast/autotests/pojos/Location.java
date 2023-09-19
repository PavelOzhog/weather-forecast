package forecast.autotests.pojos;


import lombok.Data;

@Data
public class Location {

    private String name;
    private String country;
    private String region;
    private String lat;
    private String lon;
    private String timezone_id;
    private String localtime;
    private Integer localtime_epoch;
    private String utc_offset;


}
