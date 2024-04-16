package co.com.sofka.DAO;
import co.com.sofka.model.Novel;

import java.util.List;

public interface NovelDAO {
    public void insertNovel(Novel novel);
    public List<Novel> getAllNovels();
    public Novel getNovelById(String id);
    public void updateNovel(Novel novel);
    public void deleteNovel(Novel novel);
}
