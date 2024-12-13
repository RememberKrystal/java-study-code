package com.remember.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Objects;

public class JsoupTest {
    public static void main(String[] args) {
        // 设置 ChromeDriver 路径
        System.setProperty("webdriver.chrome.driver", "D:\\software\\chromedriver-win64\\chromedriver.exe");

        // 配置 ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1200");
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.102 Safari/537.36");

        // 初始化 WebDriver
        WebDriver driver = new ChromeDriver(options);

        try {
            // 目标 URL
            String baseUrl = "https://www.zhipin.com/web/geek/job?query=";
            String keyword = "软件开发";  // 例如关键词中包含中文
            String city = "&city=101270100"; // 成都
            String page = "&page=";
            int pageNum = 1;

            // 使用 URLEncoder 对中文参数进行编码
            String encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8);
            String url = baseUrl + encodedKeyword + city + page + pageNum;

            // 使用 Selenium 获取页面内容
            driver.get(url);

            // 等待页面中某个元素加载完成
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("search-job-result")));

            // 获取页面内容
            String pageSource = driver.getPageSource();

            // 使用 Jsoup 解析页面内容
            Document doc = Jsoup.parse(pageSource);

            // 现在可以提取你需要的元素
            Element ul = doc.selectFirst("ul.job-list-box");
            Elements lis = null;
            if (ul != null) {
                lis = ul.children(); // 获取 ul 下的 子节点
            }
            if (lis != null) {
                lis.forEach(li -> {
                    Element jobName = li.selectFirst("div.job-card-body span.job-name");
                    Element jobArea = li.selectFirst("div.job-card-body span.job-area");
                    Element jobSalary = li.selectFirst("div.job-card-body span.salary");
                    Elements childrenLis = li.select("ul.tag-list li");

                    Element experience = childrenLis.getFirst(); // 工作经验
                    Element degree = childrenLis.get(1); // 学历

                    Element companyName = li.selectFirst("div.job-card-right div.company-info a"); // 公司名称
                    Element img = li.selectFirst("div.job-card-right div.company-logo img"); // 公司logo

                    if (!Objects.isNull(jobName) && !Objects.isNull(jobArea) && !Objects.isNull(jobSalary)
                            && !Objects.isNull(experience) && !Objects.isNull(degree)
                            && !Objects.isNull(companyName) && !Objects.isNull(img)) {
                        System.out.println(jobName.text() + "  " + jobArea.text() + "  " + jobSalary.text()
                                + "  " + experience.text() + "  " + degree.text() + "  " + companyName.text() + "  " + img.attr("src"));
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭 WebDriver
            driver.quit();
        }
    }
}
