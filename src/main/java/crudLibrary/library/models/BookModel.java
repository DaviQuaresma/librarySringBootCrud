package crudLibrary.library.models;

import jakarta.persistence.*;
import java.util.Date;


@Entity
@Table(name = "books")
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    @Temporal(TemporalType.DATE)
    private Date publishDate;

    private Long userId;
    private boolean userLoan;

    @Temporal(TemporalType.DATE)
    private Date loanDate;

    @Temporal(TemporalType.DATE)
    private Date dueDate;

    private boolean isOverdue;
    private String notes;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Date getPublishDate() { return publishDate; }
    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public boolean isUserLoan() { return userLoan; }
    public void setUserLoan(boolean userLoan) { this.userLoan = userLoan; }

    public Date getLoanDate() { return loanDate; }
    public void setLoanDate(Date loanDate) { this.loanDate = loanDate; }

    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public boolean isOverdue() { return isOverdue; }
    public void setOverdue(boolean overdue) { isOverdue = overdue; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
