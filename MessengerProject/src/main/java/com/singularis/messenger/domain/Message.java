package com.singularis.messenger.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;

@Entity
@Table(name = "message", schema = "messenger", catalog = "")
public class Message {
    private int id;
    private int id_user;
    private int id_dialog;
    private String content;
    private String date;
    private String speaker;
    private String avatarLink;

    private User user;

    @ManyToOne
    @JoinColumn(name="user", nullable = true)
    @JsonBackReference
    public User getUser(){
        return this.user;
    }
    public void setUser(User user){
        this.user = user;
    }

    @Id
    @GeneratedValue
    @Basic
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Basic
    @Column(name = "id_user", nullable = false)
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Basic
    @Column(name = "id_dialog", nullable = false)
    public int getId_dialog() {
        return id_dialog;
    }

    public void setId_dialog(int id_dialog) {
        this.id_dialog = id_dialog;
    }

    @Basic
    @Column(name = "speaker")
    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    @Basic
    @Column(name="avatarLink")
    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }
}
