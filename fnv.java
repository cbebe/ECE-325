import java.math.BigInteger;

public class fnv {
  /**
   * 
   * @param val byte array to hash
   * @return {@code long} fnv hash of byte array
   */
  public static long hash(byte[] val) {
    BigInteger fnvOffsetBasis = new BigInteger("14695981039346656037");
    BigInteger fnvPrime = new BigInteger("1099511628211");

    BigInteger hash = fnvOffsetBasis;

    for (int i = 0; i < val.length; ++i)
      hash = fnvPrime.multiply(hash).xor(BigInteger.valueOf(val[i]));

    return hash.longValue();
  }

  public static void main(String[] args) {
    String val = "hello";

    System.out.println(hash(val.getBytes()));
  }
}
