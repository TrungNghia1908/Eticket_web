package util;

/**
 * @author A Di Đà Phật
 */
import java.security.SecureRandom;
import java.math.BigInteger;

public final class PinCode {
  private SecureRandom random = new SecureRandom();

  public String getPinCode() {
    String value = new BigInteger(130, random).toString(32);
      return value.substring(0, 5);
  }
  
  public static void main(String[] args) {
      PinCode pinCode = new PinCode();
      System.out.println(pinCode.getPinCode());
  }
}