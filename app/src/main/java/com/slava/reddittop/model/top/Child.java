package com.slava.reddittop.model.top;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Child {

    @SerializedName("data")
    @Expose
    private Post data;

    public Post getData() {
        return data;
    }

    public void setData(Post data) {
        this.data = data;
    }

}
