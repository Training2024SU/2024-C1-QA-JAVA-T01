package co.com.sofka.businessLogic.generalAdmin.interf;

import co.com.sofka.model.Novel;

import java.util.List;

public interface NovelManagement {
    public void insertNovel(Novel novel);
    public List<Novel> getAllNovels();
    public Novel getNovelByTitle(String id);
    public void updateNovel(Novel novel);
    public void deleteNovel(Novel novel);
}
