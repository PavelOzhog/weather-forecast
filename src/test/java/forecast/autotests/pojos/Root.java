package forecast.autotests.pojos;


import lombok.Data;

@Data
public class Root {

    public Request request;
    public Location location;
    public Current current;


}
