package com.aiexpanse.lang;

/**
 * <p>A convenience class to represent left-right pair.</p>
 */
public class Pair<L,R> {

   /**
    * Left of this <code>Pair</code>.
    */
   private L left;

   /**
    * Gets the left for this pair.
    * @return left for this pair
    */
   public L getLeft() {
       return left;
   }

   /**
    * Right of this this <code>Pair</code>.
    */
   private R right;

   /**
    * Gets the right for this pair.
    * @return right for this pair
    */
   public R getRight() { return right; }

   /**
    * Creates a new pair
    * @param left The left for this pair
    * @param right The right to use for this pair
    */
   public Pair(L left, R right) {
       this.left = left;
       this.right = right;
   }

   /**
    * <p><code>String</code> representation of this
    * <code>Pair</code>.</p>
    *
    *  @return <code>String</code> representation of this <code>Pair</code>
    */
   @Override
   public String toString() {
       return "<" + left + "," + right + ">";
   }

   /**
    * <p>Generate a hash code for this <code>Pair</code>.</p>
    *
    * <p>The hash code is calculated using both the left and
    * the right of the <code>Pair</code>.</p>
    *
    * @return hash code for this <code>Pair</code>
    */
   @Override
   public int hashCode() {
       // name's hashCode is multiplied by an arbitrary prime number (13)
       // in order to make sure there is a difference in the hashCode between
       // these two parameters:
       //  left: a  right: aa
       //  left: aa right: a
       return left.hashCode() * 13 + (right == null ? 0 : right.hashCode());
   }

    /**
     * <p>Test this <code>Pair</code> for equality with another
     * <code>Object</code>.</p>
     *
     * <p>If the <code>Object</code> to be tested is not a
     * <code>Pair</code> or is <code>null</code>, then this method
     * returns <code>false</code>.</p>
     *
     * <p>Two <code>Pair</code>s are considered equal if and only if
     * both the lefts and rights are equal.</p>
     *
     * @param o the <code>Object</code> to test for
     * equality with this <code>Pair</code>
     * @return <code>true</code> if the given <code>Object</code> is
     * equal to this <code>Pair</code> else <code>false</code>
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof Pair) {
            Pair pair = (Pair) o;
            if (left != null ? !left.equals(pair.left) : pair.left != null) return false;
            if (right != null ? !right.equals(pair.right) : pair.right != null) return false;
            return true;
        }
        return false;
    }
}

