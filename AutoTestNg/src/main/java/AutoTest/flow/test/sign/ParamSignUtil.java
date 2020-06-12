package AutoTest.flow.test.sign;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


public class ParamSignUtil {

	private static final String SIGN_ALGORITHM = "HmacSHA1";
	
	private static Pattern ENCODED_CHARACTERS_PATTERN;
	
	private static final String CHARSET = "UTF-8";
	
	static {
        StringBuilder pattern = new StringBuilder();

        pattern.append(Pattern.quote("+")).append("|").append(Pattern.quote("*")).append("|")
            .append(Pattern.quote("%7E")).append("|").append(Pattern.quote("%2F"));

        ENCODED_CHARACTERS_PATTERN = Pattern.compile(pattern.toString());
    }

	private static List<String> signKeyFilter(Map<String, String> map) {
		List<String> keys = new ArrayList<String>();
		if(map != null && !map.isEmpty()) {
			for(Iterator<Entry<String, String>> iter = map.entrySet().iterator(); iter.hasNext(); ) {
				String key = iter.next().getKey();
				if(!StringUtil.equals(key, "sign")) {
					keys.add(key);
				}
			}
		}
		return keys;
	}
	
	public static String sign(Map<String, String> params, String secret)
			throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
		Mac mac = Mac.getInstance(SIGN_ALGORITHM);
		mac.init(new SecretKeySpec(secret.getBytes(CHARSET), SIGN_ALGORITHM));
		List<String> keys = signKeyFilter(params);

		Collections.sort(keys);

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			if (i != 0) {
				mac.update("&".getBytes(CHARSET));
			}
			mac.update(urlEncode(key).getBytes(CHARSET));
			mac.update("=".getBytes(CHARSET));
			mac.update(urlEncode(params.get(key)).getBytes(CHARSET));
		}

		byte[] signData = mac.doFinal();

		return HexUtils.encode2HexString(signData, false);
	}
	

    public static String urlEncode(final String value) {
        if (value == null) {
            return "";
        }

        try {
            String encoded = URLEncoder.encode(value, CHARSET);

            Matcher matcher = ENCODED_CHARACTERS_PATTERN.matcher(encoded);
            StringBuffer buffer = new StringBuffer(encoded.length());

            while (matcher.find()) {
                String replacement = matcher.group(0);

                if ("+".equals(replacement)) {
                    replacement = "%20";
                } else if ("*".equals(replacement)) {
                    replacement = "%2A";
                } else if ("%7E".equals(replacement)) {
                    replacement = "~";
                }

                matcher.appendReplacement(buffer, replacement);
            }

            matcher.appendTail(buffer);
            return buffer.toString();

        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
