import java.math.BigInteger;

public class BigNumber {
  /**
   * 
   * @param val
   * @return {@code BigInteger}
   */
  public static BigInteger fnv(byte[] val) {
    BigInteger fnvOffsetBasis = new BigInteger("14695981039346656037");
    BigInteger fnvPrime = new BigInteger("1099511628211");

    BigInteger hash = fnvOffsetBasis;

    for (int i = 0; i < val.length; ++i) 
      hash = hash.multiply(fnvPrime).xor(BigInteger.valueOf(val[i]));
    
    return hash;
  }

  public static void main(String[] args) {
    byte[] val = new byte[]{(byte)0xAA, (byte)0xBB, (byte)0xCC, (byte)0xDD, (byte)0xEE};
    System.out.println(fnv(val).toString(10));
  }
}
