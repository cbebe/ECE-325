import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class fnv {
  /**
   * 
   * @param val byte array to hash
   * @return {@code long} fnv hash of byte array
   */
  public static long FNVHash(byte[] val) {
    BigInteger fnvOffsetBasis = new BigInteger("14695981039346656037");
    BigInteger fnvPrime = new BigInteger("1099511628211");
    // largest 64 bit value plus one
    BigInteger longMaxPlusOne = new BigInteger("18446744073709551616");

    BigInteger hashNum = fnvOffsetBasis;

    for (int i = 0; i < val.length; ++i)
      hashNum = fnvPrime.multiply(hashNum).xor(BigInteger.valueOf(val[i])).mod(longMaxPlusOne);

    return hashNum.longValue();
  }

  public static void main(String[] args) {
    System.out.println(FNVHash("test".getBytes()));
  }
}
