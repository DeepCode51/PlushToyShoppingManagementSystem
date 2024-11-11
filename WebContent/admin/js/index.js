function setIframeWH()
{
     
     //设置LeftMenu高度自适应     
     var LeftMenu=document.getElementById("LeftMenu");
     var LHeight1 = LeftMenu.contentWindow.document.body.scrollHeight;
     var LHeight2 = LeftMenu.contentWindow.document.documentElement.scrollHeight; 
     var LHeight = Math.max(LHeight1, LHeight2);
     LeftMenu.height =  (document.documentElement.clientHeight-106)+"px";
     //设置MainFrame高度自适应       
     var MainFrame=document.getElementById("MainFrame");
     var RHeight1 = MainFrame.contentWindow.document.body.scrollHeight;
     var RHeight2 = MainFrame.contentWindow.document.documentElement.scrollHeight; 
     var RHeight = Math.max(RHeight1, RHeight2);     
     MainFrame.height =  (document.body.clientHeight-106)+"px";
}   