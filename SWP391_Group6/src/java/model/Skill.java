
package model;

//import java.util.List;

public class Skill {
    private int id;
    private String skillname;
    private boolean status;
    private String description;
    private String image; 
//    private List<Mentor_Skill> mentorSkills;

    public Skill() {
    }

    public Skill(int id, String skillname, boolean status, String description, String image) {
        this.id = id;
        this.skillname = skillname;
        this.status = status;
        this.description = description;
        this.image = image;
//        this.mentorSkills = mentorSkills;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    public List<Mentor_Skill> getMentorSkills() {
//        return mentorSkills;
//    }
//
//    public void setMentorSkills(List<Mentor_Skill> mentorSkills) {
//        this.mentorSkills = mentorSkills;
//    }
}
