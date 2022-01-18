package com.eugenevdovin.marvelheroestestproject.wrapper;

import com.eugenevdovin.marvelheroestestproject.entity.CharacterEntity;
import com.eugenevdovin.marvelheroestestproject.entity.ComicEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Would be great add generics here
@Component
public class WrapExecutor {
    public static CharacterWrapper getCharacterWrapper(CharacterEntity characterEntity) {
        return new CharacterWrapper(characterEntity);
    }

    public static ComicWrapper getComicWrapper(ComicEntity comicEntity) {
        return new ComicWrapper(comicEntity);
    }

    public static List<CharacterWrapper> getCharacterWrapperList(List<CharacterEntity> characterEntityList) {
        List<CharacterWrapper> characterWrapperList = new ArrayList<>();
        characterEntityList.forEach(characterEntity
                -> characterWrapperList.add(new CharacterWrapper(characterEntity)));
        return characterWrapperList;
    }

    public static List<ComicWrapper> getComicWrapperList(List<ComicEntity> comicEntityList) {
        List<ComicWrapper> comicWrapperList = new ArrayList<>();
        comicEntityList.forEach(comicEntity
                -> comicWrapperList.add(new ComicWrapper(comicEntity)));
        return comicWrapperList;
    }
}
