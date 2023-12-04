/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.linhhi.guru99.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author DELL
 */
public class Guru99Test {
    
    static WebDriver myBrowser; 
    
    @BeforeAll  //hàm này sẽ chạy triowsc tất cả @Test nếu có
                //đi đầu tiên, dùng để thiết lập môi trường kiểm thử
                //khởi động các biến gì đó sẽ dùng ở @Test
    public static void setUpClass() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        
        //mở ẩn danh, tiếng Hàn/Nhật ở đây
    }
    
    //TEST CASE #1: Check the login process ò Demo Guru99 in successful case
    //      Steps:
    //              1. Open a certain browser, e.g. Chrome
    //              2. Navigate to Guru99 login page by the url: https://demo.guru99.com/v4
    //              3. Input a valid account into the login pages, e.g. mngr533993/mezaqyd
    //              4. Hit [login] button to process the login form
    // EXPECTED RESULT:
    //              A new dashboard page is showed
    //              with a welcome message ís under the format of Manager Id: <user-name>
    
    @Test  //code để test cái gì đó thì nằm ở đây
           //code để tự động hóa các test case nằm ở đây
           //assertEquals() nằm ở đây
    public void CheckLoginGivenValidAccountLoginsSuccessfully() throws InterruptedException {
        myBrowser = new ChromeDriver();
        myBrowser.get("https://demo.guru99.com/v4");
        myBrowser.manage().window().maximize();
        
        //đưa valid account vào, ta phải đi tìm các tag của màn hình login
        WebElement txtUserName = myBrowser.findElement(By.xpath("//input[@name='uid']"));
        txtUserName.sendKeys("mngr533993");
        
        WebElement txtPassWord = myBrowser.findElement(By.xpath("//input[@name='password']"));
        txtPassWord.sendKeys("mezaqyd");
        
        WebElement txtLogin = myBrowser.findElement(By.xpath("//input[@name='btnLogin']"));
        txtLogin.click(); //enter để submit là submit(), click mouse là 
        
        //đảm bảo bắt ddược tag ở trang mới, ta cần wait 1 xí
        //code cứ nhắm mắt chạy, mà trang chưa kịp tải về do mang lag
        //k wait code k ổn định, mạng nhanh ok nhưng mạng chậm failed tìm tag
        Thread.sleep(2000);
        
        //chuyển trang bắt tag welcome
        WebElement lblMessage = myBrowser.findElement(By.cssSelector("tr[class='heading3'] td"));
        String welcomeMessage = lblMessage.getText();
        //System.out.println("Message: " + welcomeMessage);
        assertEquals("Manger Id : mngr533993", welcomeMessage);
        
    }
    
    @AfterAll   //chạy sau tất cả, sau khi @Test xong thì thằng này chạy
                //nó dùng để dọn dẹp những thứ mà @Test bày ra
                //vd: xóa data trong table do việc chạy @Test insert vào
    public static void tearDownClass() throws InterruptedException {
        Thread.sleep(3000); //ngủ 1 xí để xem kết quả rồi mí dọn dẹp
        myBrowser.quit();   //đóng trình duyệt, dọn dẹp ram
    }
    
}
