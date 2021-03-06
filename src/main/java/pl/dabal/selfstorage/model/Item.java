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
@Builder
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
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Metric metric;

    @NotNull
    @ManyToOne
    private Storage storage;


}
