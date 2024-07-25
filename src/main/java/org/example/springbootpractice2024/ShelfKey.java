package org.example.springbootpractice2024;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ShelfKey implements Serializable {
    private int shelvesId;
    private String nameOfGoods;

    public ShelfKey() {}

    public ShelfKey(int shelvesId, String nameOfGoods) {
        this.shelvesId = shelvesId;
        this.nameOfGoods = nameOfGoods;
    }

    // Getters and setters
    public int getShelvesId() {
        return shelvesId;
    }

    public void setShelvesId(int shelvesId) {
        this.shelvesId = shelvesId;
    }

    public String getNameOfGoods() {
        return nameOfGoods;
    }

    public void setNameOfGoods(String nameOfGoods) {
        this.nameOfGoods = nameOfGoods;
    }

    // hashCode and equals must be overridden for embeddable key
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nameOfGoods == null) ? 0 : nameOfGoods.hashCode());
        result = prime * result + shelvesId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ShelfKey other = (ShelfKey) obj;
        if (nameOfGoods == null) {
            if (other.nameOfGoods != null)
                return false;
        } else if (!nameOfGoods.equals(other.nameOfGoods))
            return false;
        return shelvesId == other.shelvesId;
    }
}
