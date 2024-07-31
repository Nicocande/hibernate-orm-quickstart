package com.primeur.stage.domain.dto;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "known_books")
@NamedQuery(name = "Books.findAll", query = "SELECT f FROM Book f ORDER BY f.title", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class Book {

    @Id
    @SequenceGenerator(name = "booksSequence", sequenceName = "known_books_id_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "booksSequence")
    private Integer id;

    @Column( name= "title",unique = true, nullable = false)
    private String title;

    @Column(name= "author", length = 40, nullable = false)
    private String author;

    @Column(name = "description", length = 50, nullable = false)
    private String description;

    private String publisher;

    private LocalDateTime buildDate;


    public Book() {
    }

    public Book(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getAuthor() {return author;}

    public void setAuthor(String author) {this.author = author;}

    public LocalDateTime getBuildDate() {return buildDate;}

    public void setBuildDate(LocalDateTime buildDate) {this.buildDate = buildDate;}

    public String getPublisher() {return publisher;}

    public void setPublisher(String publisher) {this.publisher = publisher;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", publisher='" + publisher + '\'' +
                ", buildDate=" + buildDate +
                '}';
    }
}
