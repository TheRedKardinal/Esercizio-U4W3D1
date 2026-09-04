package claudiogiasi.eserciziou4w3d1.repositories;

import claudiogiasi.eserciziou4w3d1.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository <BlogPost, Long> {
}
