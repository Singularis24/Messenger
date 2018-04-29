package dao;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "sticker", schema = "messenger", catalog = "")
public class StickerEntity {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StickerEntity that = (StickerEntity) o;

        if (id != that.id) return false;
        if (!Arrays.equals(content, that.content)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Arrays.hashCode(content);
        return result;
    }
}
