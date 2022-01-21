package bgv.fit.bstu.eday.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Task implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("iduser")
    @Expose
    private int iduser;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("time")
    @Expose
    private String time;

    public Integer getId() {
        return id;
    }
    public String getName() {return name; }
    public String getDescription() {
        return description;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {return time; }
    public Integer getUserId() {
        return iduser;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {this.description=description;}
    public void setDate(String date) {
        this.date = date;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setUserId(Integer iduserd) {
        this.iduser = iduser;
    }
}
