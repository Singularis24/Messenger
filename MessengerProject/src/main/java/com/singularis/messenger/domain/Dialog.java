package com.singularis.messenger.domain;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dialog", schema = "messenger", catalog = "")
public class Dialog {
    private int id;
    private Date createDate;
    private String members;

    private List<User> users = new ArrayList<>();
    @ManyToMany (mappedBy = "dialoges")
    public List<User> getUsers() {return users;}
    public void setUsers(List<User> users){this.users = users;}

    private List<Message> messages = new ArrayList<>();
    @OneToMany(fetch = FetchType.EAGER, cascade= CascadeType.ALL)
    @JoinColumn(name = "messages")
    public List<Message> getMessages() {
        return messages;
    }
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "create_date", nullable = false)
    public LocalDate getCreateDate() {
        return createDate.toLocalDate();
    }

    public void setCreateDate(LocalDate createDate){
        this.createDate = Date.valueOf(createDate);
    }

    @Basic
    @Column(name = "members", nullable = false)
    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }
}
