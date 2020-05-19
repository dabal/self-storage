package pl.dabal.selfstorage.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @DecimalMin("0.0")
    private Double quantity;

    @NotNull
    @NotBlank
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @NotNull
    @NotBlank
    @ManyToOne(fetch = FetchType.EAGER)
    private Metric metric;

    @NotBlank
    @NotNull
    @ManyToOne
    private Storage storage;


}
