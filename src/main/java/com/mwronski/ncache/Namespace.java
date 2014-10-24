package com.mwronski.ncache;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import static com.google.common.base.Objects.toStringHelper;

/**
 * Namespace of the cache region
 *
 * @author Michal Wronski
 * @date 24.10.14.
 */
final class Namespace {

    private final String name;
    private final Object[] attributes;

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(name).
                append(attributes).
                hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (!(obj instanceof Namespace)) {
            return false;
        }
        Namespace other = (Namespace) obj;
        return new EqualsBuilder().
                append(name, other.name).
                append(attributes, other.attributes).
                isEquals();
    }

    @Override
    public String toString() {
        return toStringHelper(this).
                add("name", name).
                add("attributes", ArrayUtils.toString(attributes)).
                toString();
    }

    /**
     * Create instance
     *
     * @param name       namespace name
     * @param attributes namespace additional attributes
     */
    Namespace(String name, Object... attributes) {
        this.name = name;
        this.attributes = attributes;
    }

}
