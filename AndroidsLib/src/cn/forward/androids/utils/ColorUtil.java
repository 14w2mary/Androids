package cn.forward.androids.utils;

import android.graphics.Color;

/**
 * ��ɫ������
 *
 * @author hzw
 * @date 2016/1/24.
 */
public class ColorUtil {

    /**
     * ���㽥������ɫ
     *
     * @param startColor ��ʼ��ɫ
     * @param endColor   ������ɫ
     * @param rate       �����ʣ�0,1��
     * @return ��������ɫ����rate=0ʱ������startColor����rate=1ʱ����endColor
     */
    public static int computeGradientColor(int startColor, int endColor, float rate) {
        if (rate < 0) {
            rate = 0;
        }
        if (rate > 1) {
            rate = 1;
        }

        int alpha = Color.alpha(endColor) - Color.alpha(startColor);
        int red = Color.red(endColor) - Color.red(startColor);
        int green = Color.green(endColor) - Color.green(startColor);
        int blue = Color.blue(endColor) - Color.blue(startColor);

        return Color.argb(
                Math.round(Color.alpha(startColor) + alpha * rate),
                Math.round(Color.red(startColor) + red * rate),
                Math.round(Color.green(startColor) + green * rate),
                Math.round(Color.blue(startColor) + blue * rate));
    }
}
