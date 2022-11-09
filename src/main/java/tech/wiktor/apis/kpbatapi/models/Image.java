package tech.wiktor.apis.kpbatapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "images")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column()
    private String type;

    @Column(unique = false, nullable = false, length = 100000)
    private byte[] image;

    @ManyToOne()
    private Category category;
}