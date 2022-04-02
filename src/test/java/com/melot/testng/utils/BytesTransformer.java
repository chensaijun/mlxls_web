package com.melot.testng.utils;

/**
 * @author biaoge
 * @version 1.0.0
 * @date 2019/04/21
 */
public class BytesTransformer {
    private static byte[] eto32_table1 = "AB56DE3C8L2WF4UVM7JRSGPQYZTXK9HN".getBytes();
    private static byte[] eto32_table2 = "2WF4JZ7XKTC8LSGHUDEPQYVM9R63NAB5".getBytes();

    public static int EBT_FOR_SIGN = 1;

    private byte[] eto32_table = null;

    public BytesTransformer(int ts) {
        if (ts == EBT_FOR_SIGN)
            this.eto32_table = eto32_table1;
        else
            this.eto32_table = eto32_table2;
    }

    public boolean encode(byte[] in, int ilen, byte[] out, int olen) {
        if ((in == null) || (out == null))
            return false;
        int s = 0;
        int j = 0;
        int[] buf = {0, 0};
        for (int i = 0; i < ilen; i++) {
            buf[0] = in[i];
            buf[1] = (i + 1 < ilen ? in[(i + 1)] : 0);
            buf[0] <<= s;
            buf[0] &= 255;
            buf[0] >>= s;
            if (s >= 3) {
                buf[0] = (byte) (((buf[0] & 0xFF) << s - 3 | (buf[1] & 0xFF) >> 11 - s) & 0xFF);
                out[(j++)] = this.eto32_table[buf[0]];
                s -= 3;
            } else {
                out[(j++)] = this.eto32_table[(buf[0] >> 8 - s - 5)];
                s += 5;
                i--;
            }
            if (j >= olen)
                break;
        }
        return true;
    }

}