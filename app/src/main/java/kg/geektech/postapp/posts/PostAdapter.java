package kg.geektech.postapp.posts;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kg.geektech.postapp.databinding.ItemPostBinding;
import kg.geektech.postapp.models.Post;
import kg.geektech.postapp.ui.form.FormFragment;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> posts = new ArrayList<>();
    HashMap<Integer, String> user = new HashMap<>();
    private OnClick onClick;

    public PostAdapter(FormFragment formFragment) {

    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    public PostAdapter(OnClick onClick) {
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPostBinding binding = ItemPostBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        user.put(1, "Султан Джумалиев");
        user.put(2, "Бекжан Маданбеков");
        user.put(3, "Бакай Белеков");
        user.put(4, "Медербек Шермаматов");
        user.put(5, "Адахан Касымалиев");
        user.put(6, "Жумалиев Мурат");
        user.put(7, "Альберт Жумаев");
        user.put(8, "Милана Анарбекова");
        user.put(9, "Таиров Сагыналы");
        user.put(10, "Уланбек уулу Расул");
        user.put(11, "Жакипов Абдулла");
        user.put(12, "Мыктарбекова Бермет");
        user.put(13, "Айпери Ашыралиева");
        user.put(14, "Гулбарчын Алиева");
        user.put(15, "Эрнис уулу Альберт");
        user.put(16, "Джапаркулов Ахмад");
        user.put(17, "Акедос Мукашев");
        user.put(18, "Касымов Рафкат");
        user.put(19, "Максим Катунин");
        user.put(20, "Жанышев Султанкул");

//        Султан Джумалиев
//        Бекжан Маданбеков
//        Бакай Белеков
//        Медербек Шермаматов
//        Адахан Касымалиев
//        Жумалиев Мурат
//        Альберт Жумаев
//        Милана Анарбекова
//        Таиров Сагыналы
//        Уланбек уулу Расул
//        Жакипов Абдулла
//        Мыктарбекова Бермет
//        Айпери Ашыралиева
//        Гулбарчын Алиева
//        Эрнис уулу Альберт
//        Джапаркулов Ахмад
//        Акедос Мукашев
//        Касымов Рафкат
//        Максим Катунин
//        Жанышев Султанкул
        return new PostViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.onBind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void remove(Post post) {
        int id = posts.lastIndexOf(post);
        posts.remove(post);
        notifyItemRemoved(id);
    }

    protected class PostViewHolder extends RecyclerView.ViewHolder {

        private ItemPostBinding binding;


        public PostViewHolder(@NonNull ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Post post) {
            binding.tvUserId.setText(user.get(post.getUserId()));
            binding.tvTitle.setText(post.getTitle());
            binding.tvContent.setText(post.getContent());
            itemView.setOnClickListener(view -> onClick.click(post));
            itemView.setOnLongClickListener(view -> {
             onClick.longClick(post);
                return true;
            });
        }
    }


}
