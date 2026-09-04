package claudiogiasi.eserciziou4w3d1.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Table(name = "post")
@Entity
@Getter
@ToString

public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String category;
    private String title;
    private String cover;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(name = "reading_time")
    private int readingTime;
    private boolean isPublished;

    @ManyToOne
    @JoinColumn(name = "user_id",  nullable = false)
    private User user;

    public BlogPost() {
    }

    public BlogPost(String category, String title, String content, int readingTime) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.readingTime = readingTime;
        this.cover = "https://picsum.photos/300/200";
        this.isPublished = false;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setReadingTime(int readingTime) {
        this.readingTime = readingTime;
    }

    public void setPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
