<%@ page contentType="image/jpeg" %>
<%@ page import="java.awt.*,java.awt.image.*" %>
<%@ page import="java.util.*,javax.imageio.*" %>

<%!  
//产生随机颜色函数getRandColor
     Color getRandColor(int fc,int bc){
        Random r = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int red=fc+r.nextInt(bc-fc);    //红
        int green=fc+r.nextInt(bc-fc);  //绿
        int blue=fc+r.nextInt(bc-fc);   //蓝
        return new Color(red,green,blue);
     }
%>

<%  
//设置页面不缓存  
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
    
//创建随机类
    Random r = new Random();

// 在内存中创建图像，宽度为width，高度为height
    int width=60, height=20;
    BufferedImage pic = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

// 获取图形上下文环境
    Graphics gc = pic.getGraphics();

// 设定背景色并进行填充
    gc.setColor(getRandColor(200,250));
    gc.fillRect(0, 0, width, height);

//设定图形上下文环境字体
    gc.setFont(new Font("Times New Roman",Font.PLAIN,18));

// 随机产生200条干扰直线，使图像中的认证码不易被其他分析程序探测到
    gc.setColor(getRandColor(160,200));
    for (int i=0;i<200;i++){
        int x1 = r.nextInt(width);
        int y1 = r.nextInt(height);
        int x2 = r.nextInt(15);
        int y2 = r.nextInt(15);
        gc.drawLine(x1,y1,x1+x2,y1+y2);
    }
//随机产生100个干扰点，使图像中的验证码不易被其他分析程序探测到
	gc.setColor(getRandColor(120,240));
	for (int i=0;i<100;i++) 
	{
	int x = r.nextInt(width); 
	int y = r.nextInt(height); 
	gc.drawOval(x,y,0,0); 
	} 

// 随机产生4位数字的验证码
    String RS="";
    String rn="";
    for(int i=0;i<4;i++){
        //产生10以内随机数字rn
        rn=String.valueOf(r.nextInt(10));
        RS+=rn;
        //将认证码用drawString函数显示到图像里
        gc.setColor(new Color(20+r.nextInt(110),20+r.nextInt(110),20+r.nextInt(110)));
        gc.drawString(rn,13*i+6,16);
    }

// 释放图形上下文环境
    gc.dispose();

// 将认证码RS存入SESSION中共享
    session.setAttribute("random",RS);

// 输出生成后的验证码图像到页面
    ImageIO.write(pic, "JPEG", response.getOutputStream());

    out.clear();
    out = pageContext.pushBody();

  
%>