package claudiogiasi.eserciziou4w3d1.services;

import claudiogiasi.eserciziou4w3d1.entities.BlogPost;
import claudiogiasi.eserciziou4w3d1.exceptions.NotFoundException;
import claudiogiasi.eserciziou4w3d1.exceptions.ValidationException;
import claudiogiasi.eserciziou4w3d1.repositories.PostRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepo postRepo;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public BlogPost create(BlogPost blogPost) {

        if (blogPost.getCategory() == null || blogPost.getCategory().isBlank()) {
            throw new ValidationException("La categoria è obbligatoria");
        }

        if (blogPost.getTitle() == null || blogPost.getTitle().isBlank()) {
            throw new ValidationException("Il titolo è obbligatorio");
        }

        if (blogPost.getContent() == null || blogPost.getContent().isBlank()) {
            throw new ValidationException("Il contenuto è obbligatorio");
        }

        if (blogPost.getReadingTime() < 0) {
            throw new ValidationException("Il tempo di lettura non può essere un valore negativo");
        }

        BlogPost newPost = new BlogPost(
                blogPost.getCategory(),
                blogPost.getTitle(),
                blogPost.getContent(),
                blogPost.getReadingTime()
        );

        return postRepo.save(newPost);
    }

    public BlogPost findById(Long id) {
        return postRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public List<BlogPost> findAll(String isPublished) {
        List<BlogPost> allPosts = postRepo.findAll();

        if (isPublished == null) {
            return allPosts;
        }

        boolean filter = Boolean.parseBoolean(isPublished);
        return allPosts.stream()
                .filter(post -> post.isPublished() == filter)
                .collect(Collectors.toList());
    }

    public BlogPost update(Long id, BlogPost blogPostUpdated) {
        if (blogPostUpdated.getCategory() == null || blogPostUpdated.getCategory().isBlank()) {
            throw new ValidationException("La categoria è obbligatoria");
        }

        if (blogPostUpdated.getTitle() == null || blogPostUpdated.getTitle().isBlank()) {
            throw new ValidationException("Il titolo è obbligatorio");
        }

        if (blogPostUpdated.getContent() == null || blogPostUpdated.getContent().isBlank()) {
            throw new ValidationException("Il contenuto è obbligatorio");
        }

        if (blogPostUpdated.getReadingTime() < 0) {
            throw new ValidationException("Il tempo di lettura non può essere un valore negativo");
        }

        BlogPost existingPost = postRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
        existingPost.setCategory(blogPostUpdated.getCategory());
        existingPost.setTitle(blogPostUpdated.getTitle());
        existingPost.setContent(blogPostUpdated.getContent());
        existingPost.setReadingTime(blogPostUpdated.getReadingTime());
        existingPost.setPublished(blogPostUpdated.isPublished());
        return postRepo.save(existingPost);
    }

    public void delete(Long id) {
        BlogPost blogPost = findById(id);
        postRepo.delete(blogPost);
    }
}
