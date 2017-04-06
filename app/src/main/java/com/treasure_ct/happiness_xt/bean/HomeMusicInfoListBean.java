package com.treasure_ct.happiness_xt.bean;

import android.graphics.Bitmap;

/**音乐信息
 * Created by chenling on 2016/3/15.
 */
public class HomeMusicInfoListBean {
    private int id;
    private String title;
    private String artist;
    private String url;
    private String time;
    private String size;
    private int albumId;
    private String album;
    private Bitmap image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    //格式化时间
    public void setTime(int time) {
        time /= 1000;
        int minute = time / 60;
        int hour = minute / 60;
        int second = time % 60;
        minute %= 60;
        this.time = String.format("%02d:%02d", minute, second);
    }

    public String getSize() {
        return size;
    }

    public void setSize(Long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            this.size = String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            this.size = String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            this.size = String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            this.size = String.format("%d B", size);
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
