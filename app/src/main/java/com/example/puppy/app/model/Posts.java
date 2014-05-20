package com.example.puppy.app.model;

import java.util.List;

public class Posts {
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setNewPose(List<Post> posts) {
        this.posts.addAll(posts);
    }
}
