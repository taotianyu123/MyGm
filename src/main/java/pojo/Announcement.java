package pojo;

import java.sql.Date;

public class Announcement {

  private long id;
  private String anTitle;
  private String anText;
  private java.sql.Date anDate;

  public Announcement() {
  }

  public Announcement(long id, String anTitle, String anText, Date anDate) {
    this.id = id;
    this.anTitle = anTitle;
    this.anText = anText;
    this.anDate = anDate;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAnTitle() {
    return anTitle;
  }

  public void setAnTitle(String anTitle) {
    this.anTitle = anTitle;
  }

  public String getAnText() {
    return anText;
  }

  public void setAnText(String anText) {
    this.anText = anText;
  }

  public Date getAnDate() {
    return anDate;
  }

  public void setAnDate(Date anDate) {
    this.anDate = anDate;
  }
}
