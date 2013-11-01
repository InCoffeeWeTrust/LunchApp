package se.mdh.dva217.incoffeewetrust.containers;

/**
 * Created with IntelliJ IDEA.
 * User: Tobias
 * Date: 2013-10-29
 * Time: 10:49
 * To change this template use File | Settings | File Templates.
 */
public class SchoolAndCity implements Comparable<SchoolAndCity> {

    private final String schoolName, city;

    public SchoolAndCity(String schoolName, String city) {
        // the toString() calls guards against null values
        this.schoolName = schoolName.toString();
        this.city = city.toString();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getCity() {
        return city;
    }

    /**
     * Returns schoolName + '@' + city
     */
    @Override
    public String toString() {
        return schoolName + '@' + city;
    }

    @Override
    public int compareTo(SchoolAndCity another) {
         return toString().compareTo(another.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SchoolAndCity that = (SchoolAndCity) o;

        if (!city.equals(that.city)) return false;
        if (!schoolName.equals(that.schoolName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = schoolName.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }
}
