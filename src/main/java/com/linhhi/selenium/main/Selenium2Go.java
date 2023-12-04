/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.linhhi.selenium.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author DELL
 */
public class Selenium2Go {

    static WebDriver myBrowser;
    //biến trỏ đến tab trình duyệt
    //trong OOP, cái gì cũng là object, phải new, tốn ram
    //1 tab trình duyệt là 1 object trong ram, có UI ra bên ngoài
    
    //muốn có trình duyệt xuất hiện thì phải new()
    
    public static void main(String[] args) throws InterruptedException {
        //nạp cái driver là file .exe vào máy ảo Java để lắng nghe
        //những hàng động code của mình và nó chuyển lại cho trình duyệt
        //--- câu kệnh Class.forName(jdbc driver); môn Java web
        
        System.out.println();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        
        //mở trình duyệt ẩn danh
        ChromeOptions opt = new ChromeOptions();
//        opt.addArguments("--incognito");
//        opt.addArguments("--lang=ar-AE");
        
        
        //gọi trình duyệt xuất hiện
        myBrowser = new ChromeDriver(opt);
        
        //lúc này myBrowser sẽ trỏ thẳng vào tab vừa mở
        //chấm gọi hàm là bắt trình duyệt làm gì đó
        
        //bung full màn hình
        myBrowser.manage().window().maximize();
        
        //duyệt web, tải trang về
        myBrowser.get("https://google.com");
        
        //ta đi tìm các tag - thẻ, để sau đó gọi các event trên tag
        //hoặc là đưa tag vào tag
        //mọi tag trong trang web đều là object: WebElement
        //tìm 1 tag trong trang web giống như select 1 dòng trong table
        //ta phải viết ra câu query để tìm tag như viết SQL tìm dât trong CSDL
        //có nhiều cách để query 1 tag: query theo bame của tag
        //                              id, tên tag, css, className
        //                              xPath - ngầu nhất
        WebElement searchBox = myBrowser.findElement(By.name("q"));
        searchBox.sendKeys("Đất rừng phương Nam");
        searchBox.submit(); //nhấn enter để search
        
        
        //mỗi khi ta tắt tab trình duyệt, driver vẫn nằm trong RAM
        //không được xài đến, RAM giảm dung lượng dần dần - MEMORY LEAKING
        //ta phải tắt trình duyệt đúng chuẩn để free luôn cả driver
        
        Thread.sleep(3000); //dừng CPU, dừng đóng trình duyệt, sau 3s hẵn làm
        myBrowser.quit(); //tắt trình duyệt, gỡ luôn driver khỏi ram
    }
}
