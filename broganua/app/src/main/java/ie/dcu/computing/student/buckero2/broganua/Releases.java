package ie.dcu.computing.student.buckero2.broganua;

public class Releases {
    private String igprofile;
    private String igprofile_url;
    private String iglink;
    private String igimage;
    private String igcaption;
    private String igdate;

    public Releases(){

    }

    public Releases(String igprofile, String igprofile_url, String iglink, String igimage, String igcaption, String igdate) {
        this.igprofile = igprofile;
        this.igprofile_url = igprofile_url;
        this.iglink = iglink;
        this.igimage = igimage;
        this.igcaption = igcaption;
        this.igdate = igdate;
    }

    public String getIgprofile() {
        return igprofile;
    }

    public void setIgprofile(String igprofile) {
        this.igprofile = igprofile;
    }

    public String getIgprofile_url() {
        return igprofile_url;
    }

    public void setIgprofile_url(String igprofile_url) {
        this.igprofile_url = igprofile_url;
    }

    public String getIglink() {
        return iglink;
    }

    public void setIglink(String iglink) {
        this.iglink = iglink;
    }

    public String getIgimage() {
        return igimage;
    }

    public void setIgimage(String igimage) {
        this.igimage = igimage;
    }

    public String getIgcaption() {
        return igcaption;
    }

    public void setIgcaption(String igcaption) {
        this.igcaption = igcaption;
    }

    public String getIgdate() {
        return igdate;
    }

    public void setIgdate(String igdate) {
        this.igdate = igdate;
    }
}
