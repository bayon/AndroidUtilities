package data;

/**
 * Created by BForte on 1/30/14.
 */
public class Comment {
    private long mId;
    private String mComment;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        this.mComment = comment;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return mComment;
    }
}
