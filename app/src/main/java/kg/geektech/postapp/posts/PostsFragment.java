package kg.geektech.postapp.posts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

import kg.geektech.postapp.App;
import kg.geektech.postapp.R;
import kg.geektech.postapp.databinding.FragmentPostsBinding;
import kg.geektech.postapp.models.Post;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostsFragment extends Fragment implements OnClick {


    private FragmentPostsBinding binding;
    private PostAdapter adapter;
    private NavHostFragment navHostFragment;
    private NavController controller;
    public PostsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       adapter = new PostAdapter(this);
       navHostFragment = (NavHostFragment) requireActivity()
               .getSupportFragmentManager()
               .findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        controller = navHostFragment.getNavController();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPostsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recycler.setAdapter(adapter);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controller.navigate(R.id.formFragment);

            }
        });

        App.api.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()&& response.body() != null){
                    adapter.setPosts(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });

    }


    @Override
    public void click(Post post) {

        Bundle bundle = new Bundle();
        bundle.putInt("id",post.getUserId());
        controller.navigate(R.id.formFragment,bundle);

//        App.api.putPost(post.getUserId(),post).enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//
//                Bundle bundle = new Bundle();
//                bundle.putInt("",post.getUserId());
//                controller.navigate(R.id.formFragment);
//
//
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//
//            }
//        });
    }

    @Override
    public void longClick(Post post) {
        App.api.deletePost(post.getId()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                adapter.remove(post);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}