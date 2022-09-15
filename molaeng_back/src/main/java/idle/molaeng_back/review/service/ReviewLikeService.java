package idle.molaeng_back.review.service;

public interface ReviewLikeService {
    public void like(long userId, long reviewId);
    public void dislike(long userId, long reviewId);
}
