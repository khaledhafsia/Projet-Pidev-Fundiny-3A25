package tn.esprit.models;

public class comment {
    private int commentid;
    private int postid; // Changed to postid
    private String comment; // Changed to comment

    // Constructor
    public comment(int postid, String comment) {
        this.postid = postid;
        this.comment = comment;
    }

    public comment() {

    }

    // Getters and setters
    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "comment{" +
                "commentid=" + commentid +
                ", postid=" + postid +
                ", comment='" + comment + '\'' +
                '}';
    }
}
