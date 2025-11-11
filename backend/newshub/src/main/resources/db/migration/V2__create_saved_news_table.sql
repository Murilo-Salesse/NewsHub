CREATE TABLE saved_news (
    id BIGINT PRIMARY KEY,
    article_id VARCHAR(255),
    title VARCHAR(500),
    description TEXT,
    url VARCHAR(500),
    image VARCHAR(500),
    published_at VARCHAR(100),
    user_id BIGINT,

    CONSTRAINT fk_saved_news_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);