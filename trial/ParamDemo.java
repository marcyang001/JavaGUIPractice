import java.awt.*;
import java.applet.*;

public class ParamDemo extends Applet{
    String strName;
    public void start()
    {
        strName=getParameter("name");
        if(strName==null)
            strName="Not Found";
    }
    public void paint(Graphics g)
    {
        g.drawString("Name :"+strName,10,20);
    }
}