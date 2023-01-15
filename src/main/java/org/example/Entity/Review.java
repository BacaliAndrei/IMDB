package org.example.Entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Review extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    private Integer rating;

    private Date postDate;

    private String content;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public Review() {
    }

    public Review(AppUser appUser, Integer rating, Date postDate, String content, Movie movie) {
        this.appUser = appUser;
        this.rating = rating;
        this.postDate = postDate;
        this.content = content;
        this.movie = movie;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id='" + this.getId() + '\'' +
                ", appUser=" + appUser +
                ", rating=" + rating +
                ", postDate=" + postDate +
                ", content='" + content + '\'' +
                ", movie=" + movie +
                '}';
    }

}
