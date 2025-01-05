package Nhom_06.HuaKieuLam.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "item_invoice")
public class ItemInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Sử dụng 'id' thay vì 'Id'

    @Column(name = "quantity")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY) // Cân nhắc EAGER nếu cần tải dữ liệu ngay lập tức
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ToString.Exclude
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY) // Cân nhắc EAGER nếu cần tải dữ liệu ngay lập tức
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    @ToString.Exclude
    private Invoice invoice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemInvoice that = (ItemInvoice) o;
        return id != null && Objects.equals(id, that.id); // Sử dụng 'id' thay vì 'getId()'
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Sử dụng 'id' thay vì getClass().hashCode()
    }
}
