package com.melot.testng.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.security.MessageDigest;
import java.util.*;


public class SecurityFunctions {
    private static char[] HEX_TAB_WEB = "s~0!e@5#c$8%r^6&".toCharArray();
    private static String S_PRIVATE_KEY = "cc16be4b:346c51d";

    /**
     * @param jsonObject
     * @return 返回签名加密串
     */
    public static String getSingedValue(JsonObject jsonObject) {
        JsonElement platformJsonElement = jsonObject.get("p");
        int platform = -1;
        if (platformJsonElement != null)
            try {
                platform = platformJsonElement.getAsInt();
            } catch (Exception localException) {
                System.out.println("get platform error " + localException.toString());
            }
        Set es = jsonObject.entrySet();
        Iterator ies = es.iterator();
        String sv = null;
        HashMap params = new HashMap();

        while (ies.hasNext()) {
            Map.Entry e = (Map.Entry) ies.next();
            if (!((String) e.getKey()).equalsIgnoreCase("s")) {
                params.put(e.getKey(), ((JsonElement) e.getValue()).getAsString());
            }
        }

        // app和web 用的不是同一套加密方式
        if ((platform != 1) && (platform != 7) && (platform != 6) && (platform != 12)&& (platform != 11)) {
            sv = sListAPP(params);
        } else {
            sv = sListWEB(params);
            int sum = 0;
            for (int i = 0; i < sv.length(); i += 2) {
                sum += sv.charAt(i);
            }
            char a = HEX_TAB_WEB[(sum % 16)];
            char b = HEX_TAB_WEB[(sum % 13)];
            sv = sv + a + b;
        }
        return sv;
    }

    /**
     * @return 返回web初步的加密串
     */
    private static String sListWEB(Map<String, Object> l) {
        if ((l != null) && (l.size() != 0)) {
            try {
                Set ks = l.keySet();
                String[] kss = new String[ks.size()];
                ks.toArray(kss);
                Arrays.sort(kss, String.CASE_INSENSITIVE_ORDER);
                String stos = "";

                for (int i = 0; i < kss.length; i++) {
                    Object o = l.get(kss[i]);
                    if (o != null) {
                        stos = stos + o.toString();
                    }
                }

                if (stos.length() < 8) {
                    stos = stos + "0123456789012345";
                }

                int[] s1 = new int[8];

                for (int i = 0; i < stos.length() / 8 - 1; i++) {
                    for (int j = 0; j < 8; j++) {
                        if ((i + 1) * 8 + j < stos.length()) {
                            if (i == 0) {
                                s1[j] = stos.charAt(i * 8 + j);
                            }

                            s1[j] ^= stos.charAt((i + 1) * 8 + j);
                        }
                    }
                }

                String s0 = "";

                for (int j = 0; j < 8; j++) {
                    s0 = s0 + HEX_TAB_WEB[(s1[j] >>> 3 & 0xF)];
                    s0 = s0 + HEX_TAB_WEB[(s1[j] & 0xF)];
                }

                return s0;
            } catch (Exception var7) {
                var7.printStackTrace();
                return null;
            }
        }
        return null;
    }

    /**
     * @return 返回app加密串
     */
    private static String sListAPP(Map<String, Object> l) {
        if ((l != null) && (l.size() != 0)) {
            try {
                Set ks = l.keySet();
                String[] kss = new String[ks.size()];
                ks.toArray(kss);
                Arrays.sort(kss, String.CASE_INSENSITIVE_ORDER);
                String stos = "";

                for (int i = 0; i < kss.length; i++) {
                    stos = stos + kss[i] + ":";
                    Object o = l.get(kss[i]);
                    if (o != null) {
                        stos = stos + o.toString();
                    }
                }

                stos = stos + S_PRIVATE_KEY;
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(stos.getBytes());
                byte[] tmp = md.digest();
                byte[] rs = new byte[26];
                BytesTransformer bts = new BytesTransformer(BytesTransformer.EBT_FOR_SIGN);

                if (bts.encode(tmp, tmp.length, rs, 26))
                    return new String(rs);
            } catch (Exception var8) {
                var8.printStackTrace();
            }

            return null;
        }
        return null;
    }
}
