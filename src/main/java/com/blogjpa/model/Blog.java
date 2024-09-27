package com.blogjpa.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]*")
    private String author;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]*")
    private String title;
    @Column(columnDefinition = "LONGTEXT")
    @NotBlank
    @Size( min = 100,max = 1234567, message = "Noi dung phai chua min 100 ki tu, max 123457 ki tu")
    private String content;

    @JoinColumn(name = "category_id")
    @ManyToOne
    private Category category;
    @CreationTimestamp
    private LocalDateTime createAt;

    public Blog() {
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
