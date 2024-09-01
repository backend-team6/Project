package Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "test2")
public class entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

}
