package com.example.zhongyu.retrofitdemo.Dao;



// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "NEWS".
 */
public class News {

    private Long id;
    /** Not-null value. */
    private String title;
    private String content;
    private java.util.Date publishDate;
    private Integer commentCount;

    public News() {
    }

    public News(Long id) {
        this.id = id;
    }

    public News(Long id, String title, String content, java.util.Date publishDate, Integer commentCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
        this.commentCount = commentCount;
    }

    public Long getId() {
        return id;
    }

    public News setId(Long id) {
        this.id = id;
        return this;
    }

    /** Not-null value. */
    public String getTitle() {
        return title;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public News setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public News setContent(String content) {
        this.content = content;
        return this;
    }

    public java.util.Date getPublishDate() {
        return publishDate;
    }

    public News setPublishDate(java.util.Date publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public News setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
        return this;
    }

}
