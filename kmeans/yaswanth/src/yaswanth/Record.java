package yaswanth;


/**
 * Created by gc on 2015-09-17.
 */
public class Record {
    String court;
    String state;
    String city;
    String citation;
    int citationYear;
    String company;
    String data;
    int id;

    public Record(){

    }

    public Record(int id,String court, String state, String city,
                  String citation,int citationYear, String company,
                  String data){
        this.court=court;
        this.state=state;
        this.city=city;
        this.citation=citation;
        this.citationYear=citationYear;
        this.company=company;
        this.id=id;
        this.data=data;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

    public int getCitationYear() {
        return citationYear;
    }

    public void setCitationYear(int citationYear) {
        this.citationYear = citationYear;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }
}
