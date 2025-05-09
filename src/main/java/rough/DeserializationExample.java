package rough;

/*
    Deserialization = Converting response body to Java object

    example:
    {
        "instructor": "Mahendra",
        "url":"mahendraqa@academy.com",
        "services":"Quality Assurance",
        "expertise":"Automation"
        "courses": {
               "webAutomation" :[
                  {
                      "courseTitle": "Selenium",
                      "price": "50"
                  },
                  {
                      "courseTitle": "Cypress",
                      "price": "10"
                  }
               ],
               "api" :[
                  {
                      "courseTitle": "Rest Assured",
                      "price": "45"
                  },
                  {
                      "courseTitle": "SOAP UI",
                      "price": "20"
                  }
               ],
               "mobile": {
                      "courseTitle": "Appium",
                      "price": "30"
               }
         },
         "linkedin":"https://linkedin_profile.com/mh27"
    }
 */

import java.util.List;

import static io.restassured.RestAssured.given;

// Step 1:
class GetCourse {
    private String instructor;
    private String url;
    private String services;
    private String expertise;
    private Courses courses;
    private String linkedin;

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
}

// Step 2:
class Courses {
    // use List if the key have > 1 data.
    private List<WebAutomation> webAutomation;
    private List<Api> api;
    private Mobile mobile;

    public List<WebAutomation> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<WebAutomation> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List<Api> getApi() {
        return api;
    }

    public void setApi(List<Api> api) {
        this.api = api;
    }

    public Mobile getMobile() {
        return mobile;
    }

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }
}

// Step 3:
class WebAutomation {
    private String courseTitle;
    private String price;

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

class Api {
    private String courseTitle;
    private String price;

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

class Mobile {
    private String courseTitle;
    private String price;

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

public class DeserializationExample {

}
