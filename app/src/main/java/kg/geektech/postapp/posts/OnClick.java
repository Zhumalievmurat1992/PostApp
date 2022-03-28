package kg.geektech.postapp.posts;

import kg.geektech.postapp.models.Post;

public interface OnClick {
    void click(Post post);
    void  longClick( Post post);

}
