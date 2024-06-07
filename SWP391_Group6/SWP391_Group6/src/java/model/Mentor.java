/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Mentor {
    private int id;
    private String name;
    private boolean gender;
    private String email;
    private String phone, address;
    private Date dateOfBirth;
    private String ava, job, skill, intro, achievement, experience, certificate;
    private boolean status;
    private Account account;
    private List<MentorSkill> mentorSkills;

    public Mentor() {
    }

    public Mentor(int id, String name,String email, boolean gender, String phone, String address, Date dateOfBirth, String ava, String job, String skill, String intro, String achievement, String experience, String certificate, boolean status, Account account, List<MentorSkill> mentorSkills) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.ava = ava;
        this.job = job;
        this.skill = skill;
        this.intro = intro;
        this.achievement = achievement;
        this.experience = experience;
        this.certificate = certificate;
        this.status = status;
        this.account = account;
        this.mentorSkills = mentorSkills;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAva() {
        return ava;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<MentorSkill> getMentorSkills() {
        return mentorSkills;
    }

    public void setMentorSkills(List<MentorSkill> mentorSkills) {
        this.mentorSkills = mentorSkills;
    }
}
