package ie.dcu.computing.student.buckero2.broganua.Models;

public class Releases {
    private String ig_profile;
    private String ig_profile_url;
    private String ig_link;
    private String ig_image;
    private String ig_caption;
    private String ig_date;

    public Releases() {

    }

    public Releases(String ig_profile, String ig_profile_url, String ig_link, String ig_image, String ig_caption, String ig_date) {
        this.ig_profile = ig_profile;
        this.ig_profile_url = ig_profile_url;
        this.ig_link = ig_link;
        this.ig_image = ig_image;
        this.ig_caption = ig_caption;
        this.ig_date = ig_date;
    }

    public String getIg_profile() {
        return ig_profile;
    }

    public void setIg_profile(String ig_profile) {
        this.ig_profile = ig_profile;
    }

    public String getIg_profile_url() {
        return ig_profile_url;
    }

    public void setIg_profile_url(String ig_profile_url) {
        this.ig_profile_url = ig_profile_url;
    }

    public String getIg_link() {
        return ig_link;
    }

    public void setIg_link(String ig_link) {
        this.ig_link = ig_link;
    }

    public String getIg_image() {
        return ig_image;
    }

    public void setIg_image(String ig_image) {
        this.ig_image = ig_image;
    }

    public String getIg_caption() {
        return ig_caption;
    }

    public void setIg_caption(String ig_caption) {
        this.ig_caption = ig_caption;
    }

    public String getIg_date() {
        return ig_date;
    }

    public void setIg_date(String ig_date) {
        this.ig_date = ig_date;
    }
}
