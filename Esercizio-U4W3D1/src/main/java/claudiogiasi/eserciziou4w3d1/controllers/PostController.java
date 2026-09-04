package claudiogiasi.eserciziou4w3d1.controllers;

import claudiogiasi.eserciziou4w3d1.entities.BlogPost;
import claudiogiasi.eserciziou4w3d1.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")

public class PostController {
    // POST http://port:3001/api/posts + request.body
    // GET http://port:3001/api/posts
    // GET http://port:3001/api/posts/{id}
    // PUT http://port:3001/api/posts/{id} + request.body
    // DELETE http://port:3001/api/posts/{id}

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public BlogPost create(@RequestBody BlogPost blogPost) {
        return postService.create(blogPost);
    }

    @GetMapping("/{id}")
    public BlogPost findById(@PathVariable Long id) {
        return postService.findById(id); // 200
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK) // 200
    public List<BlogPost> findAll(@RequestParam(required = false) String isPublished) {
        return postService.findAll(isPublished);
    }

    @PutMapping("/{id}")
    public BlogPost update(@PathVariable Long id, @RequestBody BlogPost blogPost) {
        return postService.update(id, blogPost); // 200
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }
}
