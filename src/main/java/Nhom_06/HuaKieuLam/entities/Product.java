package Nhom_06.HuaKieuLam.entities;

import Nhom_06.HuaKieuLam.repositories.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Min(value = 1000, groups = ValidationGroups.OnCreate.class)
    @Column(name = "price")
    private Double price;

    @Column(name = "image_url")
    private String imageUrl;

    @Min(value = 0, message = "Must be greater than or equal to 0", groups = ValidationGroups.OnCreate.class)
    @Column(name = "caloriesPerGram")
    private Double caloriesPerGram;

    @Min(value = 0, message = "Must be greater than or equal to 0", groups = ValidationGroups.OnCreate.class)
    @Column(name = "protein")
    private Double protein;

    @Min(value = 0, message = "Must be greater than or equal to 0", groups = ValidationGroups.OnCreate.class)
    @Column(name = "carbs")
    private Double carbs;

    @Min(value = 0, message = "Must be greater than or equal to 0", groups = ValidationGroups.OnCreate.class)
    @Column(name = "fat")
    private Double fat;

    @Min(value = 0, message = "Must be greater than or equal to 0", groups = ValidationGroups.OnCreate.class)
    @Column(name = "alcohol")
    private Double alcohol;

    @Min(value = 0, message = "Must be greater than or equal to 0", groups = ValidationGroups.OnCreate.class)
    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ToString.Exclude
    private Category category;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) !=
                Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return getId() != null && Objects.equals(getId(),
                product.getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}


//package Nhom_06.HuaKieuLam.entities;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.*;
//import lombok.*;
//import org.hibernate.Hibernate;
//
//import java.util.Objects;
//
//@Getter
//@Setter
//@ToString
//@RequiredArgsConstructor
//@AllArgsConstructor
//@Entity
//@Builder
//@Table(name = "product")
//public class Product {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "title", length = 50, nullable = false)
//    @NotBlank(message = "Title is required")
//    private String title;
//
//    @Column(name = "price")
//    @NotNull(message = "Price is required")
//    @Positive(message = "Price must be greater than 0")
//    private Double price;
//
//    @Column(name = "image_url")
//    @NotBlank(message = "Image URL is required")
//    private String imageUrl;
//
//    @Column(name = "caloriesPerGram")
//    @NotNull(message = "Calories per gram is required")
//    @PositiveOrZero(message = "Calories per gram must be 0 or greater")
//    private Double caloriesPerGram;
//
//    @Column(name = "protein")
//    @PositiveOrZero(message = "Protein must be 0 or greater")
//    private Double protein;
//
//    @Column(name = "carbs")
//    @PositiveOrZero(message = "Carbs must be 0 or greater")
//    private Double carbs;
//
//    @Column(name = "fat")
//    @PositiveOrZero(message = "Fat must be 0 or greater")
//    private Double fat;
//
//    @Column(name = "alcohol")
//    @PositiveOrZero(message = "Alcohol must be 0 or greater")
//    private Double alcohol;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id", referencedColumnName = "id")
//    @ToString.Exclude
//    private Category category;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        Product product = (Product) o;
//        return getId() != null && Objects.equals(getId(), product.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
//}
