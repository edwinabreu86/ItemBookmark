package com.edwin.abreusoft.itembookmark;

public class Verse {

    private String book, verse, content;
    private boolean favorite;

    public Verse(String book, String verse, String content) {
        this.book = book;
        this.verse = verse;
        this.content = content;
        favorite = false;
    }

    public String getBook() {
        return book;
    }

    public String getVerse() {
        return verse;
    }

    public String getContent() {
        return content;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
