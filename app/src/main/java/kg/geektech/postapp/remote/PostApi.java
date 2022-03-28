package kg.geektech.postapp.remote;

import java.util.List;

import kg.geektech.postapp.models.Post;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostApi {
    @GET("/posts")
    Call<List<Post>> getPosts();

    @GET("/posts/{id}")
    Call<Post> getPost(
            @Path("id")  String id
    );


    @POST("/posts")
    Call<Post> createPost(
            @Body Post post);

    @DELETE("/posts/{id}")
    Call<ResponseBody> deletePost(
            @Path("id") int id);

    @Headers({"Content-Type: application/gson"})
    @PUT("/posts/{postsId}")
    Call<Post> putPost();
}
