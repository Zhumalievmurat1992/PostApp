package kg.geektech.postapp.models;

import com.google.gson.annotations.SerializedName;

public class Post {
    int id;
    String title;
    String content;
    @SerializedName("user")
    int userId;
    @SerializedName("group")
    int groupId;

    public Post(String title, String content, int userId, int groupId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getUserId() {
        return userId;
    }

    public int getGroupId() {
        return groupId;
    }
}
