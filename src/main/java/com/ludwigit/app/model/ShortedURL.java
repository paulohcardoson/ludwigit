package com.ludwigit.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "shorted_urls")
public class ShortedURL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "original_url", nullable = false)
    private String originalUrl;

}
