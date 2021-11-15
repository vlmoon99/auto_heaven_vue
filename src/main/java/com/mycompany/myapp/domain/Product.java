package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "country_of_origin", length = 50, nullable = false)
    private String countryOfOrigin;

    @NotNull
    @Column(name = "available", nullable = false)
    private Boolean available;

    @NotNull
    @Size(max = 255)
    @Column(name = "picture_urls", length = 255, nullable = false)
    private String pictureUrls;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Size(max = 50)
    @Column(name = "vendor", length = 50)
    private String vendor;

    @Size(max = 50)
    @Column(name = "vendor_code", length = 50)
    private String vendorCode;

    @Lob
    @Column(name = "properties", nullable = false)
    private byte[] properties;

    @NotNull
    @Column(name = "properties_content_type", nullable = false)
    private String propertiesContentType;

    @NotNull
    @Column(name = "price", precision = 21, scale = 2, nullable = false)
    private BigDecimal price;

    @NotNull
    @Column(name = "total_rating", nullable = false)
    private Double totalRating;

    @Size(max = 256)
    @Column(name = "description", length = 256)
    private String description;

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "product" }, allowSetters = true)
    private Set<Review> reviews = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "products" }, allowSetters = true)
    private Order order;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryOfOrigin() {
        return this.countryOfOrigin;
    }

    public Product countryOfOrigin(String countryOfOrigin) {
        this.setCountryOfOrigin(countryOfOrigin);
        return this;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public Boolean getAvailable() {
        return this.available;
    }

    public Product available(Boolean available) {
        this.setAvailable(available);
        return this;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getPictureUrls() {
        return this.pictureUrls;
    }

    public Product pictureUrls(String pictureUrls) {
        this.setPictureUrls(pictureUrls);
        return this;
    }

    public void setPictureUrls(String pictureUrls) {
        this.pictureUrls = pictureUrls;
    }

    public String getName() {
        return this.name;
    }

    public Product name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return this.vendor;
    }

    public Product vendor(String vendor) {
        this.setVendor(vendor);
        return this;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVendorCode() {
        return this.vendorCode;
    }

    public Product vendorCode(String vendorCode) {
        this.setVendorCode(vendorCode);
        return this;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public byte[] getProperties() {
        return this.properties;
    }

    public Product properties(byte[] properties) {
        this.setProperties(properties);
        return this;
    }

    public void setProperties(byte[] properties) {
        this.properties = properties;
    }

    public String getPropertiesContentType() {
        return this.propertiesContentType;
    }

    public Product propertiesContentType(String propertiesContentType) {
        this.propertiesContentType = propertiesContentType;
        return this;
    }

    public void setPropertiesContentType(String propertiesContentType) {
        this.propertiesContentType = propertiesContentType;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public Product price(BigDecimal price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getTotalRating() {
        return this.totalRating;
    }

    public Product totalRating(Double totalRating) {
        this.setTotalRating(totalRating);
        return this;
    }

    public void setTotalRating(Double totalRating) {
        this.totalRating = totalRating;
    }

    public String getDescription() {
        return this.description;
    }

    public Product description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Review> getReviews() {
        return this.reviews;
    }

    public void setReviews(Set<Review> reviews) {
        if (this.reviews != null) {
            this.reviews.forEach(i -> i.setProduct(null));
        }
        if (reviews != null) {
            reviews.forEach(i -> i.setProduct(this));
        }
        this.reviews = reviews;
    }

    public Product reviews(Set<Review> reviews) {
        this.setReviews(reviews);
        return this;
    }

    public Product addReview(Review review) {
        this.reviews.add(review);
        review.setProduct(this);
        return this;
    }

    public Product removeReview(Review review) {
        this.reviews.remove(review);
        review.setProduct(null);
        return this;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product order(Order order) {
        this.setOrder(order);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", countryOfOrigin='" + getCountryOfOrigin() + "'" +
            ", available='" + getAvailable() + "'" +
            ", pictureUrls='" + getPictureUrls() + "'" +
            ", name='" + getName() + "'" +
            ", vendor='" + getVendor() + "'" +
            ", vendorCode='" + getVendorCode() + "'" +
            ", properties='" + getProperties() + "'" +
            ", propertiesContentType='" + getPropertiesContentType() + "'" +
            ", price=" + getPrice() +
            ", totalRating=" + getTotalRating() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
