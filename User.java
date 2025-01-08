/**
 * Represents a user in a social network. A user is characterized by a name,
 * a list of user names that s/he follows, and the list's size.
 */
public class User {

    // Maximum number of users that a user can follow
    static int maxfCount = 10;

    private String name; // name of this user
    private String[] follows; // array of user names that this user follows
    private int fCount; // actual number of followees (must be <= maxfCount)

    /** Creates a user with an empty list of followees. */
    public User(String name) {
        this.name = name;
        this.follows = new String[this.maxfCount]; // fixed-size array for storing followees
        this.fCount = 0; // initial number of followees
    }

    /**
     * Creates a user with some followees. The only purpose of this constructor is
     * to allow testing the toString and follows methods, before implementing other
     * methods.
     */
    public User(String name, boolean gettingStarted) {
        this(name);
        this.follows[0] = "Foo";
        this.follows[1] = "Bar";
        this.follows[2] = "Baz";
        this.fCount = 3;
    }

    /** Returns the name of this user. */
    public String getName() {
        return this.name;
    }

    /** Returns the follows array. */
    public String[] getfFollows() {
        return this.follows;
    }

    /** Returns the number of users that this user follows. */
    public int getfCount() {
        return this.fCount;
    }

    public String upperName(String name) {
        String theName = name;
        if (name.charAt(0) >= 'a' && name.charAt(0) <= 'z') {
            char beginning = Character.toUpperCase(name.charAt(0));
            theName = beginning + name.substring(1);
        }
        return theName;

    }

    /**
     * If this user follows the given name, returns true; otherwise returns false.
     */
    public boolean follows(String name) {
        String theName = upperName(name);
        for (int i = 0; i < this.fCount; i++) {
            if (this.follows[i].equals(theName)) {
                return true;
            }
        }
        return false;
    }

    public boolean follows(String name, boolean add) {
        for (int i = 0; i < this.fCount; i++) {
            if (this.follows[i].equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Makes this user follow the given name. If successful, returns true.
     * If this user already follows the given name, or if the follows list is full,
     * does nothing and returns false;
     */
    public boolean addFollowee(String name) {
        if (this.fCount >= this.maxfCount) {
            return false;
        }
        if (this.follows(name, true) || this.follows(name)) {
            return false;
        } else {
            this.follows[fCount++] = name;
            return true;
        }

    }

    /**
     * Removes the given name from the follows list of this user. If successful,
     * returns true.
     * If the name is not in the list, does nothing and returns false.
     */
    public boolean removeFollowee(String name) {
        if (this.follows == null || this.follows.length == 0)
            return false;
        if (this.follows == null || this.fCount == 0)
            return false;
        if (follows(name)) {
            int indexName = indexOfName(name);
            --fCount;
            for (int i = 0; i < fCount; i++) {
                this.follows[indexName] = this.follows[indexName + 1];
            }
            return true;
        }
        return false;
    }

    public int indexOfName(String name) {
        for (int i = 0; i < this.follows.length; i++) {
            if (this.follows[i].equals(name)) {
                return i;
            }
        }
        return -1;

    }

    /**
     * Counts the number of users that both this user and the other user follow.
     * /* Notice: This is the size of the intersection of the two follows lists.
     */
    public int countMutual(User other) {
        int count = 0;
        for (int i = 0; i < this.fCount; i++) {
            if (other.follows(this.follows[i])) {
                ++count;
            }
        }
        return count;
    }

    /**
     * Checks is this user is a friend of the other user.
     * (if two users follow each other, they are said to be "friends.")
     */
    public boolean isFriendOf(User other) {
        return this.follows(other.name) && other.follows(this.name);
    }

    /** Returns this user's name, and the names that s/he follows. */
    public String toString() {
        String theName = upperName(this.name);
        String ans = theName + " -> ";
        for (int i = 0; i < fCount; i++) {
            ans = ans + follows[i] + " ";
        }

        return ans;
    }
}