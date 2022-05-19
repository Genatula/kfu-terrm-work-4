package ru.kpfu.itis.genatulin.termwork.utils;

import ru.kpfu.itis.genatulin.termwork.models.AbstractFeedPost;

import java.util.Comparator;

public class AbstractFeedPostComparator implements Comparator<AbstractFeedPost> {
    @Override
    public int compare(AbstractFeedPost o1, AbstractFeedPost o2) {
        return o1.getCreationDate().compareTo(o2.getCreationDate());
    }
}
