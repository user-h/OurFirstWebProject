package com.datang.hrb.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

public class ImgCodeUtil {
	private static int width = 117;// 瀹氫箟鍥剧墖鐨剋idth
    private static int height = 45;// 瀹氫箟鍥剧墖鐨刪eight
    private static int codeCount = 4;// 瀹氫箟鍥剧墖涓婃樉绀洪獙璇佺爜鐨勪釜鏁�
    private static int xx = 20;
    private static int fontHeight = 40;
    private static  int codeY = 35;
    private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    
    /**
     * 鐢熸垚涓�涓猰ap闆嗗悎
     * code涓虹敓鎴愮殑楠岃瘉鐮�
     * codePic涓虹敓鎴愮殑楠岃瘉鐮丅ufferedImage瀵硅薄
     * @return
     */
    public static Map<String,Object> generateCodeAndPic() {
        // 瀹氫箟鍥惧儚buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Graphics2D gd = buffImg.createGraphics();
        // Graphics2D gd = (Graphics2D) buffImg.getGraphics();
        Graphics gd = buffImg.getGraphics();
        // 鍒涘缓涓�涓殢鏈烘暟鐢熸垚鍣ㄧ被
        Random random = new Random();
        // 灏嗗浘鍍忓～鍏呬负鐧借壊
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);

        // 鍒涘缓瀛椾綋锛屽瓧浣撶殑澶у皬搴旇鏍规嵁鍥剧墖鐨勯珮搴︽潵瀹氥��
        Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
        // 璁剧疆瀛椾綋銆�
        gd.setFont(font);

        // 鐢昏竟妗嗐��
        gd.setColor(Color.BLACK);
        gd.drawRect(0, 0, width - 1, height - 1);

        // 闅忔満浜х敓40鏉″共鎵扮嚎锛屼娇鍥捐薄涓殑璁よ瘉鐮佷笉鏄撹鍏跺畠绋嬪簭鎺㈡祴鍒般��
        gd.setColor(Color.BLACK);
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }

        // randomCode鐢ㄤ簬淇濆瓨闅忔満浜х敓鐨勯獙璇佺爜锛屼互渚跨敤鎴风櫥褰曞悗杩涜楠岃瘉銆�
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;

        // 闅忔満浜х敓codeCount鏁板瓧鐨勯獙璇佺爜銆�
        for (int i = 0; i < codeCount; i++) {
            // 寰楀埌闅忔満浜х敓鐨勯獙璇佺爜鏁板瓧銆�
            String code = String.valueOf(codeSequence[random.nextInt(36)]);
            // 浜х敓闅忔満鐨勯鑹插垎閲忔潵鏋勯�犻鑹插�硷紝杩欐牱杈撳嚭鐨勬瘡浣嶆暟瀛楃殑棰滆壊鍊奸兘灏嗕笉鍚屻��
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);

            // 鐢ㄩ殢鏈轰骇鐢熺殑棰滆壊灏嗛獙璇佺爜缁樺埗鍒板浘鍍忎腑銆�
            gd.setColor(new Color(red, green, blue));
            gd.drawString(code, (i + 1) * xx, codeY);

            // 灏嗕骇鐢熺殑鍥涗釜闅忔満鏁扮粍鍚堝湪涓�璧枫��
            randomCode.append(code);
        }
        Map<String,Object> map  =new HashMap<String,Object>();
        //瀛樻斁楠岃瘉鐮�
        map.put("code", randomCode);
        //瀛樻斁鐢熸垚鐨勯獙璇佺爜BufferedImage瀵硅薄
        map.put("codePic", buffImg);
        return map;
    }

    public static void main(String[] args) throws Exception {
        //鍒涘缓鏂囦欢杈撳嚭娴佸璞�
        OutputStream out = new FileOutputStream("C://"+System.currentTimeMillis()+".jpg");
        Map<String,Object> map = ImgCodeUtil.generateCodeAndPic();
        ImageIO.write((RenderedImage) map.get("codePic"), "jpeg", out);
        System.out.println("验证码"+map.get("code"));
    }
}
