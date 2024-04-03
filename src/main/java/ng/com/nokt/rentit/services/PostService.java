package ng.com.nokt.rentit.services;

import ng.com.nokt.rentit.entities.Post;
import ng.com.nokt.rentit.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Optional<Post> getPostById(Long id){
        return postRepository.findById(id);
    }

    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public void delete(Post post){
        postRepository.delete(post);
    }

    public Post save(Post post){
        if(post.getId() ==  null){
            post.setCreatedAt(LocalDateTime.now());
        }

        return postRepository.save(post);
    }
}
