package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "Graves")
@Table(name = "Graves")
public class Grave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable=false, updatable=false)
    private Long graveId;

    @Column(name = "Material", nullable = false)
    private String material;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Photo")
    private String photo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;

    public Grave(String material, String name, String description, String photo) {
        this.material = material;
        this.name = name;
        this.description = description;
        this.photo = photo;
    }

    public Grave() {}

    public Long getGraveId() {
        return graveId;
    }

    public String getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }

    public User getUser() {
        return user;
    }

    public void setGraveId(Long graveId) {
        this.graveId = graveId;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grave grave = (Grave) o;
        return Objects.equals(graveId, grave.graveId) && Objects.equals(material, grave.material) && Objects.equals(name, grave.name) && Objects.equals(description, grave.description) && Objects.equals(photo, grave.photo) && Objects.equals(user, grave.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(graveId, material, name, description, photo, user);
    }

    @Override
    public String toString() {
        return "Grave{" +
                "graveId=" + graveId +
                ", material='" + material + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                ", user=" + user +
                '}';
    }
}
