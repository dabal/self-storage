package pl.dabal.selfstorage.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "storages")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @NotBlank
    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "storage")
    private List<Item> items;

}
