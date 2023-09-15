package test.connect.myapplication.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import test.connect.myapplication.model.Comment;

public interface CommentApi {

    @GET("comment/all")
    Call<List<Comment>> GetAllComment();

    @POST("comment/post/{f}/{l}/{c}")
    Call<Comment> PostCommentByPath(@Path("f") String firstName, @Path("l") String lastName, @Path("c") String comment);

    @POST("comment/post")
    Call<Comment> PostCommentByBody(@Body Comment newComment);

}
