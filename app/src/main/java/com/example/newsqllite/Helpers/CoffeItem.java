package com.example.newsqllite.Helpers;

public class CoffeItem {
    private int imageResource;
    private String tilte;
    private String key_id;
    private String favStatus;

    public CoffeItem() {
    }

    public CoffeItem(int imageResource, String tilte, String key_id, String favStatus) {
        this.imageResource = imageResource;
        this.tilte = tilte;
        this.key_id = key_id;
        this.favStatus = favStatus;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }
}
