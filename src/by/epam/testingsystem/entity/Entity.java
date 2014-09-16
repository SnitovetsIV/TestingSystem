package by.epam.testingsystem.entity;

import org.apache.log4j.Logger;

import java.io.Serializable;

/**
 * @author Илья
 */
public abstract class Entity implements Serializable {

    private static final Logger LOG = Logger.getLogger(Entity.class);
    private static final long serialVersionUID = 7056850935410496408L;
    private int id;

    public Entity() {
    }

    /**
     * @return id of entity
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id of entity
     */
    public void setId(int id) {
        if (id >= 0) {
            this.id = id;
        } else {
            LOG.warn("ID can not be less than zero. Value has not been assigned.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Entity)) {
            return false;
        }

        Entity entity = (Entity) o;

        if (id != entity.id) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = result * 17 + id;
        return result;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}