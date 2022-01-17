package com.eugenevdovin.marvelheroestestproject.entity;

import javax.imageio.ImageIO;
import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Entity
@Table(name = "pictures")
public class PictureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "picture_id")
    private int id;
    @Column(name = "picture_array")
    private byte[] imageBytes;
    @ManyToOne
    @JoinColumn(name = "character_id")
    CharacterEntity characterEntity;
    @ManyToOne
    @JoinColumn(name = "comic_id")
    ComicEntity comicEntity;

    public BufferedImage getImage() {
        InputStream in = new ByteArrayInputStream(imageBytes);
        try {
            return ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setImage(BufferedImage image) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "PNG", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageBytes = out.toByteArray();
    }
}
