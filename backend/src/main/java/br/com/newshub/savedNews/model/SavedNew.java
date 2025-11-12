package br.com.newshub.savedNews.model;

import br.com.newshub.user.model.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "saved_news")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedNew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String articleId;
    private String title;
    private String description;
    private String url;
    private String image;
    private String publishedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
