package AutoTest.flow.test.sign;




import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public final class HexUtils {

	private static byte[] encodingTable = { (byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4', (byte) '5',
			(byte) '6', (byte) '7', (byte) '8', (byte) '9', (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e',
			(byte) 'f' };
	private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f', 'A', 'B', 'C', 'D', 'E', 'F' };
	private static byte[] decodingTable = new byte[128];

	static {
		for (int i = 0; i < decodingTable.length; i++) {
			decodingTable[i] = (byte) 0xff;
		}

		for (int i = 0; i < encodingTable.length; i++) {
			decodingTable[encodingTable[i]] = (byte) i;
		}

		decodingTable['A'] = decodingTable['a'];
		decodingTable['B'] = decodingTable['b'];
		decodingTable['C'] = decodingTable['c'];
		decodingTable['D'] = decodingTable['d'];
		decodingTable['E'] = decodingTable['e'];
		decodingTable['F'] = decodingTable['f'];
	}

	private HexUtils() {

	}

	public static int encode(byte[] data, int off, int length, OutputStream out) {
		for (int i = off; i < (off + length); i++) {
			int v = data[i] & 0xff;

			try {
				out.write(encodingTable[v >>> 4]);
				out.write(encodingTable[v & 0xf]);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		return length * 2;
	}

	private static boolean ignore(char c) {
		return c == '\n' || c == '\r' || c == '\t' || c == ' ';
	}

	public static int decode(byte[] data, int off, int length, OutputStream out) {
		byte b1;
		byte b2;
		int outLen = 0;

		int end = off + length;

		while (end > off) {
			if (!ignore((char) data[end - 1])) {
				break;
			}

			end--;
		}

		int i = off;
		while (i < end) {
			while (i < end && ignore((char) data[i])) {
				i++;
			}

			b1 = decodingTable[data[i++]];

			while (i < end && ignore((char) data[i])) {
				i++;
			}

			b2 = decodingTable[data[i++]];

			if ((b1 | b2) < 0) {
				throw new RuntimeException("invalid characters encountered in Hex data");
			}

			try {
				out.write((b1 << 4) | b2);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			outLen++;
		}

		return outLen;
	}

	
	public static byte[] decodeFromHexString(String str) {
		if (StringUtil.isBlank(str) || "0x".equals(str) || "0X".equals(str) || "0X0".equals(str)
				|| "0x0".equals(str)) {
			return new byte[0];
		}

		char[] charArray = str.toCharArray();
		int start = 0;

		if (charArray.length >= 2 && charArray[0] == '0' && (charArray[1] == 'x' || charArray[1] == 'X')) {
			start = 2;
		}

		int len = str.length();
		if (len % 2 != 0) {
			throw new RuntimeException("length of non empty hex string must be even! ");
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		while (start < len) {
			char c1 = str.charAt(start++);
			if (Arrays.binarySearch(HEX_CHARS, c1) == -1) {
				throw new RuntimeException("invalid hex char: " + c1);
			}
			char c2 = str.charAt(start++);
			if (Arrays.binarySearch(HEX_CHARS, c2) == -1) {
				throw new RuntimeException("invalid hex char: " + c2);
			}
			byte b1 = decodingTable[c1];
			byte b2 = decodingTable[c2];

			out.write((b1 << 4) | b2);
		}

		return out.toByteArray();
	}


	public static String encode2HexString(byte[] bytes, boolean withPrefix) {
		if (bytes == null || bytes.length == 0) {
			if (withPrefix) {
				return "0x0";
			} else {
				return "";
			}
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream(2 * bytes.length + 2);
		if (withPrefix) {
			out.write('0');
			out.write('x');
		}
		encode(bytes, 0, bytes.length, out);
		try {
			return new String(out.toByteArray(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("invalid characters encountered in Hex string");
		}
	}
}
