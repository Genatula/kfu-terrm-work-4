package ru.kpfu.itis.genatulin.termwork.dto;

public class UpdateArticleForm extends CreateArticleForm {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
