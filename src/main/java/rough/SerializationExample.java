package rough;

/*
    Serialization is a process of converting a Java object into request body (Payload)

    additional libraries required:
    for JSON need Jackson databind, Jackson2, Gson or Johnzon in the classpath and for XML need JAXB

    example:
    {
        "location" : {
                "lat" : -38.383494,
                "lng" : 33.427362
        },
        "accuracy" : 50,
        "name" : "Frontline house",
        "phone_number" : "(+62)8112233445",
        "address" : "29, Side layout, cohen 09",
        "types" : ["shoe park", "shop"],
        "website" : "https://google.com",
        "language" : "Deutsch-Indonesian"
    }

 */

import java.util.ArrayList;
import java.util.List;

// Step 1
class AddPlace {
    private Location location;
    private int accuracy;
    private String name;
    private String phone_number;
    private String address;
    private List<String> types;
    private String website;
    private String language;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

// Step 2
class Location {
    private double lat;
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}

public class SerializationExample {
    public static void main(String[] args) {
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setName("Frontline house");
        addPlace.setPhone_number("(+62)8112233445");
        addPlace.setAddress("29, Side layout, cohen 09");
        addPlace.setWebsite("https://google.com/");
        addPlace.setLanguage("Deutsch-Indonesian");

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        addPlace.setLocation(location);

        List<String> addTypes = new ArrayList<>();
        addTypes.add("shoe park");
        addTypes.add("shop");

        addPlace.setTypes(addTypes);
    }
}
