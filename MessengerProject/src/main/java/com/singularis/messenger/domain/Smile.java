package com.singularis.messenger.domain;

import javax.persistence.*;

@Entity
@Table(name = "smile", schema = "messenger", catalog = "")
public class Smile {
    private int id;
    private byte[] content;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "content", nullable = true)
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

}
