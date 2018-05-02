package com.cafe24.lms.domain;

import java.io.Serializable;

public class RentItemId implements Serializable {
    private Long user;
    private Long item;
    
    public Long getUser() {
        return user;
    }
    public void setUser(Long user) {
        this.user = user;
    }
    public Long getItem() {
        return item;
    }
    public void setItem(Long item) {
        this.item = item;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RentItemId other = (RentItemId) obj;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (user == null) {
            if (other.user != null)
                return false;
        } else if (!user.equals(other.user))
            return false;
        return true;
    }
}
