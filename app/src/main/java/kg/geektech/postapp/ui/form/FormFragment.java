package kg.geektech.postapp.ui.form;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import kg.geektech.postapp.App;
import kg.geektech.postapp.databinding.FragmentFormBinding;
import kg.geektech.postapp.models.Post;
import kg.geektech.postapp.posts.PostAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormFragment extends Fragment {
    private static final int GROUP_ID = 40;
    private static final int USER_ID = 6;
    private FragmentFormBinding binding;
    private PostAdapter adapter;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PostAdapter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFormBinding.inflate(
                inflater,
                container,
                false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        String id = getArguments().getString("id");
        App.api.putPost().enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()&&response.body() !=null){
                    Post post = response.body();
                    binding.etTitle.setText(post.getTitle());
                    binding.etContent.setText(post.getContent());
                }
            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show();

            }
        });

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.etTitle.getText().toString();
                String content = binding.etContent.getText().toString();

                Post post = new Post(
                        title,
                        content,
                        USER_ID,
                        GROUP_ID
                );

                App.api.createPost(post).enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                        if (response.isSuccessful()) {
                            requireActivity().onBackPressed();
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Toast.makeText(requireActivity(), "Error", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
//        String id = getArguments().getString("");
//        App.api.getPost(id).enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//                if (response.isSuccessful()&&response.body() !=null){
//                    Post post = response.body();
//                    binding.etTitle.setText(post.getTitle());
//                    binding.etContent.setText(post.getContent());
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//
//            }
//        });
    }
}